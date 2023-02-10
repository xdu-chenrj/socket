package org.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HelloClient {
    private static final Logger logger = LoggerFactory.getLogger(HelloClient.class);
    public Object send(Message message, String host, int port) {
        // 创建socket对象，并制定服务所在地址和端口号
        try (Socket socket = new Socket(host, port)){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            // 通过输出流向服务发送请求信息
            objectOutputStream.writeObject(message);
            logger.info("client send message: " + message.getConnect());
            // 通过输入流获取服务器响应信息
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("occur exception:", e);
        }
        return null;
    }

    public static void main(String[] args) {
        HelloClient helloClient = new HelloClient();
        Message message = (Message) helloClient.send(new Message("hello"), "127.0.0.1", 8888);
        System.out.println("client receive message：" + message.getConnect());
    }
}
