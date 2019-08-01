package com.zs.runtime;

import com.sun.deploy.util.BlackList;
import com.zs.base.BaseSprite;
import com.zs.base.Drawable;
import com.zs.main.GameFrame;
import com.zs.util.DataStore;
import com.zs.util.ImageMap;

import java.awt.*;

public class Blood extends BaseSprite implements Drawable {
    private Image image;
    public Blood() {
        this(20,50, ImageMap.get("blood"));
    }

    public Blood(int x, int y,Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
    }
//    public void collisionTesting(Plane plane,EnemyBullet enemyBullet){
//        GameFrame gameFrame = DataStore.get("gameFrame");


//                Blood b = gameFrame.bloodList.get(i);
//                System.out.println(b);

//       /* out:*/if (plane.getRectangle().intersects(enemyBullet.getRectangle())){
////                    for (int i = gameFrame.bloodList.size() - 1; i >= 0; i--) {
//                        gameFrame.bloodList.remove(gameFrame.bloodList.size()-1);
//                        break out;
//                    }
//                }
//    }
            /*for (Blood blood: gameFrame.bloodList){
              Blood b1 = blood;
            }*/



}
