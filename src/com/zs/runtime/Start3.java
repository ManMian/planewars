package com.zs.runtime;

import com.zs.base.BaseSprite;
import com.zs.base.Drawable;
import com.zs.base.Moveable;
import com.zs.constant.FrameConstant;
import com.zs.util.ImageMap;

import java.awt.*;

import static com.zs.constant.FrameConstant.FRAME_HEIGHT;
/*没用到*/
public class Start3 extends BaseSprite implements Drawable, Moveable {
    private Image image;

    public Start3() {

        this(0,FRAME_HEIGHT - ImageMap.get("start1").getHeight(null), ImageMap.get("start3"));
    }

    public Start3(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void move() {
        setY(getY() + FrameConstant.GAME_SPEED);
        Start.i+=1;
        System.out.println(Start.i);
        if (Start.i>=500){
            Start.i = 400;
        }
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);


    }
}
