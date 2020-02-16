package com.robertx22.mine_and_slash.database.map_affixes.beneficial;

import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IElementalGenerated;

import java.util.Arrays;
import java.util.List;

public class BonusEleResistAffix extends BaseElementalMapAffix {

    public BonusEleResistAffix(Elements element) {
        super(element);
    }

    @Override
    public StatMod.Size getSize() {
        return StatMod.Size.VERY_HIGH;
    }

    @Override
    public IElementalGenerated<StatMod> getGenStat() {
        return new ElementalResistFlat(Elements.Nature);
    }

    @Override
    public String GUID() {
        return "bonus_" + element.guidName + "_resist_affix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(getGenStat().newGeneratedInstance(element)
            .size(getSize()), percent));
    }

    @Override
    public boolean isBeneficial() {
        return true;
    }

    @Override
    public BaseMapAffix newGeneratedInstance(Elements element) {
        return new BonusEleResistAffix(element);
    }
}
