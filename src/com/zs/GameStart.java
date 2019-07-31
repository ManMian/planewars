package com.zs;

import com.zs.main.GameFrame;
import com.zs.util.DataStore;

public class GameStart {
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        DataStore.put("gameFrame",gameFrame);
        gameFrame.init();
    }
}
