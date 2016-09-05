"use strict";
/**
 * Created by anzer on 2016/9/2.
 */
import request from './base.js'
import  moment from 'moment'
let urlBase="";
let $api={};
function nowText(){
  return moment(Date.now()).format("YYYY-MM-DD HH:mm:ss")
}
function generateGetFunction({url,id,query}){
    let _url=urlBase+url;
    if(id!=undefined)_url+="/"+id;

    return function (token,_query){
      console.log(`[${nowText()}] API调用:${_url} token:${token}`);
      let ret=request.get(_url)
        .query({ token:token })
        .query(_query);
      if(query!=undefined)ret=ret.query(query);

      return ret;

    }
}
function generatePostFunction({url,id,ext_query,ext_body}){
  let _url=urlBase+url;
  if(id!=undefined)_url+="/"+id;
  return function (token,body){
    let now=Date.now();
    console.log(`[${nowText()}] API调用:${_url}`);
    let ret=request.post(_url)
      .query({ token:token })
      .type("json")
      .send(body);

    if(ext_query!=undefined)ret=ret.query(ext_query);
    if(ext_body!=undefined)ret=ret.send(ext_body);
    return ret;
  }
}
$api.demand={
  listTask:generateGetFunction({url:"/demand",query:{"label":'task'}})

};



export default{
  install(Vue,options){
    Vue.prototype.$api=$api

  }
}
