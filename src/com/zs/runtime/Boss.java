package com.zs.runtime;

import com.zs.base.BaseSprite;
import com.zs.base.Drawable;
import com.zs.main.GameFrame;
import com.zs.util.DataStore;
import com.zs.util.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boss extends BaseSprite implements Drawable {
    public static boolean live = true;
    private List<Image> imageList = new ArrayList<>();
    private Random random = new Random();
    private Image image;

    public Boss() {
        for (int i = 1; i < 10; i++) {
            imageList.add(ImageMap.get("boss" + i));
        }
        this.image = ImageMap.get("bossb1");

    }

    int index = 0;

    @Override
    public void draw(Graphics g) {
        fire();
        g.drawImage(imageList.get(index++ / 2), 100, 100, imageList.get(0).getWidth(null),
                imageList.get(0).getHeight(null), null);
        if (index >= 18) {
            index = 0;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), imageList.get(0).getWidth(null), imageList.get(0).getHeight(null));
    }

    public void fire() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (this.getY() > -150) {
            if (random.nextInt(1000) > 992) {
                gameFrame.bossBullets.add(new BossBullet(getX() + (image.getWidth(null)) - ImageMap.get("bossb1").getWidth(null),
                        getY() + image.getHeight(null),
                        ImageMap.get("bossb1")));
            }
        }
    }

}
