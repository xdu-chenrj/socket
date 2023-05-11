package org.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.*;


public class HelloServer {
    private static final Logger logger = LoggerFactory.getLogger(HelloServer.class);

    public void start(int port) {
        // 创建ServerSocket对象并绑定一个端口
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket;
            // 通过accept方法监听客户端请求
            while ((socket = serverSocket.accept()) != null) {
                logger.info("connect success");
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) objectInputStream.readObject();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                logger.info("server receive " + message.getConnect());
                message.setConnect(message.getConnect() + " hello");
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();
            }

        } catch (IOException | ClassNotFoundException e) {
            logger.error("occur exception ", e);
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            // 创建 socket 连接
            HelloServer helloServer = new HelloServer();
            helloServer.start(8888);
        });
        thread.start();
    }
}
