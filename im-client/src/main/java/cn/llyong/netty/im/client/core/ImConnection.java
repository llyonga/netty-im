package cn.llyong.netty.im.client.core;

import cn.llyong.netty.im.client.handler.ClientStringHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

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

//    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
                            channel.pipeline().addLast("decoder", new StringDecoder());
                            channel.pipeline().addLast("encoder", new StringEncoder());
                            channel.pipeline().addLast(new ClientStringHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect(host, port);
            channel = future.channel();
        } catch (Exception e) {
            e.printStackTrace();
//            logger.error("", e);
        } finally {
//            workGroup.shutdownGracefully();
        }

    }


}
