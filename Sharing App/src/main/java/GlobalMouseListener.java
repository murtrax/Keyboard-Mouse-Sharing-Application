import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class GlobalMouseListener implements NativeMouseInputListener {

    private int click = 0;

    public void  setClick(int click){
        this.click = click;
    }
    public int getClick(){ return click; }

    //Global Mouse Click detector
    public void nativeMouseClicked(NativeMouseEvent e) {
        //click = e.getButton();
        System.out.println("MouseClicked:" + e.getButton()/*e.getClickCount()*/);
    }


    //Global Mouse Key Pressed Detector
    public void nativeMousePressed(NativeMouseEvent e) {
       System.out.println("Mouse Pressed: " + e.getButton());
       click = e.getButton();
    }


    //Global Mouse Key Release Detector
    public void nativeMouseReleased(NativeMouseEvent e) {
       System.out.println("Mouse Released: " + e.getButton());
       //click = e.getButton();
    }


    //Global Mouse Move Detector
    public void nativeMouseMoved(NativeMouseEvent e) {
       System.out.println("Mouse Moved: " + e.getX() + " " + e.getY());
    }


    //Global Mouse Drag Detector
    public void nativeMouseDragged(NativeMouseEvent e) {
        System.out.println("Mouse Dragged: " + e.getX() + ", " + e.getY());
    }

}