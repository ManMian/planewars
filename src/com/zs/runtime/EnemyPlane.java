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
    private Image image, image2, image3;
    private Random random = new Random();
    private int type;
    private int speed = FrameConstant.GAME_SPEED * 3;

    public EnemyPlane(int x, int y,/* Image image,*/int type) {
        super(x, y);
        /*   this.image = image;*/
        this.type = type;
        this.image = ImageMap.get("ep01");
        this.image2 = ImageMap.get("ep02");
        this.image3 = ImageMap.get("ep03");
    }

    public EnemyPlane() {
        this(0, 0,/*ImageMap.get("ep01"),*/1);
    }

    @Override
    public void draw(Graphics g) {
        move();
        fire();
        if (type == 1) {
            g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        } else if (type == 2) {
            g.drawImage(image2, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        } else if (type == 3) {
            g.drawImage(image3, getX(), getY(), image.getWidth(null)*2, image.getHeight(null)*2, null);
        }
    }

    public void fire() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (type == 1) {
            if (this.getY() > -150) {
                if (random.nextInt(1000) > 992) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null) / 2) - ImageMap.get("epb01").getWidth(null) / 2,
                            getY() + image.getHeight(null),
                            ImageMap.get("epb02")));
                }
            }
        } else if (type == 2) {
            if (this.getY() > -150) {
                if (random.nextInt(1000) > 995) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null) / 2) - ImageMap.get("epb01").getWidth(null) / 2,
                            getY() + image.getHeight(null),
                            ImageMap.get("epb03")));
                }
            }
        } else if (type == 3) {
            if (this.getY() > -150) {
                if (random.nextInt(10000) > 9998) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null) / 2) - ImageMap.get("epb01").getWidth(null) / 2,
                            getY() + image.getHeight(null),
                            ImageMap.get("epb01")));
                }
            }
        }
    }

    private boolean right = true;

    @Override
    public void move() {
        if (type == 1) {
            setY(getY() + speed);
        } else if (type == 2) {
            if (right) {
                setX(getX() + speed + 2);
                setY(getY() + speed + 1);
            } else {
                setX(getX() - speed);
                setY(getY() - speed + 2);
            }
        } else if (type == 3) {
            if (right) {
                setX(getX() + speed);
                setY(getY()+1);
            } else {
                setX(getX() - speed);
                setY(getY()+1);
            }
        }
        borderTesting();
    }

    public void borderTesting() {
        if (type == 1) {
            if (getY() > FrameConstant.FRAME_HEIGHT) {
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.enemyPlaneList.remove(this);
            }
        } else if (type == 2) {
            if (getX() + image2.getWidth(null) >= FrameConstant.FRAME_WIDTH) {
                right = false;
            } else if (getX() < 0) {
                right = true;
            }
        } else if (type == 3) {
            if (getX() + image2.getWidth(null) >= FrameConstant.FRAME_WIDTH) {
                right = false;
            } else if (getX() < 0) {
                right = true;
            }
        }

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }
    /*public void collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
            if (plane.getRectangle().intersects(this.getRectangle())){
                gameFrame.bulletList.remove(this);
                gameFrame.gameOver = true;
            }
    }*/
}
