/**
 * Created by lirongsheng on 16/9/26.
 */
import {checkResult,url_base} from '../api/base.js';

let post=function(request,content,id,ext_type,self){
 let url=`${url_base}/comments`
  //var self = this;
  self.$request=request;
  self.content=content;
  self.id=id;
  self.ext_type=ext_type;
  console.log(content);
  self.$request
    .post(url)
    .query({token: self.token})
    .send({content: self.content, item_id: self.id, ext_type: self.ext_type})
    .then(checkResult)
    .then(function (res) {
      self.content = "";
      self.$request//注意this问题。。。这里的this不等于外部的this,上次说过了
        .get(url)
        .query({token: self.token})
        .query({ext_type: self.ext_type})
        .query({item_id: self.id})
        .then(checkResult)
        .then(function (data) {
          self.comments = data.items;
          self.total=data.total;
        })
    })
    .catch(function (e) {
      console.error(e);
    });
}

export default{
  post:post
}

