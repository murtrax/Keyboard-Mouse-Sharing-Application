import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import static java.awt.event.KeyEvent.*;
public class Key extends Thread{
	
	Socket clickSocket;
    DataOutputStream outToClient;
    BufferedReader inFromServer;

    private Robot robot;
    private tagManager tagManager;
    private String sen, tag="";


    public Key(Socket clickSocket, DataOutputStream outToClient, BufferedReader inFromServer) throws IOException, AWTException {
        this.clickSocket = clickSocket;
        this.outToClient = outToClient;
        this.inFromServer = inFromServer;

        robot = new Robot();
        tagManager= new tagManager();
    }
	
	
	public void run()
	{
		while(true)
		{

		try { sen = inFromServer.readLine(); } catch (IOException e) { e.printStackTrace(); }
		char character = sen.charAt(0);
		
		if (sen.equals("Enter"))
        {
        	robot.keyPress(VK_ENTER);
        }
		else if (sen.equals("Backspace"))
        {
        	robot.keyPress(VK_BACK_SPACE);
        }
		
		else if (sen.equals("Space"))
		{
			robot.keyPress(VK_SPACE);
		}
		
		else if (sen.equals("Escape"))
		{
			robot.keyPress(VK_ESCAPE);
		}
		else if (sen.equals("Up"))
		{
			robot.keyPress(VK_UP);
		}
		
		else if (sen.equals("Down"))
		{
			robot.keyPress(VK_DOWN);
		}
		
		else if (sen.equals("Right"))
		{
			robot.keyPress(VK_RIGHT);
		}
		
		else if (sen.equals("Left"))
		{
			robot.keyPress(VK_LEFT);
		}
		
		else if (sen.equals("Caps Lock"))
		{
			robot.keyPress(VK_CAPS_LOCK);
		}
		
			else
        {
		switch (character) {
        case 'a': robot.keyPress(VK_A); break;
        case 'b': robot.keyPress(VK_B); break;
        case 'c': robot.keyPress(VK_C); break;
        case 'd': robot.keyPress(VK_D); break;
        case 'e': robot.keyPress(VK_E); break;
        case 'f': robot.keyPress(VK_F); break;
        case 'g': robot.keyPress(VK_G); break;
        case 'h': robot.keyPress(VK_H); break;
        case 'i': robot.keyPress(VK_I); break;
        case 'j': robot.keyPress(VK_J); break;
        case 'k': robot.keyPress(VK_K); break;
        case 'l': robot.keyPress(VK_L); break;
        case 'm': robot.keyPress(VK_M); break;
        case 'n': robot.keyPress(VK_N); break;
        case 'o': robot.keyPress(VK_O); break;
        case 'p': robot.keyPress(VK_P); break;
        case 'q': robot.keyPress(VK_Q); break;
        case 'r': robot.keyPress(VK_R); break;
        case 's': robot.keyPress(VK_S); break;
        case 't': robot.keyPress(VK_T); break;
        case 'u': robot.keyPress(VK_U); break;
        case 'v': robot.keyPress(VK_V); break;
        case 'w': robot.keyPress(VK_W); break;
        case 'x': robot.keyPress(VK_X); break;
        case 'y': robot.keyPress(VK_Y); break;
        case 'z': robot.keyPress(VK_Z); break;
        case 'A': robot.keyPress(VK_A); break;
        case 'B': robot.keyPress(VK_B); break;
        case 'C': robot.keyPress(VK_C); break;
        case 'D': robot.keyPress(VK_D); break;
        case 'E': robot.keyPress(VK_E); break;
        case 'F': robot.keyPress(VK_F); break;
        case 'G': robot.keyPress(VK_G); break;
        case 'H': robot.keyPress(VK_H); break;
        case 'I': robot.keyPress(VK_I); break;
        case 'J': robot.keyPress(VK_J); break;
        case 'K': robot.keyPress(VK_K); break;
        case 'L': robot.keyPress(VK_L); break;
        case 'M': robot.keyPress(VK_M); break;
        case 'N': robot.keyPress(VK_N); break;
        case 'O': robot.keyPress(VK_O); break;
        case 'P': robot.keyPress(VK_P); break;
        case 'Q': robot.keyPress(VK_Q); break;
        case 'R': robot.keyPress(VK_R); break;
        case 'S': robot.keyPress(VK_S); break;
        case 'T': robot.keyPress(VK_T); break;
        case 'U': robot.keyPress(VK_U); break;
        case 'V': robot.keyPress(VK_V); break;
        case 'W': robot.keyPress(VK_W); break;
        case 'X': robot.keyPress(VK_X); break;
        case 'Y': robot.keyPress(VK_Y); break;
        case 'Z': robot.keyPress(VK_Z); break;
        case '0': robot.keyPress(VK_0); break;
        case '1': robot.keyPress(VK_1); break;
        case '2': robot.keyPress(VK_2); break;
        case '3': robot.keyPress(VK_3); break;
        case '4': robot.keyPress(VK_4); break;
        case '5': robot.keyPress(VK_5); break;
        case '6': robot.keyPress(VK_6); break;
        case '7': robot.keyPress(VK_7); break;
        case '8': robot.keyPress(VK_8); break;
        case '9': robot.keyPress(VK_9); break;
  
        
        default:
		}
		}
		
		

	}

}
}
