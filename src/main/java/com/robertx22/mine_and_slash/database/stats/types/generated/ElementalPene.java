package com.robertx22.mine_and_slash.database.stats.types.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.ElementalPeneEffect;
import com.robertx22.mine_and_slash.database.stats.types.ElementalStat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

import java.util.List;

public class ElementalPene extends ElementalStat implements IStatEffects {
    public static MapWrapper<Elements, ElementalPene> MAP = new MapWrapper();

    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Stat> list = super.generateAllPossibleStatVariations();
        list.add(new ElementalPene(Elements.Physical));
        list.forEach(x -> MAP.put(x.getElement(), (ElementalPene) x));
        return list;

    }

    @Override
    public Stat.StatGroup statGroup() {
        return Stat.StatGroup.Penetration;
    }

    public ElementalPene(Elements element) {
        super(element);
        this.minimumValue = 0;

    }

    @Override
    public Stat newGeneratedInstance(Elements element) {

        return new ElementalPene(element);
    }

    @Override
    public String getIconPath() {
        return "pene/" + element.guidName;
    }

    @Override
    public String GUID() {
        return this.getElement().guidName + "_penetration";
    }

    @Override
    public String locDescForLangFile() {
        return "Penetration ignores that much armor";
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "ele_pene";
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public String locNameForLangFile() {
        return this.getElement().name() + " Penetration";
    }

    @Override
    public IStatEffect getEffect() {
        return new ElementalPeneEffect();
    }

}
