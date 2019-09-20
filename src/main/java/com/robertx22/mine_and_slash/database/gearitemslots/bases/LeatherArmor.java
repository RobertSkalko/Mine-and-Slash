package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.DodgeFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.DexterityFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.StaminaFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;

import java.util.Arrays;
import java.util.List;

public abstract class LeatherArmor extends BaseArmor {

    @Override
    public List<StatMod> PrimaryStats() {
        return Arrays.asList(new HealthFlat());
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return Arrays.asList(new ArmorFlat().multi(0.75F), new DodgeFlat(), new StaminaFlat(), new DexterityFlat());
    }

}
