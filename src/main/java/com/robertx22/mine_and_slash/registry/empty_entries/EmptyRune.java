package com.robertx22.mine_and_slash.registry.empty_entries;

import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.stats.StatMod;

import java.util.ArrayList;
import java.util.List;

public class EmptyRune extends BaseRune {
    private EmptyRune(int rarity) {
        super(rarity);
    }

    public static EmptyRune getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String name() {
        return "";
    }

    @Override
    public List<StatMod> weaponStat() {
        return new ArrayList<>();
    }

    @Override
    public List<StatMod> armorStat() {
        return new ArrayList<>();
    }

    @Override
    public List<StatMod> jewerlyStat() {
        return new ArrayList<>();
    }

    private static class SingletonHolder {
        private static final EmptyRune INSTANCE = new EmptyRune(0);
    }
}
