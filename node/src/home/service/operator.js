/**
 * Created by anzer on 2016/9/12.
 */
'use strict';

export default class extends think.service.base {
    /**
     * init
     * @return {}         []
     */
    constructor(data={}) {
        super();
        this.user_id = data.user_id||"";
        this.school_id =data.school_id||"";
        this.role_id=data.role_id||"";

        think.log(`${this.user_id}`,"OPERATOR");
    }

    isMe(user_id) {
        return this.user_id+"" === user_id+"";

    }
    inList(list) {
        return list.indexOf(this.user_id+"")!=-1;
    }
    isLogin(){
        return (this.user_id+"") !=="";
    }
}