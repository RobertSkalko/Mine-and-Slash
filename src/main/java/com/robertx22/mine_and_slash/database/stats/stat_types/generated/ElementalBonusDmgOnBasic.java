package com.robertx22.mine_and_slash.database.stats.stat_types.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_effects.potion_effects.PotionBonusDmgEffect;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IElementalGenerated;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElementalBonusDmgOnBasic extends Stat implements IElementalGenerated<Stat>, IStatEffects {
    public Elements element;

    public ElementalBonusDmgOnBasic(Elements element) {
        this.element = element;
    }

    @Override
    public String locNameForLangFile() {
        return "Extra " + element.name() + " Basic Dmg";
    }

    @Override
    public String GUID() {
        return element.name().toLowerCase() + "_bonus_dmg_on_basic";
    }

    @Override
    public Stat newGeneratedInstance(Elements element) {
        return new ElementalBonusDmgOnBasic(element);
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public Elements Element() {
        return element;
    }

    @Override
    public String locDescForLangFile() {
        return "Adds dmg on basic attack";
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + formattedGUID();
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new PotionBonusDmgEffect());
    }

    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Stat> list = new ArrayList<>();
        Elements.getAllSingleElements().forEach(x -> list.add(newGeneratedInstance(x)));
        return list;
    }

}
