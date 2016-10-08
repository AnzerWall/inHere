package com.inHere.redis;

import com.alibaba.fastjson.JSONArray;
import com.inHere.entity.Roles;
import com.inHere.entity.Token;
import com.inHere.entity.User;
import com.inHere.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Map;

/**
 * token数据库操作类
 *
 * @author lwh
 */
@Component
public class TokenManage {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    /**
     * @param user 指定用户的id
     * @return 生成的token
     */
    public Token createToken(User user) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        // 校验登录用户是否已存在,存在则删除
        this.checkUserDel(user.getUserId());

        String tokenStr = securityService.createToken(user.getUserId());
        JSONArray roles = new JSONArray(); // 赋予角色
        JSONArray permissions = new JSONArray();// 赋予权限
        if (user.getRoles() != null) {
            for (Roles tmp : user.getRoles()) {
                roles.add(tmp.getRole());
                permissions.addAll(tmp.getPermissions());
            }
        }
        Token token = new Token(tokenStr, user.getUserId(), user.getSchoolId(), roles, permissions);
        jedis.hmset("token:" + token.getKey(), token.toMap()); // token绑定用户信息
        jedis.set("user:" + user.getUserId(), token.getKey()); // 用户绑定token，保证单一登录
        // 过期时间12个小时
        jedis.expire("token:" + token.getKey(), 12 * 60 * 60);
        jedis.expire("user:" + user.getUserId(), 12 * 60 * 60);
        jedis.close();
        return token;
    }

    /**
     * 检查用户是否存在,存在则删除
     *
     * @param user_id
     * @return
     */
    public void checkUserDel(String user_id) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        String token = jedis.get("user:" + user_id);
        if (token != null) {
            jedis.del("token:" + token);
            jedis.del("user:" + user_id);
        }
        jedis.close();
    }

    /**
     * 从字符串中解析token
     *
     * @param tokenStr 加密后的字符串
     * @return
     */
    public Token getToken(String tokenStr) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        Map<String, String> userMap = jedis.hgetAll("token:" + tokenStr);
        if (userMap.size() > 1) {
            Token token = new Token();
            token.setKey(tokenStr);
            token.setUser_id(userMap.get("user_id"));
            token.setSchool_id(Integer.parseInt(userMap.get("school_id")));
            token.setRoles(JSONArray.parseArray(userMap.get("roles")));
            token.setPermissions(JSONArray.parseArray(userMap.get("permissions")));
            return token;
        }
        jedis.close();
        return null;
    }

    /**
     * TODO 用户退出登录，清除token
     *
     * @param token 登录用户的id
     */
    public void deleteToken(Token token) {

    }

}
