import java.awt.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Wheel extends Thread{

    private Robot robot;
    private tagManager tagManager;

    Socket wheelSocket;
    DataOutputStream outToClient;
    BufferedReader inFromServer;

    String sen = "", tag = "";

    public Wheel(Socket wheelSocket, DataOutputStream outToClient, BufferedReader inFromServer) throws AWTException {
        this.wheelSocket = wheelSocket;
        this.outToClient = outToClient;
        this.inFromServer = inFromServer;

        robot = new Robot();
        tagManager= new tagManager();

    }

    @Override
    public void run() {
        while (true) {

            try { sen = inFromServer.readLine(); } catch (IOException e) { e.printStackTrace(); }

           

            try {
                tag = tagManager.getTag(sen);
                
            } catch (StringIndexOutOfBoundsException e) { e.printStackTrace(); }

            sen = tagManager.removeTag(sen);

            if (tag.equals("MouseWheel")) {
                if (Integer.parseInt(sen) == 1) {
                    robot.mouseWheel(1);
                    System.out.println("Mouse wheel moved: 1");
                }
                if (Integer.parseInt(sen) == -1) {
                    robot.mouseWheel(-1);
                    System.out.println("Mouse wheel moved: -1");
                }
            } else
                System.out.println("not the right tag");
        }
    }


}
