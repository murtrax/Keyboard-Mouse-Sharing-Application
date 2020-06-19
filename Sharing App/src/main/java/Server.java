import jdk.nashorn.internal.objects.Global;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseWheelListener;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static PointerInfo a = MouseInfo.getPointerInfo();

    public static void main(String arg[]) throws IOException, InterruptedException, AWTException
    {

        tagManager tagManger = new tagManager();

        {
            /**************************************Disable Logger Messages For JNativeHook**********************************/
            // Get the logger for "org.jnativehook" and set the level to warning.
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.WARNING);
            // Don't forget to disable the parent handlers.
            logger.setUseParentHandlers(false);
            /******************************************************************************************************************/
        }


        //Server Socket
        ServerSocket welcomeSocket = new ServerSocket(5602);
        ServerSocket clickServerSocket = new ServerSocket(5603);
        ServerSocket wheelServerSocket = new ServerSocket(5604);
        ServerSocket keyListenSocket = new ServerSocket(5605);

    



        while (true) {

            int x=0, y=0;
            int tX=0, tY=0;
            
            /**************************************************************************/
            //Connection variables
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Socket 1 for mouse movements accepted");
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            /**************************************************************************/

            /*
             * Read x and y coordinates from client (resolution)
             * Set the value of respective variables accordingly
             * Print the received values on the console
             */
            String sentence;
            sentence = inFromClient.readLine();
            int maxY = Integer.parseInt(sentence);              //height of screen
            sentence = inFromClient.readLine();
            int maxX = Integer.parseInt(sentence);              //width of screen
            System.out.println("X: " + maxX + " Y: " + maxY);


            /*Register native hook*/
            try {
                GlobalScreen.registerNativeHook();
            } catch (NativeHookException ex) {
                System.err.println("There was a problem registering the native hook.");
                System.err.println(ex.getMessage());
                System.exit(1);
            }



            /**************************************************************************/

            //Connection variables
            Socket clickSocket = clickServerSocket.accept();
            System.out.println("Socket 2 for mouse clicks accepted");
            BufferedReader inFromClient2 = new BufferedReader(new InputStreamReader(clickSocket.getInputStream()));
            DataOutputStream outToClient2 = new DataOutputStream(clickSocket.getOutputStream());



            /**************************************************************************/

            //Connection variables
            Socket wheelSocket = wheelServerSocket.accept();
            System.out.println("Socket 3 for mouseWheel accepted");
            BufferedReader inFromClient3 = new BufferedReader(new InputStreamReader(wheelSocket.getInputStream()));
            DataOutputStream outToClient3 = new DataOutputStream(wheelSocket.getOutputStream());



            /**************************************************************************/
            

            
            /**************************************************************************/

            //Connection variables
            Socket keySocket = keyListenSocket.accept();
            System.out.println("Socket 4 for keyboard accepted");
            BufferedReader inFromClient4 = new BufferedReader(new InputStreamReader(keySocket.getInputStream()));
            DataOutputStream outToClient4 = new DataOutputStream(keySocket.getOutputStream());

            /**************************************************************************/

            //Thread to listen for mouse click
            ClickThread t1 = new ClickThread(clickSocket, outToClient2, inFromClient2);
            //Thread to listen for mouse wheel
            WheelThread t2 = new WheelThread(wheelSocket, outToClient3, inFromClient3);
            //Thread to listen for each keypress
           KeyPress t3 = new KeyPress (keySocket, outToClient4, inFromClient4);
            t1.start();
           t2.start();
           t3.start();
            PointerInfo b = MouseInfo.getPointerInfo();
            while (true)
            {
                a = MouseInfo.getPointerInfo();
                tY = (int) a.getLocation().getY();
                tX = (int) a.getLocation().getX();

                if (  tY != y || tX != x )
                {
                    x= tX;
                    y=tY;
                    tY = (int) a.getLocation().getY();
                    tX = (int) b.getLocation().getX();

                    //Print statements
                    System.out.println(x + " " + y);                        //print the x and y coordinates of mouse on screen
                    //send the x and y coordinates + value of mouseClicked variable to client
                    outToClient.writeBytes( x + " " + y + "\n");
                    

                }
            }






        }
    }}



