package cn.llyong.netty.im.server.core;

import cn.llyong.marshalling.MarshallingCodeFactory;
import cn.llyong.netty.im.server.handler.ServerMarshallingHandler;
import cn.llyong.netty.im.server.handler.ServerStringHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author: lvyong
 * @date: 2019-09-25
 * @time: 10:27 上午
 * @version: 1.0
 */
public class ImServer {

    public void run(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .handler(new ChannelInitializer<ServerSocketChannel>() {
                    @Override
                    protected void initChannel(ServerSocketChannel serverSocketChannel) throws Exception {
                        System.out.println("服务端启动了。。。。。。");
                    }
                })
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
//                        channel.pipeline().addLast(new StringDecoder());
//                        channel.pipeline().addLast(new StringEncoder());
//                        channel.pipeline().addLast(new ServerStringHandler());

                        // 添加Jboss的序列化，编解码工具
                        channel.pipeline().addLast(MarshallingCodeFactory.buildMarshallingEncoder());
                        channel.pipeline().addLast(MarshallingCodeFactory.buildMarshallingDecoder());
                        channel.pipeline().addLast(new ServerMarshallingHandler());
                    }
                });
        try {
            ChannelFuture future = serverBootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
