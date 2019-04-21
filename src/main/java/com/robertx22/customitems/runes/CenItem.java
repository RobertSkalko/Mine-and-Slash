package com.robertx22.customitems.runes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stat_mods.percent.HealthPercent;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class CenItem extends BaseRuneItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public CenItem(int rarity) {
	super(rarity);

    }

    @Override
    public String name() {
	return "CEN";
    }

    @Override
    public List<StatMod> weaponStat() {
	return Arrays.asList(new CriticalHitFlat());
    }

    @Override
    public List<StatMod> armorStat() {
	return Arrays.asList(new HealthPercent());
    }

    @Override
    public List<StatMod> jewerlyStat() {
	return this.peneFlats();
    }

    @Override
    public BaseRuneItem byRarity(int rar) {
	return (BaseRuneItem) Items.get(rar);
    }

}