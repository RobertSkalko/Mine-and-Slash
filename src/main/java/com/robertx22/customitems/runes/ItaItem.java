package com.robertx22.customitems.runes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.stat_mods.percent.ArmorPercent;
import com.robertx22.database.stat_mods.percent.PhysicalDamagePercent;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class ItaItem extends BaseRuneItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItaItem(int rarity) {
	super(rarity);

    }

    @Override
    public String name() {
	return "ITA";
    }

    @Override
    public List<StatMod> weaponStat() {
	return Arrays.asList(new PhysicalDamagePercent());
    }

    @Override
    public List<StatMod> armorStat() {
	return Arrays.asList(new ArmorPercent());
    }

    @Override
    public List<StatMod> jewerlyStat() {
	return this.spellDamageFlats();
    }

    @Override
    public BaseRuneItem byRarity(int rar) {
	return (BaseRuneItem) Items.get(rar);
    }

}
