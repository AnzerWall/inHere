"use strict";
/**
 * Created by anzer on 2016/9/5.
 */
import  moment from 'moment';

moment.locale('zh-cn');
export function fromNow(time){

  return moment(time).fromNow();
}
export function fromNowAndFormat(_time){
  let time=moment(_time);
  let now=moment();
  if(now.diff(time,'hour')>=23){
    return time.format('MM-DD h:mm');
  }else{
    return time.fromNow();
  }

  //
}
export function humanized(time){
  return moment(time).format('h:mm A');
}

export function date(timestamp){
  return moment(timestamp).format('YYYY-MM-DD');
}
export function now(timestamp){

  return moment(timestamp).format('YYYY-MM-DD HH:mm');
}
export function parseDateTime(datetime) {
  return moment(datetime).valueOf();
}
