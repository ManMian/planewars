package com.zs.runtime;

import com.zs.base.BaseSprite;
import com.zs.base.Drawable;
import com.zs.base.Moveable;
import com.zs.constant.FrameConstant;
import com.zs.util.ImageMap;

import java.awt.*;

import static com.zs.constant.FrameConstant.FRAME_HEIGHT;

public class Start extends BaseSprite implements Drawable, Moveable {
    private Image image;
    public static int i = 0;

    public Start() {

        this(0,FRAME_HEIGHT - ImageMap.get("start5").getHeight(null), ImageMap.get("start5"));
    }

    public Start(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void move() {
        setY(getY() + FrameConstant.GAME_SPEED);
        i++;
        System.out.println(i);
        /*if (i>=500){
            i = 400;
        }*/
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
    }
}
