import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

public class GlobalMouseWheelListener implements NativeMouseWheelListener {

    private int wheel = 0;

    public int getWheel() {
        return wheel;
    }
    public void setWheel(int wheel) {
        this.wheel = wheel;
    }

    
    Socket wheelSocket;
    DataOutputStream outToClient;
    BufferedReader inFromServer;

    public GlobalMouseWheelListener ()
    {
    	
    }

    public GlobalMouseWheelListener (Socket wheelSocket, DataOutputStream outToClient, BufferedReader inFromServer) throws AWTException {
        this.wheelSocket = wheelSocket;
        this.outToClient = outToClient;
        this.inFromServer = inFromServer;

    }


    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
        //System.out.println("MouseWheelMoved:" + e.getWheelRotation());
        wheel = e.getWheelRotation();
        try {
			outToClient.writeBytes("MouseWheel" + " " + getWheel() + "\n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	
		setWheel(0);
    }

}