'use strict';
/**
 * logic
 * @param  {} []
 * @return {}     []
 */
export default class extends think.logic.base {
    async createAction(){
        this.rules={
            title:"string|post",
            text:"string|post"
        }
    }
    async updateAction(){
        this.rules={
            id:"int|get",
            title:"string|post",
            text:"string|post"
        }
    }
    async praiseAction(){
        this.rules={
            id:"int|get"
        }
    }
    async lowAction(){
        this.rules={
            id:"int|get"
        }
    }
    async getListAction(){
        this.rules={
            limit:"int|get|min:1|max:50|default:10",
            offset:"int|get|min:0|default:0"
        }
    }
}