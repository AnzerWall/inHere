package com.inHere.redis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inHere.entity.Token;
import com.inHere.entity.User;
import com.inHere.service.SecurityService;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * token数据库操作类
 * 
 * @author lwh
 *
 */
@Component
public class TokenManage {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private ShardedJedisPool shardedJedisPool;

	/**
	 * TODO redis连接回收问题
     * 创建一个token关联上指定用户
     * @param userId 指定用户的id
     * @return 生成的token
     */
	public Token createToken(User user) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		String tokenStr = securityService.createToken(user.getUserId());
		Token token = new Token(tokenStr, user.getUserId(), user.getSchoolId(), user.getRoleId());
		jedis.hmset(token.getKey(), token.toMap());
		return token;
	}

    /**
     * 检查token是否有效
     * @param model token
     * @return 是否有效
     */
    public boolean checkToken(Token model){
    	return true;
    }
    
    /**
     * 从字符串中解析token
     * @param authentication 加密后的字符串
     * @return
     */
    public Token getToken(String tokenStr){
    	ShardedJedis jedis = shardedJedisPool.getResource();
    	Map<String, String> userMap = jedis.hgetAll("token:"+tokenStr);
    	return new Token();
    }

    /**
     * 清除token
     * @param userId 登录用户的id
     */
    public void deleteToken(long userId){
    	
    }

}
