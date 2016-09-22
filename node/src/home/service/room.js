/**
 * Created by anzer on 2016/9/19.
 */
let redis = think.service("redis",'home');
let Operator=think.service("operator","home");
export default class extends think.service.base{
    async online(operator){
        await redis.hsetAsync("websocket_online_user_info",operator.user_id,JSON.stringify(operator));
        //await redis.saddAsync("websocket_online_user_list",operator.user_id);
        await redis.hsetAsync("websocket_online_user_socket_id",operator.socket_id,JSON.stringify(operator));

    }
    async offline(operator){
        await redis.hdelAsync("websocket_online_user_info",operator.user_id);
        await redis.hdelAsync("websocket_online_user_socket_id",operator.socket_id);
      //  await redis.hsetAsync("websocket_online_user_list",user_id,"");
    }

    async isOnlineBySocketId(socket_id){

        return (await redis.hexistsAsync('websocket_online_user_socket_id',socket_id))==1;
    }
    async isOnlineByUserId(user_id){

        return (await redis.hexistsAsync('websocket_online_user_info',user_id))==1;
    }
    async getOperatorByUserId(user_id){
        let val=await redis.hgetAsync("websocket_online_user_info",user_id);
        let operator=new Operator();
        if(!think.isEmpty(val)){
            try {
                let user_info=JSON.parse(val);
                operator=new Operator(user_info);

            }catch(e){}
        }
        return operator;
    }
    async getOperatorBySocketId(socket_id){
        let val=await redis.hgetAsync("websocket_online_user_socket_id",socket_id);
        let operator=new Operator();
        if(!think.isEmpty(val)){
            try {
                let user_info=JSON.parse(val);
                operator=new Operator(user_info);

            }catch(e){}
        }
        return operator;
    }
}