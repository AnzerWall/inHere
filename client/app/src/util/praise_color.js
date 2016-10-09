/**
 * Created by lirongsheng on 16/10/8.
 */
let messageColor=function(item){
  if(item.ext_data.praised==0){
    return "#cccccc"
  }
  else if(item.ext_data.praised==1){
    return "#000000"
  }
}

let commentColor=function(list){
  if(list.praised==0){
    return "#cccccc"
  }
  else if(list.praised==1){
    return "#000000"
  }
}
export default{
  messageColor,
  commentColor
}
