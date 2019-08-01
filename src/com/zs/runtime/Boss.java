package com.zs.runtime;

import com.zs.base.BaseSprite;
import com.zs.base.Drawable;
import com.zs.util.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class Boss extends BaseSprite implements Drawable {
    public static boolean live = true;
    private List<Image> imageList = new ArrayList<>();

    public Boss() {
        for (int i = 1; i < 10; i++) {
            imageList.add(ImageMap.get("boss"+i));
            
        }
    }

    int index = 0;
    @Override
    public void draw(Graphics g) {
        g.drawImage(imageList.get(index++ / 2),100,100,imageList.get(0).getWidth(null),
                imageList.get(0).getHeight(null),null);
        if (index >= 18){
            index = 0;
        }
    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),imageList.get(0).getWidth(null),imageList.get(0).getHeight(null));
    }

}
