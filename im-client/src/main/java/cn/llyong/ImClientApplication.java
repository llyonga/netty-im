package cn.llyong;

import cn.llyong.bo.Message;
import cn.llyong.netty.im.client.core.ImConnection;
import io.netty.channel.Channel;

import java.time.Instant;
import java.util.UUID;

/**
 * @description: 
 * @author: lvyong
 * @date: 2019/9/25 
 * @time: 10:27 上午
 * @version: 1.0
 */
//@SpringBootApplication
public class ImClientApplication {

    public static void main(String[] args) throws Exception{
        Channel channel = new ImConnection().connection("localhost", 5678);
        Message message;
        while (true) {
//            channel.writeAndFlush(Unpooled.copiedBuffer((Instant.now().toEpochMilli()+",hello server!").getBytes("UTF-8")));

            message = new Message();
            message.setMsgId(UUID.randomUUID().toString().replace("-", ""));
            message.setContent(Instant.now().toEpochMilli()+",hello server!");
            channel.writeAndFlush(message);
            Thread.sleep(2000);
        }
//        SpringApplication.run(ImClientApplication.class, args);
    }

}
