package cn.llyong;

import cn.llyong.bo.Message;
import cn.llyong.netty.im.client.core.ImConnection;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

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
        ChannelFuture channelFuture = new ImConnection().connection2("localhost", 5678);
        Channel channel = channelFuture.channel();
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    ByteBuf buffer = Unpooled.buffer(1024);
                    Channel channel1 = future.channel();
                }
            }
        });

        System.out.println("++++++++++++++++++++++");
        System.out.println(channel);
        System.out.println("++++++++++++++++++++++");
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
