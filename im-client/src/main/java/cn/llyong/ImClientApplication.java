package cn.llyong;

import cn.llyong.netty.im.client.core.ImConnection;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

/**
 * @description: 
 * @author: lvyong
 * @date: 2019/9/25 
 * @time: 10:27 上午
 * @version: 1.0
 */
//@SpringBootApplication
public class ImClientApplication {

    public static void main(String[] args) {
        Channel channel = new ImConnection().connection("localhost", 5678);
        channel.writeAndFlush("你好啊，我是渣渣辉！");
//        SpringApplication.run(ImClientApplication.class, args);
    }

}
