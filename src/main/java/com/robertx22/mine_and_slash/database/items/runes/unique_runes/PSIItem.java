package com.robertx22.mine_and_slash.database.items.runes.unique_runes;

import com.robertx22.mine_and_slash.database.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.items.runes.base.BaseUniqueRuneItem;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class PSIItem extends BaseUniqueRuneItem {

    public static BaseRuneItem item = null;

    public PSIItem() {
        super(IRarity.Unique);
    }

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new CriticalDamageFlat().multi(2));
    }

    @Override
    public BaseRuneItem byRarity(int rar) {
        return item;
    }

    @Override
    public String name() {
        return "PSI";
    }

}
