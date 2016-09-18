"use strict";
/**
 * Created by anzer on 2016/9/3.
 */
import mocker from './mocker.js';
import Demand from './demand.js'
import OfficialNotice from './official_notice.js'
import NoteNotice from './note_notice.js'
import ModuleNotice from './module_notice.js'
import Square from './square.js'
//拦截api请求
export default function (request){
  let mock =mocker(request);
  mock.timeout = 300;
  //拦截demand相关请求
  mock.get('/demand',Demand.getList);
  mock.get('/demand/:id', Demand.getDetail);
  mock.get('/official_notice',OfficialNotice.getList);
  mock.get('/note_notice',NoteNotice.getList);
  mock.get('/module_notice',ModuleNotice.getList);
  mock.get('/square',Square.getList);
}


//export default function (){
//
//}
