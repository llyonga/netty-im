package cn.llyong.netty.im.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author: lvyong
 * @date: 2019-09-25
 * @time: 10:05 上午
 * @version: 1.0
 */
public class ImConnection {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Channel channel;

    public Channel connection(String host, int port) {
        doConnection(host, port);
        return this.channel;
    }

    private void doConnection(String host, int port) {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast();
                        }
                    });
            ChannelFuture future = bootstrap.connect();
            channel = future.channel();
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            workGroup.shutdownGracefully();
        }

    }


}
