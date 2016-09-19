/**
 * Created by anzer on 2016/8/31.
 */
import  request from  'superagent'
import  bluebird from 'bluebird'
import fakeApi from './fake/api.js'

window.request=request;
global.Promise=bluebird;


fakeApi(request);
let java_api_base="http://115.28.67.181:8080";
let  url_base="http://115.28.67.181:8080";
let node_api_base="http://127.0.0.1:8360";
 function checkResult(res){
  if(res.ok){
    if(res.body===undefined){
      return Promise.reject(new Error("api通讯失败，res.body未定义"))
    }else{
      if(res.body.code===200){
        return Promise.resolve(res.body.data)
      }else{
        return Promise.reject(new Error(res.body.status,res.body.code));
      }
    }
  }else{
    return Promise.reject(new Error("api通讯失败，res.ok不等于true"))
  }


}

export default request;
export {java_api_base,url_base,node_api_base,checkResult};
