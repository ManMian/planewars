package com.zs.main;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.xml.internal.bind.v2.model.core.EnumConstant;
import com.zs.constant.FrameConstant;
import com.zs.runtime.Background;
import com.zs.runtime.Blood;
import com.zs.runtime.Boss;
import com.zs.runtime.Bullet;
import com.zs.runtime.EnemyBullet;
import com.zs.runtime.EnemyPlane;
import com.zs.runtime.Plane;
import com.zs.runtime.Props;
import com.zs.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    //血量
    public final List<Blood> bloodList = new CopyOnWriteArrayList<>();
    //道具
    public final List<Props> propsList = new CopyOnWriteArrayList<>();

    public Boss boss = new Boss();

    public boolean gameOver = false;

    public int score = 0;
    public int bossHp = 10;

    @Override
    public void paint(Graphics g) {
        if (!gameOver) {
            background.draw(g);
            plane.draw(g);
            if (score >= 10 && Boss.live && bossHp > 0) {
                boss.draw(g);
            } else if (bossHp ==0 && Boss.live) {
                Boss.live = false;
                score += 100;
            }
            for (Blood blood : bloodList) {
                blood.draw(g);
            }
            for (Bullet bullet : bulletList) {
                bullet.draw(g);
            }
            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.draw(g);
//                for (Blood blood:bloodList){
//                    blood.collisionTesting(plane,enemyBullet);
//                }
            }
            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.draw(g);
            }
            for (Props props : propsList) {
                props.draw(g);
            }
            for (Bullet bullet : bulletList) {
                bullet.collisionTesting(enemyPlaneList);
            }

            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.collisionTesting(plane);
            }
            for (Props props : propsList) {
                props.collisionTesting(plane);
            }
            if (score >= 10 && Boss.live) {
                for (Bullet bullet : bulletList) {
                    bullet.collisionTesting(boss);
                }
            }




            /*g.setColor(Color.magenta);
            g.drawString("" + enemyBulletList.size(),100,100);*/
            g.setFont(new Font("楷体", Font.BOLD, 25));
            g.setColor(new Color(222, 117, 129));
            g.drawString("得分：" + score, 650, 100);
            if (score >= 10) {
                g.drawString("bossHp：" + bossHp, 650, 150);

            }
        }
    }

    /**
     * 初始化窗口的方法
     */
    public void init() {
        //设置好尺寸
        setSize(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
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
        //鼠标监听
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.print(e.getX() + ",");
                System.out.println(e.getY());
            }
        });

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        //游戏初始时添加敌方飞机
        for (int i = 0; i < 102400; i += 700) {
            int r = 0;
            r += 30;
            enemyPlaneList.add(new EnemyPlane((63 + r), -i + 200, /*ImageMap.get("ep01"),*/1));
            enemyPlaneList.add(new EnemyPlane((576 + r), -i + 500, /*ImageMap.get("ep01"),*/1));
            enemyPlaneList.add(new EnemyPlane((613 + r), -i - 200,/* ImageMap.get("ep01"),*/1));
            enemyPlaneList.add(new EnemyPlane((r + 600), -i,/* ImageMap.get("ep01"),*/2));
            enemyPlaneList.add(new EnemyPlane((3 + r), -i + 500,/* ImageMap.get("ep01"),*/1));
//            enemyPlaneList.add(new EnemyPlane(503,-1320, ImageMap.get("ep01")));
//            enemyPlaneList.add(new EnemyPlane(103,-2220, ImageMap.get("ep01")));
//            enemyPlaneList.add(new EnemyPlane(403,-2820, ImageMap.get("ep01")));
//            enemyPlaneList.add(new EnemyPlane(503,-3920, ImageMap.get("ep01")));
//            enemyPlaneList.add(new EnemyPlane(403,-3620, ImageMap.get("ep01")));
//            enemyPlaneList.add(new EnemyPlane(703,-4220, ImageMap.get("ep01")));
//            enemyPlaneList.add(new EnemyPlane(303,-4320, ImageMap.get("ep01")));
//            enemyPlaneList.add(new EnemyPlane(303,-5320, ImageMap.get("ep01")));

        }
        bloodList.add(new Blood(20, 50, ImageMap.get("blood")));
        bloodList.add(new Blood(70, 50, ImageMap.get("blood")));
        bloodList.add(new Blood(120, 50, ImageMap.get("blood")));
        bloodList.add(new Blood(170, 50, ImageMap.get("blood")));
        bloodList.add(new Blood(220, 50, ImageMap.get("blood")));
        bloodList.add(new Blood(270, 50, ImageMap.get("blood")));

        propsList.add(new Props(202, 0, 1));
        propsList.add(new Props(250, 00, 1));
        propsList.add(new Props(300, 50, 1));
        propsList.add(new Props(400, 50, 1));
        propsList.add(new Props(500, -1050, 2));
        propsList.add(new Props(600, -1150, 2));
        setVisible(true);
    }

    private Image offScreenImage = null;//创建缓冲区

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_WIDTH);
        }
        Graphics gOff = offScreenImage.getGraphics();//创建离线图片实例，在图片缓冲区绘图

        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);//将缓冲图片绘制到窗口目标
    }
}
