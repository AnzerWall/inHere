/**
 * Created by anzer on 2016/9/18.
 */
import request from '../../api/base.js'
import {java_api_base as url_base,checkResult} from '../../api/base.js'


function login ({dispatch},username,password) {
  console.log(username,password);
  return request.get(`${url_base}/login`)
    .send({
      user_id:username,
      passwd:password
    })
    .then(checkResult)
    .then(function(data){
        console.log(data);
    })

}
