/**
 * Created by anzer on 2016/9/9.
 */
let data={};
let storage = window.localStorage;
export default{
  get(key){
    return storage.getItem(key);
  },
  set(key,val){
    return storage.setItem(key,val);
  },
  clear(){
    return storage.clear()
  },
  remove(){
    return storage.removeItem(key);
  }

}
