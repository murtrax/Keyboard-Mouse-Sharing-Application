import org.jnativehook.GlobalScreen;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.jnativehook.GlobalScreen;

public class KeyPress extends Thread{


	    Socket wheelSocket;
	    DataOutputStream outToClient;
	    BufferedReader inFromServer;

	    public KeyPress()
	    {
	    	
	    }
	
	    public KeyPress (Socket wheelSocket, DataOutputStream outToClient, BufferedReader inFromServer) throws AWTException {
	        this.wheelSocket = wheelSocket;
	        this.outToClient = outToClient;
	        this.inFromServer = inFromServer;


	    }

	    
	public void run()
	{
		KeyListener keyListen = null;
		try {
			keyListen = new KeyListener(wheelSocket,outToClient,inFromServer);
		} catch (AWTException e) {
			
			e.printStackTrace();
		}    
        GlobalScreen.addNativeKeyListener(keyListen);                
		while(true)
		{
			
          

		}
		}
	}

