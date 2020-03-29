package com.robertx22.mine_and_slash.database.runes.unique_runes;

import com.robertx22.mine_and_slash.database.runes.base.BaseUniqueRune;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;

import java.util.Arrays;
import java.util.List;

public class ONI extends BaseUniqueRune {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new ArmorFlat().size(StatMod.Size.DOUBLE));
    }

    @Override
    public String name() {
        return "ONI";
    }

    @Override
    public int getTier() {
        return 4;
    }

}
