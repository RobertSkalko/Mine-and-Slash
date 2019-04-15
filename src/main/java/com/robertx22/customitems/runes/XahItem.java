package com.robertx22.customitems.runes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class XahItem extends BaseRuneItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public XahItem(int rarity) {
	super(rarity);

    }

    @Override
    public String name() {
	return "XAH";
    }

    @Override
    public List<StatMod> weaponStat() {
	return Arrays.asList(new LifestealFlat());
    }

    @Override
    public List<StatMod> armorStat() {
	return this.spellDamagePercents();
    }

    @Override
    public List<StatMod> jewerlyStat() {
	return Arrays.asList(new HealthRegenFlat());
    }

    @Override
    public BaseRuneItem byRarity(int rar) {
	return (BaseRuneItem) Items.get(rar);
    }

}