package cn.llyong;

import cn.llyong.netty.im.server.core.ImServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImServerApplication {

    public static void main(String[] args) {
        new Thread(() -> {
            new ImServer().run(5678);
        }).start();

        SpringApplication.run(ImServerApplication.class, args);
    }

}
