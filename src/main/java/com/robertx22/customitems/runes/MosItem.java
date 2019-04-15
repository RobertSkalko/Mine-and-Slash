package com.robertx22.customitems.runes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class MosItem extends BaseRuneItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public MosItem(int rarity) {
	super(rarity);

    }

    @Override
    public String name() {
	return "MOS";
    }

    @Override
    public List<StatMod> weaponStat() {
	return Arrays.asList(new CriticalDamageFlat());
    }

    @Override
    public List<StatMod> armorStat() {
	return Arrays.asList(new ArmorFlat());
    }

    @Override
    public List<StatMod> jewerlyStat() {
	return this.resistFlats();
    }

    @Override
    public BaseRuneItem byRarity(int rar) {
	return (BaseRuneItem) Items.get(rar);
    }

}
