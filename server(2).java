package hassaan.project;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
	
	public static PointerInfo a = MouseInfo.getPointerInfo();
	
	
	public static void main(String arg[]) throws IOException, InterruptedException
	{
		String clientSentence;
		  String capitalizedSentence;
		  ServerSocket welcomeSocket = new ServerSocket(5600);

		  while (true) {
			  
		   Socket connectionSocket = welcomeSocket.accept();
		   BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		   DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		   PointerInfo b = MouseInfo.getPointerInfo();
		   while (true)
		   {
			   a = MouseInfo.getPointerInfo();
			   if (a.getLocation().getY() != b.getLocation().getY() && b.getLocation().getX() != a.getLocation().getX())
			   {
				   System.out.println(a.getLocation().getY() + " b : " +b.getLocation().getY());
				   b = a;
		   
		   Point c = a.getLocation();
			int x = (int) c.getX();
			int y = (int) c.getY();
			//System.out.println(x + " " + y);
		   outToClient.writeBytes(x + " " + y +"\n");
		  // Thread.sleep(50);
		  }
		   }
		  }
	}
	
	
}
