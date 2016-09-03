/**
 * Created by anzer on 2016/8/31.
 */
import  request from  'superagent'
//import  bluebird from 'bluebird'
import fakeApi from './fake/api.js'


//global.Promise=bluebird;
fakeApi(request);

export default request;
