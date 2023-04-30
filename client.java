import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class client extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private JButton b1;
	static JTextArea a1;
	static Socket s;
	static int port=9898;
	static String ip="localhost";
	static DataOutputStream out;
	static DataInputStream in;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
		try {
			String str="";
			client frame = new client();
			frame.setVisible(true);
			
			s=new Socket(ip,port);
			System.out.println("connecting...");
			in= new DataInputStream(s.getInputStream());
			
			while(!str.equals("exit"))
			{
			 str=(String)in.readUTF();
			a1.append("\nServer: "+str+"\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public client() {
		setTitle("CLIENT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		a1 = new JTextArea();
		a1.setBounds(10, 11, 414, 197);
		contentPane.add(a1);
		
		t1 = new JTextField();
		t1.setBounds(10, 219, 306, 31);
		contentPane.add(t1);
		t1.setColumns(10);
		
		b1 = new JButton("Send");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String str=t1.getText();
				try
				{
					out=new DataOutputStream(s.getOutputStream());
					out.writeUTF(str);
					t1.setText("");
					a1.append("\nMe:"+str+"\n");
				}
				catch(IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		b1.setBounds(326, 219, 98, 31);
		contentPane.add(b1);
	}
}
