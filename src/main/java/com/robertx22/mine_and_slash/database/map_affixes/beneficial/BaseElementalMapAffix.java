package com.robertx22.mine_and_slash.database.map_affixes.beneficial;

import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IElementalGenerated;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseElementalMapAffix extends BaseMapAffix implements IElementalGenerated<BaseMapAffix> {

    public Elements element;

    public BaseElementalMapAffix(Elements element) {
        this.element = element;

    }

    public abstract IGenerated<StatMod> getGenStat();

    @Override
    public final List<BaseMapAffix> generateAllPossibleStatVariations() {

        List<BaseMapAffix> list = new ArrayList<>();

        List<StatMod> all = getGenStat().generateAllPossibleStatVariations();

        for (StatMod sg : all) {
            list.add(this.newGeneratedInstance(sg.GetBaseStat().getElement()));
        }

        return list;

    }
}
