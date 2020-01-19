package com.robertx22.mine_and_slash.mmorpg;

import com.robertx22.mine_and_slash.network.sync_cap.CapTypes;

import java.util.HashMap;

public class CapSyncCheck {

    private static HashMap<CapTypes, Boolean> map = new HashMap<>();

    public static void set(CapTypes type) {
        map.put(type, true);
    }

    public static boolean get(CapTypes type) {
        Boolean bool = map.getOrDefault(type, false);
        map.put(type, false);
        return bool;

    }
}
