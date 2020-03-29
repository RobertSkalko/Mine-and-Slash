package com.robertx22.mine_and_slash.database.stats.types.generated;

import com.robertx22.mine_and_slash.database.stats.ConversionMethod;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.defense.ElementalResistEffect;
import com.robertx22.mine_and_slash.database.stats.types.ElementalStat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatConversion;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

import java.util.Arrays;
import java.util.List;

public class ElementalResist extends ElementalStat implements IStatEffects, IStatConversion {

    public static MapWrapper<Elements, ElementalResist> MAP = new MapWrapper();

    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Stat> list = super.generateAllPossibleStatVariations();
        list.forEach(x -> MAP.put(x.getElement(), (ElementalResist) x));
        return list;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.SLOW_SCALING;
    }

    @Override
    public Stat.StatGroup statGroup() {
        return Stat.StatGroup.Defenses;
    }

    public ElementalResist(Elements element) {
        super(element);
        this.minimumValue = 0;

        this.maximumValue = 75;
    }

    @Override
    public Stat newGeneratedInstance(Elements element) {
        return new ElementalResist(element);
    }

    @Override
    public String getIconPath() {
        return "ele_res/" + element.guidName;
    }

    @Override
    public String GUID() {
        return this.getElement().guidName + "_resist";
    }

    @Override
    public String locDescForLangFile() {
        return "Stops X percent damage of that element";
    }

    @Override
    public IStatEffect getEffect() {
        return new ElementalResistEffect();
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "ele_resist";
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public String locNameForLangFile() {
        return this.getElement()
            .name() + " Resist";
    }

    @Override
    public boolean IsShownOnStatGui() {
        return getElement() != Elements.Elemental;
    }

    @Override
    public List<ConversionMethod> conversion() {

        if (this.getElement()
            .equals(Elements.Elemental)) {
            return Arrays.asList(
                new ConversionMethod(new ElementalResist(Elements.Elemental), new ElementalResist(Elements.Nature)),
                new ConversionMethod(new ElementalResist(Elements.Elemental), new ElementalResist(Elements.Fire)),
                new ConversionMethod(new ElementalResist(Elements.Elemental), new ElementalResist(Elements.Thunder)),
                new ConversionMethod(new ElementalResist(Elements.Elemental), new ElementalResist(Elements.Water))
            );
        }

        return Arrays.asList();
    }

}

