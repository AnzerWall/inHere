package com.inHere.redis;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;

/**
 * 订阅发布监听
 */
@Component
public class PubSubListener extends JedisPubSub {

    Logger log = Logger.getLogger(getClass());

    // 取得订阅的消息后的处理
    public void onMessage(String channel, String message) {
        log.info("onMessage: " + channel + "=" + message);
    }

    // 初始化订阅时候的处理
    public void onSubscribe(String channel, int subscribedChannels) {
        log.info("onSubscribe: " + channel + "=" + subscribedChannels);
    }

    // 取消订阅时候的处理
    public void onUnsubscribe(String channel, int subscribedChannels) {
        log.info("onUnsubscribe: " + channel + "=" + subscribedChannels);
    }

    // 初始化按表达式的方式订阅时候的处理
    public void onPSubscribe(String pattern, int subscribedChannels) {
        log.info("onPSubscribe: " + pattern + "=" + subscribedChannels);
    }

    // 取消按表达式的方式订阅时候的处理
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        log.info("onPUnsubscribe: " + pattern + "=" + subscribedChannels);
    }

    // 取得按表达式的方式订阅的消息后的处理
    public void onPMessage(String pattern, String channel, String message) {
        log.info("onPMessage: " + pattern + "=" + channel + "=" + message);
    }

}
