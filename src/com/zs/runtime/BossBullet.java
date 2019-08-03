package com.zs.runtime;

import com.zs.base.BaseSprite;
import com.zs.base.Drawable;
import com.zs.base.Moveable;
import com.zs.constant.FrameConstant;
import com.zs.main.GameFrame;
import com.zs.util.DataStore;

import java.awt.*;

public class BossBullet extends BaseSprite implements Moveable, Drawable {
    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 5;

    public BossBullet() {}

    public BossBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }
    public void draw(Graphics g){
        move();
//        g.drawImage(image,getX()+174,getY()+190,image.getWidth(null),image.getHeight(null),null);
//        int r = 0;
        g.drawImage(image,getX()+202,getY()+214,image.getWidth(null),image.getHeight(null),null);
//        r+=0.5;
    }
    @Override
    public void move() {
        setY(getY() + speed);
        borderTesting();
    }
    public void borderTesting(){
        if (getY() > FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.enemyBulletList.remove(this);
        }
    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

    public void collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())){
            gameFrame.bossBullets.remove(this);
            if(gameFrame.bloodList.size()>0){
                gameFrame.bloodList.remove(gameFrame.bloodList.size() -1 );
            }
            if (gameFrame.bloodList.size() < 0){
          //  gameFrame.gameOver = true;
            }
        }
    }

}
