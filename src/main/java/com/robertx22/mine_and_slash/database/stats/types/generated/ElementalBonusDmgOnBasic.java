package com.robertx22.mine_and_slash.database.stats.types.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.potion_effects.PotionBonusDmgEffect;
import com.robertx22.mine_and_slash.database.stats.types.ElementalStat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

import java.util.List;

public class ElementalBonusDmgOnBasic extends ElementalStat implements IStatEffects {

    public static MapWrapper<Elements, ElementalBonusDmgOnBasic> MAP = new MapWrapper();

    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Stat> list = super.generateAllPossibleStatVariations();
        list.forEach(x -> MAP.put(x.getElement(), (ElementalBonusDmgOnBasic) x));
        return list;

    }

    public ElementalBonusDmgOnBasic(Elements element) {
        super(element);

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
    public Elements getElement() {
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
    public IStatEffect getEffect() {
        return new PotionBonusDmgEffect();
    }

}
