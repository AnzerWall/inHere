/**
 * Created by lirongsheng on 16/10/8.
 */
import {checkResult} from '../api/base.js'
let praise=function(ext_data,id,ext_type,self){
  var token="4121581213c1605a1db4872d7cca6eed1b41259bffd8066d9573783b07214d6f";
  var item= function(ext_type){
    if(ext_type===null){
      return { item_id : id };
    }else{
      return {ext_type: ext_type, item_id: id }
    }

  }(ext_type);
  console.log(ext_data,id,ext_type);
  self.$request
    .post("http://115.28.67.181:8080/praise")
    .query({token:token})
    .send(item)
    .then(self.$api.checkResult)
    .then((data=>{
      console.log(data);
      if(ext_data.praised==0){
        ext_data.praise=ext_data.praise+1;
        ext_data.praised=1;
      }
      else if(ext_data.praised==1){
        ext_data.praise=ext_data.praise-1;
        ext_data.praised=0;
      }

    }))
    .catch(function (e) {
      console.error(e);
    });
}
  export default{
  praise:praise
}
