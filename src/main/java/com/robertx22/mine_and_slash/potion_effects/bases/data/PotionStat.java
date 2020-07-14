package com.robertx22.mine_and_slash.potion_effects.bases.data;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;

public class PotionStat {

    ModType type = ModType.FLAT;
    float value;
    Stat stat;

    public PotionStat(float value, Stat stat) {
        this.value = value;
        this.stat = stat;
    }

    public ExactStatData getExactStat(EntityCap.UnitData data, PlayerSpellCap.ISpellsCap cap, ExtraPotionData pdata, BasePotionEffect effect) {

        float multi = 1;

        float finalVal = pdata.getStacks() * value * multi;

        ExactStatData statData = ExactStatData.scaleTo(finalVal, type, stat.GUID(), data.getLevel());

        return statData;

    }

}
