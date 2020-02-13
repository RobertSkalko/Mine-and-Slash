package com.robertx22.mine_and_slash.database.map_affixes.detrimental;

import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.BaseElementalMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.minus.LessEleDmgMap;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IElementalGenerated;

import java.util.Arrays;
import java.util.List;

public class LessEleDmgAffix extends BaseElementalMapAffix {

    public LessEleDmgAffix(Elements element) {
        super(element);
    }

    @Override
    public IElementalGenerated<StatMod> getGenStat() {
        return new LessEleDmgMap(Elements.Nature);
    }

    @Override
    public String GUID() {
        return "less_all_" + element.guidName + "_damage_affix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(getGenStat().newGeneratedInstance(element), percent));
    }

    @Override
    public boolean isBeneficial() {
        return false;
    }

    @Override
    public BaseMapAffix newGeneratedInstance(Elements element) {
        return new LessEleDmgAffix(element);
    }
}
