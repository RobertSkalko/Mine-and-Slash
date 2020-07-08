package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatModsContainer;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Storable
public abstract class AffixData implements IStatModsContainer, IRerollable {

    @Store
    public Integer percent = 0;

    @Store
    public String baseAffix;

    public abstract BaseAffix BaseAffix();

    @Override
    public List<LevelAndStats> GetAllStats(int level) {

        if (percent == 0 || baseAffix == null || baseAffix.isEmpty() || BaseAffix() == null) {
            return new ArrayList<>();
        }

        BaseAffix base = BaseAffix();

        List<StatModData> list = new ArrayList<StatModData>();

        for (int i = 0; i < base.StatMods()
            .size(); i++) {

            StatMod mod = base.StatMods()
                .get(i);

            list.add(StatModData.Load(mod, percent));
        }

        return Arrays.asList(new LevelAndStats(list, level));

    }

}
