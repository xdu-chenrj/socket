package org.socket;

public class ClientC {
    public static void main(String[] args) {
        HelloClient clientB = new HelloClient();
        Message message = (Message) clientB.send(new Message("clientC"), "127.0.0.1", 9999);
        System.out.println("client receive message：" + message.getConnect());
    }
}
