package cn.llyong.netty.im.server.handler;

import cn.llyong.bo.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.Instant;
import java.util.UUID;

/**
 * @description: 
 * @author: lvyong
 * @date: 2019/9/26 
 * @time: 11:06 上午
 * @version: 1.0
 */
public class ServerMarshallingHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
        System.out.println("服务端收到消息：" + message.getContent());
        message = new Message();
        message.setMsgId(UUID.randomUUID().toString().replace("-",""));
        message.setContent(Instant.now().toEpochMilli() + "，我已经收到你的消息！");
        ctx.channel().writeAndFlush(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}