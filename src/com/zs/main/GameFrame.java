package com.zs.main;

import com.sun.xml.internal.bind.v2.model.core.EnumConstant;
import com.zs.constant.FrameConstant;
import com.zs.runtime.Background;
import com.zs.runtime.Bullet;
import com.zs.runtime.EnemyBullet;
import com.zs.runtime.EnemyPlane;
import com.zs.runtime.Plane;
import com.zs.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {
    //创建背景对象
    private Background background = new Background();
    //创建飞机对象
    private Plane plane = new Plane();
    //创建子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();
    //创建敌方飞机
    public final List<EnemyPlane> enemyPlaneList = new CopyOnWriteArrayList<>();
    //敌方子弹集合
    public final List<EnemyBullet> enemyBulletList = new CopyOnWriteArrayList<>();

    public boolean gameOver = false;

    @Override
    public void paint(Graphics g) {
        if (!gameOver){
            background.draw(g);
            plane.draw(g);
            for(Bullet bullet:bulletList){
                bullet.draw(g);
            }
            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.draw(g);
            }
            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.draw(g);

            }
            for (Bullet bullet:bulletList){
                bullet.collisionTesting(enemyPlaneList);
            }
            for (EnemyBullet enemyBullet: enemyBulletList){
                enemyBullet.collisionTesting(plane);
            }
            /*g.setColor(Color.magenta);
            g.drawString("" + enemyBulletList.size(),100,100);*/
        }
    }

    /**
     * 初始化窗口的方法
     * */
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
        //添加键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);
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
        //游戏初始时添加敌方飞机
        enemyPlaneList.add(new EnemyPlane(300,30, ImageMap.get("ep01")));
        enemyPlaneList.add(new EnemyPlane(300,-30, ImageMap.get("ep01")));
        enemyPlaneList.add(new EnemyPlane(150,30, ImageMap.get("ep01")));
        enemyPlaneList.add(new EnemyPlane(100,130, ImageMap.get("ep01")));
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
