package cn.llyong.netty.im.client.handler;

import cn.llyong.bo.Message;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author: lvyong
 * @date: 2019-09-26
 * @time: 11:02 上午
 * @version: 1.0
 */
public class ClientMarshallingHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
        System.out.println("客户端收到回复：" + message.getContent());
        Channel channel = ctx.channel();
        System.out.println("**********************");
        System.out.println(channel);
        System.out.println("**********************");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
