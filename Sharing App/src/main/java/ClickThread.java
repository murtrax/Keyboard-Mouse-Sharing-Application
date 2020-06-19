import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClickThread extends Thread {


    private Socket socket;
    private DataOutputStream outToClient;
    private BufferedReader inFromClient;

    public ClickThread(Socket socket, DataOutputStream outToClient, BufferedReader inFromClient){
        this.socket = socket;
        this.outToClient = outToClient;
        this.inFromClient = inFromClient;
    }



    @Override
    public  void run(){

            /* Start Listening for Mouse Clicks*/
            GlobalMouseListener mouseClick = new GlobalMouseListener();    // Construct the example object.
            GlobalScreen.addNativeMouseListener(mouseClick);                // Add the appropriate listeners.

        while(true) {
            //if(mouseClick.getClick() == 1 || mouseClick.getClick() == 2) {
                try {
                    outToClient.writeBytes("MouseClick" + " " + mouseClick.getClick() + "\n");
                    outToClient.flush();
                    mouseClick.setClick(0);
                    Thread.sleep(240);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
               // }
            }
        }

    }


}

