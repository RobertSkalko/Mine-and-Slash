package com.robertx22.mine_and_slash.items.runes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.ArmorPercent;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;

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