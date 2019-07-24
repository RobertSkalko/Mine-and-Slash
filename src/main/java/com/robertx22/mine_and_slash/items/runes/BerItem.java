package com.robertx22.mine_and_slash.items.runes;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.DodgeFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.HealthRegenPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.CriticalHitPercent;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BerItem extends BaseRuneItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public BerItem(int rarity) {
        super(rarity);

    }

    @Override
    public String name() {
        return "BER";
    }

    @Override
    public List<StatMod> weaponStat() {
        return Arrays.asList(new CriticalHitPercent());
    }

    @Override
    public List<StatMod> armorStat() {
        return Arrays.asList(new DodgeFlat());
    }

    @Override
    public List<StatMod> jewerlyStat() {
        return Arrays.asList(new HealthRegenPercent());
    }

    @Override
    public BaseRuneItem byRarity(int rar) {
        return (BaseRuneItem) Items.get(rar);
    }

}