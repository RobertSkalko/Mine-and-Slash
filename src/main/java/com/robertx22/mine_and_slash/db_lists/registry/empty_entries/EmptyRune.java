package com.robertx22.mine_and_slash.db_lists.registry.empty_entries;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;

import java.util.ArrayList;
import java.util.List;

public class EmptyRune extends BaseRuneItem {
    public EmptyRune(int rarity) {
        super(rarity);
    }

    @Override
    public BaseRuneItem byRarity(int rar) {
        return new EmptyRune(rar);
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
}
