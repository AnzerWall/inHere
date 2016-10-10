package com.inHere.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.FileType;
import com.inHere.constant.Path;
import com.inHere.dao.SchoolMapper;
import com.inHere.dto.ReturnPhotoDto;
import com.inHere.entity.School;
import com.inHere.service.CommonService;
import com.inHere.util.FileUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 公共业务模块
 *
 * @author lwh
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Value("${ip.root}")
    private String ip;

    @Autowired
    private SchoolMapper schoolMapper;

    /**
     * 图片输出：图片解析成JSONArray传输对象
     *
     * @return
     * @throws IOException
     */
    public JSONArray photoResolution(String photos) throws IOException {
        JSONArray array = new JSONArray();
        // 判断是否有图片
        if (photos != null && !photos.trim().equals("")) {
            // 解析图片信息
            JSONArray tmpArray = null;
            try {
                tmpArray = JSON.parseArray(photos);
            } catch (Exception e) {
                tmpArray = new JSONArray();
                tmpArray.add(JSON.parseObject(photos));
            }

            int len = tmpArray.size();
            for (int i = 0; i < len; i++) {
                ReturnPhotoDto photo = new ReturnPhotoDto();

                JSONObject obj = tmpArray.getJSONObject(i);
                String src = obj.getString("src");

                // 获取项目根路径和图片路径
                String root = System.getProperty("inHere.root");
                String minSrc = root + File.separator + src.replace("max", "min").replace("/", File.separator);

                // 小图转Base64传输
                File file = new File(minSrc);
                // 图片不存在
                if (!file.exists()) {
                    file = new File(Path.Photo404);
                    src = Path.Photo404Uri;
                }
                FileInputStream inputFile = new FileInputStream(file);
                byte[] buffer = new byte[(int) file.length()];
                inputFile.read(buffer);
                inputFile.close();
                String min = Base64.encodeBase64String(buffer);

                Integer w = obj.getInteger("w");
                Integer h = obj.getInteger("h");

                photo.setMin(min);
                photo.setSrc(ip + src);
                photo.setW(w);
                photo.setH(h);
                array.add(photo);
            }
            return array;
        }
        return array;
    }

    /**
     * 图片写入：解析图片列表
     *
     * @return
     */
    public JSONArray resolverPhotos(List<MultipartFile> fileList) throws IOException {
        JSONArray photos = new JSONArray();
        // 原图保存及缩略图创建
        for (MultipartFile file : fileList) {
            // 空文件不处理
            if (file.getSize() == 0) continue;
            FileType fileType = FileUtil.getTypeByByte(file.getBytes());

            // 生成随机唯一的文件名
            String fineName = UUID.randomUUID().toString() + "-max." + fileType.getSuffix();

            // 检测文件夹是否存在
            File photoDir = new File(Path.PhotoDir);
            if (!(photoDir.exists())) {
                photoDir.mkdir();
            }
            File maxFile = new File(Path.PhotoDir + fineName);
            File minFile = new File(Path.PhotoDir + fineName.replace("max", "min"));
            // 保存原图
            file.transferTo(maxFile);
            // 缩略图创建, 按比例缩放
            Thumbnails.of(maxFile).size(30, 30).toFile(minFile);

            String uri = Path.PhotoUri + fineName;
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
        return photos;
    }

    /**
     * 获取学校列表信息
     *
     * @return
     */
    @Override
    public JSONObject getSchools() {
        JSONObject data = new JSONObject();
        List<School> schools = schoolMapper.selectAll();
        data.put("total", schools.size());
        JSONArray items = new JSONArray();
        for (School school : schools) {
            JSONObject item = new JSONObject();
            item.put("id", school.getId());
            item.put("name", school.getSchool());
            items.add(item);
        }
        data.put("items", items);
        return data;
    }
}
