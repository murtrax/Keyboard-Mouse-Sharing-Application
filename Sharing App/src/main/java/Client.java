import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static PointerInfo a = MouseInfo.getPointerInfo();

    public static void main(String args[]) throws IOException, IOException, InterruptedException, AWTException, StringIndexOutOfBoundsException
    {
        //Robot object
        Robot robot = new Robot();

        //Get the screen resolution
   
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenSize.getHeight());     // the screen height
        System.out.println(screenSize.getWidth());      //the screen width
        int h = (int)screenSize.getHeight();
        int w = (int)screenSize.getWidth();
        String H = Integer.toString(h);
        String W = Integer.toString(w);

        /*--------------------------------------------*/

        //Connection variables
        Socket clientSocket = new Socket(/*"192.168.8.109"*/"10.7.40.47", 5602);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        //Send screen resolution to server
        outToServer.writeBytes(H+"\n");
        outToServer.writeBytes(W+"\n");

        /*--------------------------------------------*/


        //Connection variables
        Socket clickSocket = new Socket("10.7.40.47", 5603);
        DataOutputStream outToServer2 = new DataOutputStream(clickSocket.getOutputStream());
        BufferedReader inFromServer2 = new BufferedReader(new InputStreamReader(clickSocket.getInputStream()));

        //Start listening for clicks(left / right / middle)
        Click click = new Click(clickSocket, outToServer2, inFromServer2);
        click.start();

        /*--------------------------------------------*/

        //Connection variables
        Socket wheelSocket = new Socket("10.7.40.47", 5604);
        DataOutputStream outToServer3 = new DataOutputStream(wheelSocket.getOutputStream());
        BufferedReader inFromServer3 = new BufferedReader(new InputStreamReader(wheelSocket.getInputStream()));

        //Start listening for clicks(left / right / middle)
        Wheel wheel = new Wheel(wheelSocket, outToServer3, inFromServer3);
        wheel.start();

        /*--------------------------------------------*/
        
        
        /*--------------------------------------------*/

        //Connection variables
        Socket keyListen = new Socket("10.7.40.47", 5605);
        DataOutputStream outToServer4 = new DataOutputStream(keyListen.getOutputStream());
        BufferedReader inFromServer4 = new BufferedReader(new InputStreamReader(keyListen.getInputStream()));

        Key key = new Key(keyListen, outToServer4, inFromServer4);
        key.start();

        /*--------------------------------------------*/


        while(true) {
            String sentence;
            a = MouseInfo.getPointerInfo();
            sentence = inFromServer.readLine();
            String[] position = sentence.split(" ");

            robot.mouseMove((Integer.parseInt(position[0])), (Integer.parseInt(position[1])));
            System.out.println(position[0] + " " + position[1]);    //x and y position of mouse
            
        }

    }

}


//}