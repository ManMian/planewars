package com.zs.runtime;

import com.zs.base.BaseSprite;
import com.zs.base.Drawable;
import com.zs.base.Moveable;
import com.zs.util.ImageMap;
import com.zs.util.ImageUtil;

import java.awt.*;

public class Background extends BaseSprite implements Drawable, Moveable {
    private Image image;

    public Background() {
        this(0,0, ImageMap.get("bg01"));
    }

    public Background(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void move() {
        setY(getY() - 1);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
    }
}
