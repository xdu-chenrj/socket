package org.socket;

import java.util.Scanner;

public class ClientB {

    public static void main(String[] args) {
        HelloClient clientB = new HelloClient();
        Message message = (Message) clientB.send(new Message("clientB"), "127.0.0.1", 8888);
        System.out.println("client receive message：" + message.getConnect());
    }

}
