import java.io.*;
import java.net.*;
import java.util.*;
public class MyServer 
{
	ArrayList al=new ArrayList();
	ServerSocket ss;
	Socket s;
	public MyServer()
	{
		try
		{
			ss=new ServerSocket(10);
			while(true)
			{
				s=ss.accept();
				System.out.println("client connected");
				al.add(s);
				Runnable r=new MyThread(s,al);
				Thread t=new Thread(r);
				t.start();
			}
		}
		catch(Exception e)
		{}
	}
	public static void main(String args[])
	{
		new MyServer();
	}
}