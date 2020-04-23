package com.robertx22.mine_and_slash.saveclasses.anti_mob_farm;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.math.MathHelper;

@Storable
public class AntiMobFarmChunkData {

    @Store
    public int kills = 0;

    public void tickDown() {
        this.kills = MathHelper.clamp(kills - 1, 0, Integer.MAX_VALUE);
    }

    public int getKills() {
        return kills;
    }

}
