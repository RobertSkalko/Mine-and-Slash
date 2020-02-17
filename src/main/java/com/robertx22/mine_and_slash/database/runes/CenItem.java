package com.robertx22.mine_and_slash.database.runes;

import com.robertx22.mine_and_slash.database.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.MagicShieldPercent;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CenItem extends BaseRuneItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public CenItem(int rarity) {
        super(rarity);

    }

    @Override
    public String name() {
        return "CEN";
    }

    @Override
    public List<StatMod> weaponStat() {
        return Arrays.asList(new CriticalHitFlat());
    }

    @Override
    public List<StatMod> armorStat() {
        return Arrays.asList(new HealthPercent(), new MagicShieldPercent());
    }

    @Override
    public List<StatMod> jewerlyStat() {
        return this.peneFlats();
    }

    @Override
    public BaseRuneItem byRarity(int rar) {
        return (BaseRuneItem) Items.get(rar);
    }

}
