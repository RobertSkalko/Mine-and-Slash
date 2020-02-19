package com.robertx22.mine_and_slash.database.runes;

import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaFlat;

import java.util.Arrays;
import java.util.List;

public class Voh extends BaseRune {
    public Voh(int rarity) {
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

}
