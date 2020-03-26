package com.robertx22.mine_and_slash.database.stats.mods.flat.corestats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.*;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.BaseCoreStat;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseCoreStatFlat extends StatMod implements IGenerated<StatMod> {

    BaseCoreStat stat;

    public BaseCoreStatFlat(BaseCoreStat stat) {
        this.stat = stat;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public Stat GetBaseStat() {
        return stat;
    }

    @Override
    public int Weight() {
        return super.Weight();
    }

    public abstract BaseCoreStatFlat newGeneratedInstance(BaseCoreStat stat);

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {

        List<StatMod> list = new ArrayList<>();
        Arrays.asList(Dexterity.INSTANCE, Stamina.INSTANCE, Intelligence.INSTANCE, Wisdom.INSTANCE, Vitality.INSTANCE,
            Strength.INSTANCE
        )
            .forEach(x -> list.add(newGeneratedInstance(x)));
        return list;
    }
}
