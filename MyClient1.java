import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
public class MyClient1
{
	Socket s;
	DataInputStream din;
	DataOutputStream dout;
	
	JFrame f;
	JTextArea jta,jta1;
	JButton jb;
	JScrollPane jsp,jsp1;
	public MyClient1()
	{
		try
		{
			s=new Socket("localhost",10);
			din=new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());
			
			System.out.println("enter client name");
			BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
			String s2=br1.readLine();
			f=new JFrame(s2);
			f.setLayout(null);
			f.setSize(500,500);
			jta=new JTextArea(50,50);
			jta1=new JTextArea(50,50);
			jb=new JButton("send");
			jb.setBounds(400,350,70,50);
			jb.addActionListener(this);
			jsp=new JScrollPane(jta);
			jsp1=new JScrollPane(jta1);
			jsp.setBounds(50,60,320,150);
			jsp1.setBounds(50,270,320,150);
			
			f.add(jsp);
			f.add(jsp1);
			f.add(jb);
			
			f.setVisible(true);
			
			clientChat();
		}catch(Exception e){System.out.println(e);}
	}
	public void clientChat()throws IOException
	{
		My m=new My(din);
		Thread t1=new Thread(m);
		t1.start();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s1;
		do
		{
			String client=jta1.getText();
			dout.writeUTF(client);
			dout.flush();
		}while(!client.equals("stop"));
	}
	public static void main(String args[])
	{
		new MyClient1();
	}
}