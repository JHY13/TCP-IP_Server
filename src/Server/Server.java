package Server;

/*1026��� ����*/

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

		// ����� hashmap ������(Key, value) ����
		clients = new HashMap<String, DataOutputStream>();
		// clients ����ȭ
		Collections.synchronizedMap(clients);
		show();
	}


	public void runServer() {
		// Port ���� ���Ǹ����� 5001�� ���� (Random������ ���氡��)
		int port = 5001;
		Socket socket = null;

		try {
			// �������� ������ while������ �����Ͽ� accept(���)�ϰ� ���ӽ� ip�ּҸ� ȹ���ϰ� ����ѵ�
			// MultiThread�� �����Ѵ�.
			ServerSocket = new ServerSocket(port); 
			textArea.setText("���Ӵ����\n");
			System.out.println("���Ӵ����");
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
				// ��ü�� �ְ���� Stream�����ڸ� �����Ѵ�.
				input = new DataInputStream(socket.getInputStream());
				output = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
			}
		}


		public void run() {

			try {
				// ���ӵ��� �ٷ� Mac �ּҸ� �޾ƿ� ����ϰ� clients�� ������ �Ѱ��ְ� Ŭ���̾�Ʈ���� mac�ּҸ�������.
				mac = input.readUTF();
				textArea.append("Mac address : " + mac+"\n");
				System.out.println("Mac address : " + mac);
				clients.put(mac, output);
				sendMsg(mac + "   ����");

				// ���Ŀ� ä�ø޼������Ž�
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

		// �޼��������� Ŭ���̾�Ʈ���� Return �� sendMsg �޼ҵ�
		private void sendMsg(String msg) {

			// clients�� Key���� �޾Ƽ� String �迭�μ���
			Iterator<String> it = clients.keySet().iterator();

			// Return �� key���� ����������
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


