package com.zs.runtime;

import com.zs.base.BaseSprite;
import com.zs.base.Drawable;
import com.zs.base.Moveable;
import com.zs.constant.FrameConstant;
import com.zs.util.ImageMap;

import java.awt.*;

import static com.zs.constant.FrameConstant.FRAME_HEIGHT;

public class Over extends BaseSprite implements Drawable, Moveable {
    private Image image;
    private int speed = FrameConstant.GAME_SPEED;

    public Over() {
        this(338,148, ImageMap.get("over"));
    }

    public Over(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
    }

    @Override
    public void move()  {
        setY(getY() + FrameConstant.GAME_SPEED);
    }
}
