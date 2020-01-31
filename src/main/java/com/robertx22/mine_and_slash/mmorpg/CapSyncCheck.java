package com.robertx22.mine_and_slash.mmorpg;

import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;

import java.util.HashMap;

public class CapSyncCheck {

    private static HashMap<PlayerCaps, Boolean> map = new HashMap<>();

    public static void set(PlayerCaps type) {
        map.put(type, true);
    }

    public static boolean get(PlayerCaps type) {
        Boolean bool = map.getOrDefault(type, false);
        map.put(type, false);
        return bool;

    }
}
