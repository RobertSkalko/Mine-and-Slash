package com.robertx22.mine_and_slash.database.runes;

import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifeOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaRegenFlat;

import java.util.Arrays;
import java.util.List;

public class Ano extends BaseRune {

    public Ano(int rarity) {
        super(rarity);

    }

    @Override
    public String name() {
        return "ANO";
    }

    @Override
    public List<StatMod> weaponStat() {
        return Arrays.asList(new LifeOnHitFlat(), new ManaOnHitFlat());
    }

    @Override
    public List<StatMod> armorStat() {
        return this.peneFlats();
    }

    @Override
    public List<StatMod> jewerlyStat() {
        return Arrays.asList(new ManaRegenFlat());
    }

}