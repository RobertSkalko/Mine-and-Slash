package com.robertx22.customitems.runes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaFlat;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class VohItem extends BaseRuneItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public VohItem(int rarity) {
	super(rarity);

    }

    @Override
    public String name() {
	return "VOH";
    }

    @Override
    public List<StatMod> weaponStat() {
	return this.peneFlats();
    }

    @Override
    public List<StatMod> armorStat() {
	return Arrays.asList(new ManaFlat());
    }

    @Override
    public List<StatMod> jewerlyStat() {
	return Arrays.asList(new CriticalHitFlat());
    }

    @Override
    public BaseRuneItem byRarity(int rar) {
	return (BaseRuneItem) Items.get(rar);
    }

}
