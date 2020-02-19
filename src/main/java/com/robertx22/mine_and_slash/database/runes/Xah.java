package com.robertx22.mine_and_slash.database.runes;

import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifestealFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;

import java.util.Arrays;
import java.util.List;

public class Xah extends BaseRune {

    public Xah(int rarity) {
        super(rarity);

    }

    @Override
    public String name() {
        return "XAH";
    }

    @Override
    public List<StatMod> weaponStat() {
        return Arrays.asList(new LifestealFlat());
    }

    @Override
    public List<StatMod> armorStat() {
        return this.spellDamagePercents();
    }

    @Override
    public List<StatMod> jewerlyStat() {
        return Arrays.asList(new HealthRegenFlat(), new MagicShieldRegenFlat());
    }

}