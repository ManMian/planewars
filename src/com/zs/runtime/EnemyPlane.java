package com.zs.runtime;

import com.zs.base.BaseSprite;
import com.zs.base.Drawable;
import com.zs.base.Moveable;
import com.zs.constant.FrameConstant;
import com.zs.main.GameFrame;
import com.zs.util.DataStore;
import com.zs.util.ImageMap;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class EnemyPlane extends BaseSprite implements Moveable, Drawable {
    private Image image;
    private Random random = new Random();
    private int speed = FrameConstant.GAME_SPEED * 3;

    public EnemyPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    public EnemyPlane() {
        this(0,0,ImageMap.get("ep01"));
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
        fire();
    }
    public void fire(){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (random.nextInt(1000)>990){
            gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null) / 2) - ImageMap.get("epb01").getWidth(null) / 2,
                    getY() + image.getHeight(null),
                    ImageMap.get("epb01")));
        }
    }

    @Override
    public void move() {
        setY(getY() + speed);
        borderTesting();
    }
    public void borderTesting(){
        if (getY() > FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.enemyPlaneList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
    /*public void collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
            if (plane.getRectangle().intersects(this.getRectangle())){
                gameFrame.bulletList.remove(this);
                gameFrame.gameOver = true;
            }
    }*/
}
