package com.zs.runtime;

import com.zs.base.BaseSprite;
import com.zs.base.Drawable;
import com.zs.base.Moveable;
import com.zs.constant.FrameConstant;
import com.zs.main.GameFrame;
import com.zs.util.DataStore;
import com.zs.util.ImageMap;

import java.awt.*;
import java.util.Random;

public class Props extends BaseSprite implements Drawable, Moveable {
    private Image image,image2;
    private int type;
    Random random = new Random();
    private int speed = FrameConstant.GAME_SPEED  * 3;

    public Props() {
        this(0,0,1);
    }

    public Props(int x, int y, int type) {
        super(x, y);
        this.type = type;
        this.image = ImageMap.get("props1");
        this.image2 = ImageMap.get("props2");
    }

    @Override
    public void draw(Graphics g) {
        move();
//        fire();
        if (type == 1){
            g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        }else if (type == 2){
            g.drawImage(image2,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        }
    }
/*    public void fire(){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (type == 1){
            if (this.getY() > -150){
                if (random.nextInt(1000)>995){
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null) / 2) - ImageMap.get("props1").getWidth(null) / 2,
                            getY() + image.getHeight(null),
                            ImageMap.get("props1")));
                }
            }
        }else if (type == 2){
            if (this.getY() > -150){
                if (random.nextInt(1000)>998){
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null) / 2) - ImageMap.get("props1").getWidth(null) / 2,
                            getY() + image.getHeight(null),
                            ImageMap.get("props1")));
                }
            }
        }
    }*/
    private boolean right = true;
    @Override
    public void move() {
//        if (type == 1){
//            setY(getY() + speed);
//        }else if (type == 2){
//            setY(getY() + speed);
//        }
        setY(getY() + speed);
        borderTesting();
    }
    public void borderTesting(){
        if (type == 1){
            if (getY() > FrameConstant.FRAME_HEIGHT){
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.enemyPlaneList.remove(this);
            }
        }else if (type == 2){
            if (getX() + image2.getWidth(null) >= FrameConstant.FRAME_WIDTH){
                right = false;
            }else if (getX() < 0){
                right = true;
            }
        }

    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
    public void collisionTesting(Plane plane) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.propsList.remove(this);
            plane.propss+=1;
            if (plane.propss>2){
                plane.propss = 1;
            }
        }
    }
}
