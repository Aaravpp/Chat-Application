

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class Server implements ActionListener{
	
	JTextField text;
	static JPanel a1;
	static Box vertical = Box.createVerticalBox();
	static JFrame f = new JFrame(); 
	static DataOutputStream dout;
	
	public Server() {
		
		f.setLayout(null);
		
		JPanel p1 = new JPanel();
		p1.setBackground(new Color(7,94 ,84));
		p1.setBounds(0, 0, 450, 70);
		p1.setLayout(null);
		f.add(p1);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
		Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2); 
		JLabel back = new JLabel(i3);
		back.setBounds(5, 20, 25, 25);
		p1.add(back);
		
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				f.setVisible(false);    
			}
		});
		
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
		Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		JLabel profile = new JLabel(i6);
		profile.setBounds(40, 10, 50, 50);
		p1.add(profile);
		
		
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
		Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i9 = new ImageIcon(i8);
		JLabel video = new JLabel(i9);
		video.setBounds(300, 20, 30, 30);
		p1.add(video);
		
		ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
		Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
		ImageIcon i12 = new ImageIcon(i11);
		JLabel phone = new JLabel(i12);
		phone.setBounds(360, 20, 35, 30);
		p1.add(phone);
		
		ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
		Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
		ImageIcon i15 = new ImageIcon(i14);
		JLabel more = new JLabel(i15);
		more.setBounds(420, 20, 10, 25);
		p1.add(more);
		
		JLabel name = new JLabel("Dhamu");
		name.setBounds(110, 15, 100, 18);
		name.setForeground(Color.white);
		name.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
		p1.add(name);
		
		JLabel status = new JLabel("online");
		status.setBounds(110, 35, 100, 18);
		status.setForeground(Color.white);
		status.setFont(new Font("SAN_SERIF", Font.BOLD, 13));
		p1.add(status);
		
		a1 = new JPanel();
		a1.setBounds(5, 75, 440, 570);
		f.add(a1);
		
		text = new JTextField();
		text.setBounds(5, 655, 310, 40);
		text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		f.add(text);
		
		JButton send = new JButton("Send");
		send.setBounds(320, 655, 123, 40);
		send.setBackground(new Color(7 , 94 , 84));
		send.addActionListener(this);
		send.setForeground(Color.white);
		f.add(send);

		
		f.setSize(450 , 700);
		f.setLocation(200 , 50);
		f.setUndecorated(true);
		f.getContentPane().setBackground(Color.white);
		
		
		f.setVisible(true);
		 
	}
	
	public static void main(String[] args) {
		
		new Server();
		
		try {
			Socket s = new Socket("127.0.0.1", 6001);
			DataInputStream din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			
			while(true) {
				a1.setLayout(new BorderLayout());
				String msg = din.readUTF();
				JPanel panel = formatLabel(msg);
				
				JPanel left = new JPanel(new BorderLayout());
				left.add(panel, BorderLayout.LINE_START);
				vertical.add(left);
				
				vertical.add(Box.createVerticalStrut(15));
				a1.add(vertical, BorderLayout.PAGE_START);
								
				f.validate();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		try {

			String out = text.getText();
		
			JLabel output = new JLabel(out);
		
			JPanel p2 = formatLabel(out);
		
			a1.setLayout(new BorderLayout());
		
			JPanel right = new JPanel(new BorderLayout());
			right.add(p2, BorderLayout.LINE_END);
			vertical.add(right);  
			vertical.add(Box.createVerticalStrut(15));
		
			a1.add(vertical, BorderLayout.PAGE_START);
		
			dout.writeUTF(out);
		
		
			text.setText("");
		
			f.repaint();
			f.invalidate();
			f.validate();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	public static JPanel formatLabel(String out) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p><html>");
		output.setFont(new Font("Tahoma", Font.PLAIN, 16));
		output.setBackground(new Color(37,211 ,102));
//		output.setForeground(Color.white);
		output.setOpaque(true);
		output.setBorder(new EmptyBorder(15, 15, 15, 50));
		
		panel.add(output);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
		JLabel time = new JLabel();
		time.setText(sdf.format(cal.getTime()));
		
		panel.add(time);
		
		
		return panel;
		
	}
	 

}








//import java.io.*;
//import java.net.*;
//import java.util.concurrent.CopyOnWriteArrayList;
//import java.util.Scanner;
//
//public class Server {
//    private static final int PORT = 12346;
//    private static CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();
//
//    public static void main(String[] args) {
//        try {
//            ServerSocket serverSocket = new ServerSocket(PORT);
//            System.out.println("Server is running and waiting for connections...");
//
//            // Thread to handle server admin input
//            new Thread(() -> {
//                Scanner scanner = new Scanner(System.in);
//                while (true) {
//                    String serverMessage = scanner.nextLine();
//                    broadcast("[Server]: " + serverMessage, null);
//                }
//            }).start();
//
//            // Accept incoming connections
//            while (true) {
//                Socket clientSocket = serverSocket.accept();
//                System.out.println("New client connected: " + clientSocket);
//
//                // Create a new client handler for the connected client
//                ClientHandler clientHandler = new ClientHandler(clientSocket);
//                clients.add(clientHandler);
//                new Thread(clientHandler).start();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Broadcast a message to all clients
//    public static void broadcast(String message, ClientHandler sender) {
//        for (ClientHandler client : clients) {
//            if (client != sender) {
//                client.sendMessage(message);
//            }
//        }
//    }
//
//    // Internal class to handle client connections
//    private static class ClientHandler implements Runnable {
//        private Socket clientSocket;
//        private PrintWriter out;
//        private BufferedReader in;
//        private String username;
//
//        public ClientHandler(Socket socket) {
//            this.clientSocket = socket;
//
//            try {
//                out = new PrintWriter(clientSocket.getOutputStream(), true);
//                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void run() {
//            try {
//                // Get the username from the client
//                out.println("Enter your username:");
//                username = in.readLine();
//                System.out.println("User " + username + " connected.");
//                out.println("Welcome to the chat, " + username + "!");
//                out.println("Type Your Message");
//
//                String inputLine;
//                while ((inputLine = in.readLine()) != null) {
//                    System.out.println("[" + username + "]: " + inputLine);
//                    broadcast("[" + username + "]: " + inputLine, this);
//                }
//
//                // Remove the client handler from the list
//                clients.remove(this);
//                System.out.println("User " + username + " disconnected.");
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    in.close();
//                    out.close();
//                    clientSocket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        public void sendMessage(String message) {
//            out.println(message);
//        }
//    }
//}
