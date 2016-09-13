/**
 * Created by anzer on 2016/9/12.
 */
export function  thenDefault(value,default_value){
    if(value===undefined)return default_value;
    else return value;
}