"use strict";
/**
 * Created by anzer on 2016/9/5.
 */
import  moment from 'moment';
export function fromNow(time){

  return moment(time).fromNow();
}
export function humanized(time){
  return moment(time).format('h:mm A');
}
