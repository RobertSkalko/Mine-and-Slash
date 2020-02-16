package com.robertx22.mine_and_slash.database.items.runes;

import com.robertx22.mine_and_slash.database.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ArmorPercent;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        return Arrays.asList(new ArmorPercent(), new DodgeRatingFlat());
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