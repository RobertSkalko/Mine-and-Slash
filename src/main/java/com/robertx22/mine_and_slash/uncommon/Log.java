package com.robertx22.mine_and_slash.uncommon;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;

import java.util.logging.Level;

public class Log {

    public static void error(String str) {
        MMORPG.LOGGER.log(Level.WARNING, str);
    }
}
