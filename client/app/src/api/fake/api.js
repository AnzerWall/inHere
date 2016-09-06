"use strict";
/**
 * Created by anzer on 2016/9/3.
 */
import mocker from './mocker.js';
import Demand from './demand.js'
//拦截api请求
export default function (request){
  let mock =mocker(request);
  mock.timeout = 800;
  //拦截demand相关请求
  Demand.fake(mock);

}


//export default function (){
//
//}
