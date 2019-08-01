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

public class Bullet extends BaseSprite implements Moveable, Drawable {
    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 5;

    public Bullet() {
        this(0,0, ImageMap.get("mb01"));
    }

    public Bullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();
        g.drawImage(image,getX()+80,getY()+70,image.getWidth(null) / 4,image.getHeight(null) / 4,null);

    }

    @Override
    public void move() {
        setY(getY() - speed);
    }
    public void borderTesting(){
        if (getY() < 30 - image.getHeight(null)){
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.bulletList.remove(this);
        }
    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
    /*public void collisionTesting(List<EnemyPlane> enemyPlaneList){
        for (EnemyPlane enemyPlane: enemyPlaneList){
            if (enemyPlane.getRectangle().intersects(this.getRectangle())){
                enemyPlaneList.remove(enemyPlane);
            }
        }
    }*/
    public void collisionTesting(List<EnemyPlane> enemyPlaneList){
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (EnemyPlane enemyPlane: enemyPlaneList){
            if (enemyPlane.getRectangle().intersects(this.getRectangle())){
                enemyPlaneList.remove(enemyPlane);
                gameFrame.bulletList.remove(this);
                gameFrame.score++;
            }
        }
    }
    public void collisionTesting(Boss boss){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (boss.getRectangle().intersects(this.getRectangle()) /*&& gameFrame.score>=10*/&& Boss.live){
            gameFrame.bulletList.remove(this);
            if (gameFrame.score>=10){
                gameFrame.bossHp--;
                if (gameFrame.bossHp <= 0){
                    gameFrame.bossHp = 0;
//                    gameFrame.gameOver = true;
                }
            }
        }
    }
}
