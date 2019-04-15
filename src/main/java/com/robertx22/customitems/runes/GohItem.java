package com.robertx22.customitems.runes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stat_mods.percent.ArmorPercent;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class GohItem extends BaseRuneItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public GohItem(int rarity) {
	super(rarity);

    }

    @Override
    public String name() {
	return "GOH";
    }

    @Override
    public List<StatMod> weaponStat() {
	return Arrays.asList(new EnergyRegenFlat());
    }

    @Override
    public List<StatMod> armorStat() {
	return Arrays.asList(new ArmorPercent());
    }

    @Override
    public List<StatMod> jewerlyStat() {
	return this.spellDamagePercents();
    }

    @Override
    public BaseRuneItem byRarity(int rar) {
	return (BaseRuneItem) Items.get(rar);
    }

}