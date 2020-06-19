import java.awt.*;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Click extends Thread{

    Socket clickSocket;
    DataOutputStream outToClient;
    BufferedReader inFromServer;

    private Robot robot;
    private tagManager tagManager;
    private String sen, tag="";


    public Click(Socket clickSocket, DataOutputStream outToClient, BufferedReader inFromServer) throws IOException, AWTException {
        this.clickSocket = clickSocket;
        this.outToClient = outToClient;
        this.inFromServer = inFromServer;

        robot = new Robot();
        tagManager= new tagManager();
    }


    @Override
    public void run(){
        while (true){

            //System.out.println("hello step 1");

            try { sen = inFromServer.readLine(); } catch (IOException e) { e.printStackTrace(); }

            //System.out.println("hello step 2");
            //System.out.println(sen);

            try {
                tag = tagManager.getTag(sen);
                //System.out.println(tag );
            } catch (StringIndexOutOfBoundsException e){e.printStackTrace();}

            sen = tagManager.removeTag(sen);
            //System.out.println(sen + "step 4");

            if (tag.equals("MouseClick")) {
                if (Integer.parseInt(sen) == 1) {
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    System.out.println("Left Click!");
                }
                if (Integer.parseInt(sen) == 2) {
                    robot.mousePress(InputEvent.BUTTON3_MASK);
                    robot.mouseRelease(InputEvent.BUTTON3_MASK);
                    System.out.println("Right Click!");
                }
            } else
               System.out.println(/*"not the right tag"*/);

        }
    }
}
