package org.socket;

import java.util.Scanner;

public class ClientA {
    public static void main(String[] args) {
        HelloClient clientA = new HelloClient();
        Message message = (Message) clientA.send(new Message("clientA"), "127.0.0.1", 8888);
        System.out.println("client receive message：" + message.getConnect());
    }
}
