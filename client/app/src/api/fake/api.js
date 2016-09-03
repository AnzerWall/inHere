"use strict";
/**
 * Created by anzer on 2016/9/3.
 */
import mocker from 'superagent-mocker';
import fakeDemandApi from './FakeDemandApi.js'
export default function (request){
  let mock =mocker(request);
  fakeDemandApi(mock);

}
