import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class serverchat extends JFrame {

	private JPanel contentPane;
	private JTextField t2;
	private JButton b2;
	static JTextArea a2;
	static ServerSocket ss;
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
			serverchat frame = new serverchat();
			frame.setVisible(true);
			
			ss=new ServerSocket(9898);
			s=ss.accept();
			
			System.out.println("connecting...");
			in= new DataInputStream(s.getInputStream());
			while(!str.equals("exit"))
			{
            str=(String)in.readUTF();
			a2.append("\nClient:"+str+"\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public serverchat() {
		setTitle("SERVER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		a2 = new JTextArea();
		a2.setBounds(10, 11, 414, 197);
		contentPane.add(a2);
		
		t2 = new JTextField();
		t2.setBounds(10, 219, 306, 31);
		contentPane.add(t2);
		t2.setColumns(10);
		
		b2 = new JButton("Send");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String str=t2.getText();
				try
				{
					out=new DataOutputStream(s.getOutputStream());
					out.writeUTF(str);
					t2.setText("");
					a2.append("\nMe:"+str+"\n");
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		b2.setBounds(326, 219, 98, 31);
		contentPane.add(b2);
	}
}
