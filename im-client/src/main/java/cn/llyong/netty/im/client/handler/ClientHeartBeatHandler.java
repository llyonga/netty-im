package cn.llyong.netty.im.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @description: 服务端心跳handler
 * @author: lvyong
 * @date: 2019-09-26
 * @time: 12:36 下午
 * @version: 1.0
 */
public class ClientHeartBeatHandler extends ChannelInboundHandlerAdapter {
    /**
     * 丢失次数
     */
    private int lossConnectCount = 0;

    /**
     * 最大丢失心跳次数
     */
    private int maxLossConnectCount = 3;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object o) throws Exception {
        System.out.println("客户端循环心跳监测发送: "+ LocalDateTime.now());
        if (o instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) o;
            if (event.state()== IdleState.READER_IDLE){
                lossConnectCount++;
                if (lossConnectCount >= maxLossConnectCount){
                    System.out.println("关闭这个不活跃通道！");
                    ctx.channel().close();
                }
            }
        }else {
            super.userEventTriggered(ctx, o);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        lossConnectCount = 0;
        System.out.println("client says: "+msg.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
