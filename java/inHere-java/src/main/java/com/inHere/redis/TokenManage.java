package com.inHere.redis;

import com.alibaba.fastjson.JSONArray;
import com.inHere.entity.Roles;
import com.inHere.entity.Token;
import com.inHere.entity.User;
import com.inHere.service.SecurityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * token数据库操作类
 *
 * @author lwh
 */
@Component
public class TokenManage {

    private static final String CHANNEL_FOLLOW = "follow";
    private static boolean IsSubscribe = false;

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private SecurityService securityService;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private PubSubListener pubSubListener;

    /**
     * 订阅一个频道
     */
    public void subscribe() {
        if (!IsSubscribe) {
            // 开一个线程进行订阅
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = jedisPool.getResource();
                    try {
                        TokenManage.IsSubscribe = true;
                        jedis.subscribe(pubSubListener, TokenManage.CHANNEL_FOLLOW);
                    } catch (Exception e) {
                        TokenManage.IsSubscribe = false;
                        log.error("subscribe error", e);
                    }
                    jedis.close();
                }
            }).start();
        }
    }

    /**
     * @param user 指定用户的id
     * @return 生成的token
     */
    public Token createToken(User user) {
        Jedis jedis = jedisPool.getResource();
        Token token = checkUserReturnToken(user);
        // 校验登录用户是否已存在,存在则返回token
        if (token != null) {
            return token;
        }

        String tokenStr = securityService.createToken(user.getUserId());
        JSONArray roles = new JSONArray(); // 赋予角色
        JSONArray permissions = new JSONArray();// 赋予权限
        if (user.getRoles() != null) {
            for (Roles tmp : user.getRoles()) {
                roles.add(tmp.getRole());
                permissions.addAll(tmp.getPermissions());
            }
        }
        token = new Token(tokenStr, user.getUserId(), user.getSchoolId(), roles, permissions);
        jedis.hmset("token:" + token.getKey(), token.toMap()); // token绑定用户信息
        jedis.set("user:" + user.getUserId(), token.getKey()); // 用户绑定token，保证单一登录
        // 过期时间12个小时
        jedis.expire("token:" + token.getKey(), 12 * 60 * 60);
        jedis.expire("user:" + user.getUserId(), 12 * 60 * 60);
        jedis.close();
        return token;
    }

    /**
     * 后台token绑定redis
     */
    public void pushToken(User user, String tokenStr) {
        Jedis jedis = jedisPool.getResource();

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

    }

    /**
     * 检查用户是否存在,存在则返回token
     *
     * @param user
     * @return
     */
    public Token checkUserReturnToken(User user) {
        Jedis jedis = jedisPool.getResource();
        Token token = null;
        String tokenStr = jedis.get("user:" + user.getUserId());
        if (tokenStr != null) {
            Map<String, String> userMap = jedis.hgetAll("token:" + tokenStr);
            if (userMap.size() > 1) {
                token = new Token();
                token.setKey(tokenStr);
                token.setUser_id(userMap.get("user_id"));
                token.setSchool_id(Integer.parseInt(userMap.get("school_id")));
                token.setRoles(JSONArray.parseArray(userMap.get("roles")));
                token.setPermissions(JSONArray.parseArray(userMap.get("permissions")));
            }
        }
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
        Jedis jedis = jedisPool.getResource();
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
        Jedis jedis = jedisPool.getResource();
        Token token = null;
        Map<String, String> userMap = jedis.hgetAll("token:" + tokenStr);
        if (userMap.size() > 1) {
            token = new Token();
            token.setKey(tokenStr);
            token.setUser_id(userMap.get("user_id"));
            token.setSchool_id(Integer.parseInt(userMap.get("school_id")));
            token.setRoles(JSONArray.parseArray(userMap.get("roles")));
            token.setPermissions(JSONArray.parseArray(userMap.get("permissions")));
        }
        jedis.close();
        return token;
    }

    /**
     * 发布一条提醒信息，把提醒信息加入信息队列
     */
    public void publish(String msg) {
        // 先订阅频道
        this.subscribe();
        Jedis jedis = jedisPool.getResource();
        jedis.lpush("tip_message_list", msg);
        jedis.publish(CHANNEL_FOLLOW, "tip_message_list");
        jedis.close();
    }

    /**
     * @param token 登录用户的id
     */
    public void deleteToken(Token token) {
        checkUserDel(token.getUser_id());
    }

}
