/**
 * Created by anzer on 2016/8/31.
 */
import  request from  'superagent'
import  bluebird from 'bluebird'
import fakeApi from './fake/api.js'

window.request=request;
global.Promise=bluebird;


fakeApi(request);
import {java_api_base,url_base,node_api_base} from '../common/config.js'

 function checkResult(res){
  if(res.ok){
    if(typeof res.body!=="object"){
      return Promise.reject(new Error("api通讯失败，res.body为空"))
    }else{
      if(res.body.code===200){
        return Promise.resolve(res.body.data)
      }else{
        if(res.body.message)
          return Promise.reject(new Error(res.body.status+" : "+res.body.message,res.body.code));
        else
          return Promise.reject(new Error(res.body.status,res.body.code));
      }
    }
  }else{
    return Promise.reject(new Error("api通讯失败，res.ok不等于true"))
  }


}

export default request;
export {java_api_base,url_base,node_api_base,checkResult};
