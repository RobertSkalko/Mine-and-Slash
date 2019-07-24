package com.robertx22.mine_and_slash.items.runes;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
