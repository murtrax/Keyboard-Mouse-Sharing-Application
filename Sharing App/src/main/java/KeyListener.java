import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;




public class KeyListener implements NativeKeyListener{
	
	
	 Socket wheelSocket;
	    DataOutputStream outToClient;
	    BufferedReader inFromServer;


	
	    public KeyListener (Socket wheelSocket, DataOutputStream outToClient, BufferedReader inFromServer) throws AWTException {
	        this.wheelSocket = wheelSocket;
	        this.outToClient = outToClient;
	        this.inFromServer = inFromServer;


	    }


	
	String keyPress;
	int pressed = 0;
	
	public String getKeyPress() {
		return keyPress;
	}

	public void setKeyPress(String keyPress) {
		this.keyPress = keyPress;
	}

	public void nativeKeyPressed(NativeKeyEvent e) {
		System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		keyPress=NativeKeyEvent.getKeyText(e.getKeyCode());
		try {
			outToClient.writeBytes(keyPress + "\n");
			System.out.println("WRITTEN TO CLIENT " + e.getKeyChar());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			Thread.sleep(2);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

	}
}