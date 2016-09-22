/**
 * Created by anzer on 2016/9/21.
 */
import Chance from 'chance'
let chance = new Chance();
let timeout=4000;
let request_table={};
function genRequestKey(){
  return chance.string({length:20})
}

export default{

  startRequest(socket,event,data){
    let request_key=genRequestKey();
    let request_value={};
    request_value.resolve=new Function();
    request_value.reject=new Function();
    request_value.status='padding';
    request_value.promise=new Promise(function(resolve,reject){
      request_value.resolve=resolve;
      request_value.reject=reject;
      setTimeout(()=>{
          if(request_value.status==='padding'){
            request_value.status='reject';
            reject(new Error("Websocket Query Timeout"));
          }
      },timeout)
    });
    request_table[request_key]=request_value;
    socket.emit(event,{
      request_key:request_key,
      data:data
    });
    request_value.promise.request_key=request_key;
    return request_value.promise;



  },
  endRequest(request_key,data){
    if(typeof request_table[request_key]!='undefined'&&request_table[request_key].status==='padding'){
      request_table[request_key].resolve(data);
      delete    request_table[request_key];
    }
  }

}
