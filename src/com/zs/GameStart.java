package com.zs;

import com.zs.main.GameFrame;
import com.zs.runtime.Plane;
import com.zs.runtime.Props;
import com.zs.util.DataStore;

public class GameStart {
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        DataStore.put("gameFrame",gameFrame);
        gameFrame.init();;
    }
}
