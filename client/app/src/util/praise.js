/**
 * Created by lirongsheng on 16/10/8.
 */

import {checkResult,url_base} from '../api/base.js'
let url=`${url_base}/praise`;
let praise=function(ext_data,id,ext_type,self){
  var item= function(ext_type){
    if(ext_type===null){
      return { item_id : id };
    }else{
      return {ext_type: ext_type, item_id: id }
    }

  }(ext_type);
  console.log(ext_data,id,ext_type);
  self.$request
    .post(url)
    .query({token:self.token})
    .send(item)
    .then(checkResult)
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
