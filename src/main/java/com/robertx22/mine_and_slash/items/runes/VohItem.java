package com.robertx22.mine_and_slash.items.runes;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
