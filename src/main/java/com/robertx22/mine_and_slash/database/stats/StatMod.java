package com.robertx22.mine_and_slash.database.stats;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

public abstract class StatMod implements IWeighted, IRarity, IGUID, ISlashRegistryEntry<StatMod> {

    public float multiplier = 1F;

    public float sizeMultiplier() {
        return multiplier;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.STATMOD;
    }

    @Override
    public int Weight() {
        return this.getRarity().Weight();
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Items.get(getRarityRank());
    }

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public int getRarityRank() {
        return 1;
    }

    public abstract Stat GetBaseStat();

    public abstract float Min();

    public abstract float Max();

    public abstract StatTypes Type();

    @Override
    public String GUID() {
        return this.GetBaseStat().GUID() + "_" + this.Type().name();
    }

    public float GetFloatByPercent(int percent) {
        return (Min() + (Max() - Min()) * percent / 100) * multiplier;
    }

    public StatMod multi(float multiplier) {
        this.multiplier = multiplier;
        return this;
    }

}