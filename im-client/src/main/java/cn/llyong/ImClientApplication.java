package cn.llyong;

import cn.llyong.netty.im.client.ImConnection;
import io.netty.channel.Channel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: 
 * @author: lvyong
 * @date: 2019/9/25 
 * @time: 10:27 上午
 * @version: 1.0
 */
@SpringBootApplication
public class ImClientApplication {

    public static void main(String[] args) {
        new Thread(() ->{
            Channel channel = new ImConnection().connection("localhost", 5678);
        }).start();
        SpringApplication.run(ImClientApplication.class, args);
    }

}
