/**
 * Created by anzer on 2016/9/12.
 */
export default class extends think.model.base {
    parseValue(value){
        return this.db().parseValue(value);
    }
    //async countSelect(...arg){
    //    let ret=await super.countSelect(...arg);
    //    return {
    //        count:ret.count,
    //        items:ret.data
    //    }
    //}
}