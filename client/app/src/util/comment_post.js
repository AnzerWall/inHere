/**
 * Created by lirongsheng on 16/9/26.
 */
import {checkResult} from '../api/base.js'

let post=function(request,content,id,ext_type,self){
  var token = "4121581213c1605a1db4872d7cca6eed1b41259bffd8066d9573783b07214d6f";
  //var self = this;
  self.$request=request;
  self.content=content;
  self.id=id;
  self.ext_type=ext_type;
  self.$request
    .post("http://115.28.67.181:8080/comments")
    .query({token: token})
    .send({content: self.content, item_id: self.id, ext_type: self.ext_type})
    .then(checkResult)
    .then(function (res) {
      self.content = "";
//            console.log(res);
      self.$request//注意this问题。。。这里的this不等于外部的this,上次说过了
        .get("http://115.28.67.181:8080/comments")
        .query({token: token})
        .query({ext_type: self.ext_type})
        .query({item_id: self.id})
        .then(checkResult)
        .then(function (data) {
          console.log(data);
          self.comments = data.items;
        })
    })
    .catch(function (e) {
      //console.error(e);
    });
}
export default{
  post:post
}
