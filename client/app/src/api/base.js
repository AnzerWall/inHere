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
    if(typeof res.body!=="object"||!res.body){
      return Promise.reject({
        type:'API_ERROR',
        code:500,
        message:"api通讯失败，res.body为空"
      })
    }else{
      if(res.body.code===200){
        return Promise.resolve(res.body.data)
      }else{
        return Promise.reject({
          type:'API_ERROR',
          code:res.body.code,
          message:res.body.status
        });
      }
    }
  }else{
    return Promise.reject({
      type:'API_ERROR',
      code:500,
      message:'api通讯失败'
    });
  }


}

export default request;
export {java_api_base,url_base,node_api_base,checkResult};
