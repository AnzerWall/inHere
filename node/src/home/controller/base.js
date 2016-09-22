'use strict';

export default class extends think.controller.base {
  /**
   * some base method in here
   */
  constructor(...arg){
    super(...arg);
    let TokenService=this.service("token");
    this.Token=new TokenService();
  }
  async __before(){
    this.data=this.http._data;
    let token=this.get("token");
    if(think.isEmpty(token)){
      let Operator=this.service("operator");
      this.operator=new Operator();
    }else{
      let Operator=this.service("operator");
      let user_info=await this.Token.verifyTokenAndGetInfo(token);
      this.operator=new Operator(user_info);

    }
    this.header("Access-Control-Allow-Origin", "*");
    this.header("Access-Control-Allow-Methods", "GET,PUT,DELETE,POST,OPTION,HEAD,PATCH");
    this.header("Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept");

  }
  success(data){
    return this.json({
      code:200,
      status:"success",
      data:data
    })
  }
}