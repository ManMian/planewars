package com.zs.test;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Test extends JFrame {
    public Test(){
        getContentPane().add(new GJpanel());
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
// TODO Auto-generated method stub
        new Test();
    }
}
class GJpanel extends JPanel {
    private int w;
    private int h;
    public GJpanel(){
    }
    public void paintComponent(final Graphics g){
        w = getWidth();
        h = getHeight();
        g.setColor(Color.green);
        g.drawLine(0, 0, 0,getHeight());
        g.setColor(Color.red);
        g.drawLine(0,h/2,w,h/2); //x
        g.drawLine(w, h/2, w-10, h/2-10);
        g.drawLine(w, h/2, w-10, h/2+10);
        g.drawLine(w/2, 0,w/2, h); //y
        g.drawLine(w/2, 0, w/2-10, 10);
        g.drawLine(w/2, 0, w/2+10, 10);
        g.drawString("Y", w/2-20, 20);
        g.drawString("X", w-20, h/2+20);
        for(int x =0;x<w; x++){
            int y =(int) (Math.cos (x*Math. PI/180)*h/3);
            g.drawString("·", x, h/2-y);
        }
    }
}
