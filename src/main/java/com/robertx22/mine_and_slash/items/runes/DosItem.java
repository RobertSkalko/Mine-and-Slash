package com.robertx22.mine_and_slash.items.runes;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DosItem extends BaseRuneItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public DosItem(int rarity) {
        super(rarity);

    }

    @Override
    public String name() {
        return "DOS";
    }

    @Override
    public List<StatMod> weaponStat() {
        return Arrays.asList(new PhysicalDamagePercent());
    }

    @Override
    public List<StatMod> armorStat() {
        return this.penePercents();
    }

    @Override
    public List<StatMod> jewerlyStat() {
        return Arrays.asList(new EnergyRegenFlat());
    }

    @Override
    public BaseRuneItem byRarity(int rar) {
        return (BaseRuneItem) Items.get(rar);
    }

}