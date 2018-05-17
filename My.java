import java.io.*;
class My implements Runnable
{
	DataInputStream din;
	My(DataInputStream din)
	{
		this.din=din;
	}
	public void run()
	{
		String s2=" ";
		do
		{
			try
			{
				s2=din.readUTF();
				System.out.println(s2);
			}catch(Exception w){}
		}while(!s2.equals("stop"));
	}
}