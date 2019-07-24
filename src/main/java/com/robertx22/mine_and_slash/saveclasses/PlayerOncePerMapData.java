package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;

@Storable
public class PlayerOncePerMapData {

    @Store
    public HashMap<String, String> playerGUIDandMapGUIDMap = new HashMap<>();

    public void add(PlayerEntity player) {
        playerGUIDandMapGUIDMap.put(getID(player), Load.playerMapData(player)
                .getLastMapGUID());
    }

    public boolean canDo(PlayerEntity player) {

        String key = getID(player);

        if (playerGUIDandMapGUIDMap.containsKey(key) == false) {
            return true;
        } else {

            String lastmapID = playerGUIDandMapGUIDMap.get(key);

            if (lastmapID.equals(Load.playerMapData(player).getLastMapGUID())) {
                return false; // can't do more than 1 per map

            } else {
                return true;
            }
        }

    }

    private String getID(PlayerEntity player) {
        return player.getUniqueID().toString();

    }

}
