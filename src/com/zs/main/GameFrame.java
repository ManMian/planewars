package com.zs.main;

import com.zs.constant.FrameConstant;
import com.zs.runtime.Background;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {
    private Background background = new Background();

    @Override
    public void paint(Graphics g) {
        background.draw(g);
    }
    public void init(){
        //设置好尺寸
        setSize(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);
        //设置居中
        setLocationRelativeTo(null);

        enableInputMethods(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        new Thread() {
            @Override
            public void run(){
               while (true){
                   repaint();
                   try {
                       Thread.sleep(10);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            }
        }.start();

        setVisible(true);
    }
    private Image offScreenImage = null;//创建缓冲区
    public void update(Graphics g) {
        if (offScreenImage == null){
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_WIDTH);
        }
        Graphics gOff = offScreenImage.getGraphics();//创建离线图片实例，在图片缓冲区绘图

        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);//将缓冲图片绘制到窗口目标
    }
}
