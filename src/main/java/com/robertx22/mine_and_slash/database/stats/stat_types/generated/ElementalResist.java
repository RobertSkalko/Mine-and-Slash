package com.robertx22.mine_and_slash.database.stats.stat_types.generated;

import com.robertx22.mine_and_slash.database.stats.IUsableStat;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_effects.defense.ElementalResistEffect;
import com.robertx22.mine_and_slash.database.stats.stat_types.ElementalStat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class ElementalResist extends ElementalStat implements IStatEffects, IUsableStat {
    @Override
    public Stat.StatGroup statGroup() {
        return Stat.StatGroup.Defenses;
    }

    public ElementalResist(Elements element) {
        super(element);
        this.minimumValue = 0;

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
        return this.Element().name() + " Resist";
    }

    @Override
    public String locDescForLangFile() {
        return "Stops a % damage of that element";
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
    public float MaximumPercent() {
        return 0.8F;
    }

    @Override
    public float AverageStat() {
        return 2.5F;
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public String locNameForLangFile() {
        return this.Element().name() + " Resist";
    }

}

