package hassaan.project;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class client {
	
	public static void main(String args[]) throws IOException, IOException, InterruptedException, AWTException
	{
		Robot robot = new Robot();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// the screen height
		System.out.println(screenSize.getHeight());
		System.out.println(screenSize.getWidth());
	String sentence;
	  String modifiedSentence;
	  BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	  Socket clientSocket = new Socket("localhost", 6789);
	  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
	  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	 while (true)
	 {
	  sentence = inFromServer.readLine();
	  String[] animals = sentence.split(" ");
	  robot.mouseMove(Integer.parseInt(animals[0]), Integer.parseInt(animals[1]) + 100);
	  System.out.println(sentence);
	  Thread.sleep(250);
	 }

	}
}
