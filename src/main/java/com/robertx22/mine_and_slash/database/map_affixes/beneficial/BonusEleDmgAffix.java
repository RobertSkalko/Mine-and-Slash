package com.robertx22.mine_and_slash.database.map_affixes.beneficial;

import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus.BonusEleDamageMap;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IElementalGenerated;

import java.util.Arrays;
import java.util.List;

public class BonusEleDmgAffix extends BaseElementalMapAffix {

    public BonusEleDmgAffix(Elements element) {
        super(element);
    }

    @Override
    public IElementalGenerated<StatMod> getGenStat() {
        return new BonusEleDamageMap(Elements.Nature);
    }

    @Override
    public String GUID() {
        return "bonus_" + element.guidName + "_damage_affix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(getGenStat().newGeneratedInstance(element), percent));
    }

    @Override
    public boolean isBeneficial() {
        return true;
    }

    @Override
    public BaseMapAffix newGeneratedInstance(Elements element) {
        return new BonusEleDmgAffix(element);
    }
}
