import java.io.*;
import java.net.*;
import java.util.*;
class MyThread implements Runnable
{
	Socket s;
	ArrayList al;
	MyThread(Socket s,ArrayList al)
	{
		this.s=s;
		this.al=al;
	}
	public void run()
	{
		String s1;
		try
		{
			DataInputStream din=new DataInputStream(s.getInputStream());
			do
			{
				s1=din.readUTF();
				System.out.println(s1);
				if(!s1.equals("stop"))
					tellEveryone(s1);
				else
				{
					DataOutputStream dout=new DataOutputStream(s.getOutputStream());
					dout.writeUTF(s1);
					dout.flush();
					al.remove(s);
				}
			}while(!s1.equals("stop"));
		}
		catch(Exception e){}
	}
	public void tellEveryone(String s1)
	{
		Iterator i=al.iterator();
		while(i.hasNext())
		{
			try
			{
				Socket sc=(Socket)i.next();
				DataOutputStream dout=new DataOutputStream(sc.getOutputStream());
				dout.writeUTF(s1);
				dout.flush();
				System.out.println("client");
			}
			catch(Exception e){}
		}
	}
}