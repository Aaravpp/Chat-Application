import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Server extends JFrame implements ActionListener{
	
	public Server() {
		
		setLayout(null);
		
		JPanel p1 = new JPanel();
		p1.setBackground(new Color(7,94 ,84));
		p1.setBounds(0, 0, 450, 70);
		p1.setLayout(null);
		add(p1);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
		Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2); 
		JLabel back = new JLabel(i3);
		back.setBounds(5, 20, 25, 25);
		p1.add(back);
		
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});

		
		setSize(450 , 650);
		setLocation(200 , 50);
		getContentPane().setBackground(Color.white);
		
		
		setVisible(true);
		 
	}
	
	public static void main(String[] args) {
		
		new Server();
		
	}

	public void actionPerformed(ActionEvent e) {
		
	}

}
