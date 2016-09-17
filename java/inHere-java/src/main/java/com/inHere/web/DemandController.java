package com.inHere.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.annotation.Params;
import com.inHere.constant.Code;
import com.inHere.constant.Field;
import com.inHere.constant.FileType;
import com.inHere.constant.Path;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.dto.ReturnDemandDto;
import com.inHere.dto.ReturnListDto;
import com.inHere.entity.Demand;
import com.inHere.entity.Token;
import com.inHere.service.DemandService;
import com.inHere.util.FileUtil;
import com.inHere.validator.DemandValidator;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 有求必应Controller
 *
 * @author lwh
 */
@RestController
public class DemandController {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private DemandService demandService;

    /**
     * @param params
     * @return
     * @throws IOException
     */
    @Authorization
    @Params(DemandValidator.class)
    @RequestMapping(path = "/demand", method = RequestMethod.GET)
    public ReturnBaseDto<ReturnListDto> getDemandList(ParamsListDto params, @CurrentToken Token token)
            throws IOException {
        // 初始化参数
        Integer offset = params.getOffset() == null ? 0 : params.getOffset();
        Integer limit = params.getLimit() == null ? 10 : params.getLimit();
        params.setOffset(offset);
        params.setLimit(limit);
        params.setTokenEntity(token);

        ReturnListDto listDto = demandService.getList(params);

        ReturnBaseDto<ReturnListDto> result = new ReturnBaseDto<ReturnListDto>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        result.setData(listDto);
        return result;
    }

    /**
     * @param item_id
     * @return
     * @throws IOException
     */
    @Authorization
    @RequestMapping(path = "/demand/{item_id}", method = RequestMethod.GET)
    public ReturnBaseDto<ReturnDemandDto> getDemandItem(@PathVariable Integer item_id, @CurrentToken Token token)
            throws IOException {
        log.info("参数" + item_id);
        ReturnBaseDto<ReturnDemandDto> result = new ReturnBaseDto<ReturnDemandDto>();
        ParamsListDto params = new ParamsListDto();

        // 实体关联的评论列表分页初始化
        params.setItem_id(item_id);
        params.setLimit(5);
        params.setOffset(0);
        params.setTokenEntity(token);

        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        ReturnDemandDto data = demandService.selectOneById(params);
        result.setData(data == null ? new ReturnDemandDto() : data);
        return result;
    }

    /**
     * 创建需求资源
     *
     * @return
     */
    @Authorization
    @Params(DemandValidator.class)
    @RequestMapping(path = "/demand", method = RequestMethod.POST)
    public ReturnBaseDto<JSONObject> createDemandItem(HttpServletRequest request, @CurrentToken Token token) throws IOException {
        // 将request变成多部分request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

        Demand demand = this.createDemand(multiRequest, token);

        // 提交数据库创建一条需求
        demandService.createDemand(demand);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<JSONObject>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        return result;
    }

    /**
     * 创建一个需求
     *
     * @param multiRequest
     * @return
     */
    public Demand createDemand(MultipartHttpServletRequest multiRequest, Token token) throws IOException {
        Demand demand = new Demand();

        Integer ext_type = Integer.parseInt(multiRequest.getParameter("ext_type"));
        demand.setExtType(ext_type);
        String text = multiRequest.getParameter("text");
        demand.setText(text);
        demand.setIsEnd(Field.IsEnd_NO);
        demand.setUserId(token.getUser_id());
        demand.setPraise(new JSONObject().toJSONString());

        JSONObject ext_data = new JSONObject();
        JSONArray photos = new JSONArray();

        // 获取上传图片集合
        List<MultipartFile> fileList = multiRequest.getFiles("file");

        // 原图保存及缩略图创建
        for (MultipartFile file : fileList) {
            FileType fileType = FileUtil.getTypeByByte(file.getBytes());

            // 生成随机唯一的文件名
            String fineName = UUID.randomUUID().toString() + "-max." + fileType.getSuffix();
            File maxFile = new File(Path.DemandDir + fineName);
            File minFile = new File(Path.DemandDir + fineName.replace("max", "min"));
            // 保存原图
            file.transferTo(maxFile);
            // 缩略图创建
            Thumbnails.of(maxFile).size(50, 50).toFile(minFile);

            String uri = Path.DemandUri + fineName;
            // 获取图片宽高
            BufferedImage buff = ImageIO.read(maxFile);
            Integer w = buff.getWidth();
            Integer h = buff.getHeight();
            JSONObject img = new JSONObject();
            img.put("src", uri);
            img.put("w", w);
            img.put("h", h);

            photos.add(img);
        }
        demand.setPhotos(photos.toJSONString());

        // 处理快递私有属性
        if (Field.ExtType_Express == ext_type) {
            Double pay = Double.parseDouble(multiRequest.getParameter("pay"));
            ext_data.put("pay", pay);
            demand.setExtDataJSON(ext_data);
            return demand;
        }

        // 处理转让私有属性
        if (Field.ExtType_Sell == ext_type) {
            Double price = Double.parseDouble(multiRequest.getParameter("price"));
            Double original_price = Double.parseDouble(multiRequest.getParameter("original_price"));
            log.info("--->" + multiRequest.getParameter("quality") );
            String quality = multiRequest.getParameter("quality");
            String buy_time = multiRequest.getParameter("buy_time");
            ext_data.put("price", price);
            ext_data.put("original_price", original_price);
            ext_data.put("quality", quality);
            ext_data.put("buy_time", buy_time);
            demand.setExtData(ext_data.toJSONString());
            return demand;
        }

        // 处理帮忙私有属性
        if (Field.ExtType_Help == ext_type) {
            Double pay = Double.parseDouble(multiRequest.getParameter("pay"));
            ext_data.put("pay", pay);
            demand.setExtData(ext_data.toJSONString());
            return demand;
        }

        // 处理丢失私有属性
        if (Field.ExtType_Lost == ext_type) {

            return demand;
        }

        // 处理找到私有属性
        if (Field.ExtType_Found == ext_type) {

            return demand;
        }

        // 处理走起私有属性
        if (Field.ExtType_Dating == ext_type) {

            return demand;
        }
        return null;
    }

}