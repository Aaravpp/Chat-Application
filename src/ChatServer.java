

import java.io.*;
import java.net.*;

public class ChatServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6001);
            System.out.println("Server started, waiting for clients...");
            Socket socket = serverSocket.accept();  // Wait for client
            
            System.out.println("Client connected.");

            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String msg = din.readUTF();
                System.out.println("Client: " + msg);
                // Echo message or handle it
                dout.writeUTF("Server received: " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
