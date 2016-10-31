package Server;

/*1026사용 서버*/

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame  {

	JPanel contentPane;
	InetAddress addr = null;
	JTextField textField;
	JTextField textField_1;
	JTextField textField_2;
	ObjectOutputStream output;
	ObjectInputStream input;
	JTextArea textArea;
	JButton btnNewButton;
	HashMap<String, DataOutputStream> clients;
	private ServerSocket ServerSocket = null;


	//file
	private static final int DEFAULT_BUFFER_SIZE = 1024;
	private static final String CONFIGURATION_FILENAME = "conf/file_sending_service.properties";
	private static final String KEY_FILENAME_TO_SEND = "FILENAME_TO_SEND";
	String name ;
	JFileChooser fc;
	FileInputStream in;
	DataOutputStream dout;
	DataInputStream din;
	int i;


	private boolean running = false;

	private String getFilenameToSendFromConfiguration() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(CONFIGURATION_FILENAME));
			return properties.getProperty(KEY_FILENAME_TO_SEND);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Server() {
		super("SERVER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		textArea = new JTextArea();

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("\uD3EC\uD2B8\uBC88\uD638 : ");
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setText("6868");
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("ip \uC8FC\uC18C : ");
		panel.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		panel.add(textField_1);
		textField_1.setText("192.168.0.13");
		textField_1.setColumns(10);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JLabel lblNewLabel_3 = new JLabel("\uC6B4\uC1A1\uC7A5 \uBC88\uD638 : ");
		panel_1.add(lblNewLabel_3);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		panel_1.add(textField_2);
		textField_2.setColumns(10);

		btnNewButton = new JButton("QR\uCF54\uB4DC \uC804\uC1A1");

		panel_1.add(btnNewButton);
		textArea.setEditable(false);

		contentPane.add(textArea, BorderLayout.CENTER);
		setSize(500,400);

		// 연결부 hashmap 생성자(Key, value) 선언
		clients = new HashMap<String, DataOutputStream>();
		// clients 동기화
		Collections.synchronizedMap(clients);
		show();
	}


	public void runServer() {
		// Port 값은 편의를위해 5001로 고정 (Random값으로 변경가능)
		int port = 5001;
		Socket socket = null;

		try {
			// 서버소켓 생성후 while문으로 진입하여 accept(대기)하고 접속시 ip주소를 획득하고 출력한뒤
			// MultiThread를 생성한다.
			ServerSocket = new ServerSocket(port); 
			textArea.setText("접속대기중\n");
			System.out.println("접속대기중");
			while (true) {
				socket = ServerSocket.accept();
				InetAddress ip = socket.getInetAddress();
				System.out.println(ip + "  connected");
				textArea.append(ip + "  connected\n");
				new MultiThread(socket).start();
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}
	class MultiThread extends Thread {

		Socket socket = null;

		String mac = null;
		String msg = null;

		DataInputStream input;
		DataOutputStream output;

		public MultiThread(Socket socket) {
			this.socket = socket;
			try {
				// 객체를 주고받을 Stream생성자를 선언한다.
				input = new DataInputStream(socket.getInputStream());
				output = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
			}
		}


		public void run() {

			try {
				// 접속된후 바로 Mac 주소를 받아와 출력하고 clients에 정보를 넘겨주고 클라이언트에게 mac주소를보낸다.
				mac = input.readUTF();
				textArea.append("Mac address : " + mac+"\n");
				System.out.println("Mac address : " + mac);
				clients.put(mac, output);
				sendMsg(mac + "   접속");

				// 그후에 채팅메세지수신시
				while (input != null) {
					try {
						String temp = input.readUTF();
						sendMsg(temp);
						textArea.append(temp+"\n");
						System.out.println(temp);
					} catch (IOException e) {
						sendMsg("No massege");
						break;
					}
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}

		// 메세지수신후 클라이언트에게 Return 할 sendMsg 메소드
		private void sendMsg(String msg) {

			// clients의 Key값을 받아서 String 배열로선언
			Iterator<String> it = clients.keySet().iterator();

			// Return 할 key값이 없을때까지
			while (it.hasNext()) {
				try {
					OutputStream dos = clients.get(it.next());
					// System.out.println(msg);
					DataOutputStream output = new DataOutputStream(dos);
					output.writeUTF(msg);

				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}
	 public static void main(String[] args) {
	        new Server().runServer();
	    }
}


