package com.robertx22.customitems.runes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stat_mods.percent.CriticalHitPercent;
import com.robertx22.database.stat_mods.percent.HealthRegenPercent;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

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