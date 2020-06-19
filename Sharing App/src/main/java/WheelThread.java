import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.Buffer;

public class WheelThread extends Thread{

    Socket socket;
    DataOutputStream outToClient;
    BufferedReader inFromClient;

    public WheelThread(Socket socket, DataOutputStream outToClient, BufferedReader inFromClient){
        this.socket = socket;
        this.outToClient = outToClient;
        this.inFromClient = inFromClient;
    }

    @Override
    public void run() {

            /*start listening for moue wheel*/
            GlobalMouseWheelListener mouseWheelListener = null;
			try {
				mouseWheelListener = new GlobalMouseWheelListener(socket,outToClient, inFromClient);
			} catch (Exception e) {
				e.printStackTrace();
			}
            GlobalScreen.addNativeMouseWheelListener(mouseWheelListener);

        while(true) {
        	

    }


}
}