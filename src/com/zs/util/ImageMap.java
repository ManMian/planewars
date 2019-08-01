package com.zs.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    private static final Map<String, Image> map = new HashMap<>();
    static{
        map.put("bg01", ImageUtil.getImage("com/zs/imgs/bg/bg01.png"));

        map.put("my01", ImageUtil.getImage("com\\zs\\imgs\\plane\\my_1.png"));
        map.put("my02", ImageUtil.getImage("com\\zs\\imgs\\plane\\my_2.png"));

        map.put("mb01", ImageUtil.getImage("com\\zs\\imgs\\bullet\\mb_1.png"));
        map.put("mb02", ImageUtil.getImage("com\\zs\\imgs\\bullet\\mb_2.png"));

        map.put("ep01", ImageUtil.getImage("com\\zs\\imgs\\plane\\ep_1.png"));
        map.put("ep02", ImageUtil.getImage("com\\zs\\imgs\\plane\\ep_2.png"));

        map.put("epb01", ImageUtil.getImage("com\\zs\\imgs\\bullet\\epb_1.png"));

        map.put("blood", ImageUtil.getImage("com\\zs\\imgs\\blood\\blood.png"));

        map.put("boss1", ImageUtil.getImage("com\\zs\\imgs\\boss\\boss_A_01.png"));
        map.put("boss2", ImageUtil.getImage("com\\zs\\imgs\\boss\\boss_A_02.png"));
        map.put("boss3", ImageUtil.getImage("com\\zs\\imgs\\boss\\boss_A_03.png"));
        map.put("boss4", ImageUtil.getImage("com\\zs\\imgs\\boss\\boss_A_04.png"));
        map.put("boss5", ImageUtil.getImage("com\\zs\\imgs\\boss\\boss_A_05.png"));
        map.put("boss6", ImageUtil.getImage("com\\zs\\imgs\\boss\\boss_A_06.png"));
        map.put("boss7", ImageUtil.getImage("com\\zs\\imgs\\boss\\boss_A_07.png"));
        map.put("boss8", ImageUtil.getImage("com\\zs\\imgs\\boss\\boss_A_08.png"));
        map.put("boss9", ImageUtil.getImage("com\\zs\\imgs\\boss\\boss_A_09.png"));

        map.put("props1", ImageUtil.getImage("com\\zs\\imgs\\props\\props1.png"));
        map.put("props2", ImageUtil.getImage("com\\zs\\imgs\\props\\props2.png"));
    }
    public static Image get(String key){
        return map.get(key);
    }

}
