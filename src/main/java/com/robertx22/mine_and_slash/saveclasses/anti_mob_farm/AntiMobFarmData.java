package com.robertx22.mine_and_slash.saveclasses.anti_mob_farm;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.ChunkPos;

import java.util.HashMap;

@Storable
public class AntiMobFarmData {

    @Store
    private HashMap<String, AntiMobFarmChunkData> map = new HashMap<String, AntiMobFarmChunkData>();

    public void onValidMobDeathByPlayer(LivingEntity en) {

        String key = getKey(en);

        AntiMobFarmChunkData data = map.getOrDefault(key, new AntiMobFarmChunkData());
        data.kills++;
        map.put(key, data);
    }

    public void tickDownAllKillCounters() {

        map.entrySet()
            .forEach(x -> x.getValue().kills--);

    }

    private String getKey(LivingEntity en) {
        ChunkPos cp = new ChunkPos(en.getPosition());
        return cp.x + "_" + cp.z;
    }

}