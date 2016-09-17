/**
 * Created by anzer on 2016/9/14.
 */
import _ from "lodash"
import Joi from 'joi'
export default class extends think.logic.base {
    constructor(...arg) {
        super(...arg);
        this.Joi = Joi;
        this._data = {};
        this._schema = undefined;
        this._needValidate=false;
    }
    get data(){
        return  this._data;
    }
    set data(data){
        this._data = think.extend({}, data);
        for (var key in  this._data) {
            if (typeof this._data[key] === 'undefined')
                delete  this._data[key]
        }
    }
    get schema(){
        return this._schema;
    }
    set schema(schema){
        this._needValidate=true;
        this._schema=schema;
    }
    __after() {
        if(this._needValidate){
            let config=this.config('error');
            let result = Joi.validate(this._data, this._schema);
            if(!think.isEmpty(result.error)){
                return this.fail(config.validate_errno||1000,result.error);
            }
            this._data=result.value;

        }
        this.http.data=this._data;
    }
}