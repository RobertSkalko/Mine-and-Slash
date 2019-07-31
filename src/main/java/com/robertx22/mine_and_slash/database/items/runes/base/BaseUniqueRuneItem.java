package com.robertx22.mine_and_slash.database.items.runes.base;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.List;

public abstract class BaseUniqueRuneItem extends BaseRuneItem {

    public BaseUniqueRuneItem(int rarity) {
        super(rarity);

    }

    @Override
    public int Weight() {
        return super.Weight() / 10;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Unique;
    }

    abstract public List<StatMod> mods();

    @Override
    public List<StatMod> weaponStat() {
        return mods();
    }

    @Override
    public List<StatMod> armorStat() {
        return mods();
    }

    @Override
    public List<StatMod> jewerlyStat() {
        return mods();
    }

}