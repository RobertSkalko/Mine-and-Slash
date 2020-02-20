package com.robertx22.mine_and_slash.database.runes.base;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.List;
import java.util.Locale;

public abstract class BaseUniqueRune extends BaseRune {

    public BaseUniqueRune() {
        super(IRarity.Unique);
        this.isUnique = true;
    }

    @Override
    public String genRegistryName(int rar) {
        return "runes/uniques/" + name().toLowerCase(Locale.ROOT);
    }

    @Override
    public int Weight() {
        return getRarity().Weight();
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