import java.io.*;
import java.net.*;

public class ServerListener {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6001);
            System.out.println("Server started. Waiting for client...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String msg = din.readUTF();
                System.out.println("Client says: " + msg);
                // You can add logic to broadcast this message or respond
                dout.writeUTF("Message received: " + msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
