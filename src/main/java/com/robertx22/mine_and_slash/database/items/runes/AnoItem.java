package com.robertx22.mine_and_slash.database.items.runes;

import com.robertx22.mine_and_slash.database.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.LifeOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AnoItem extends BaseRuneItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public AnoItem(int rarity) {
        super(rarity);

    }

    @Override
    public String name() {
        return "ANO";
    }

    @Override
    public List<StatMod> weaponStat() {
        return Arrays.asList(new LifeOnHitFlat());
    }

    @Override
    public List<StatMod> armorStat() {
        return this.peneFlats();
    }

    @Override
    public List<StatMod> jewerlyStat() {
        return Arrays.asList(new ManaRegenFlat());
    }

    @Override
    public BaseRuneItem byRarity(int rar) {
        return (BaseRuneItem) Items.get(rar);
    }

}