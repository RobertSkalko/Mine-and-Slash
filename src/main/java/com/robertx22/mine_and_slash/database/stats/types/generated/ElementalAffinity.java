package com.robertx22.mine_and_slash.database.stats.types.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.types.ElementalStat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.ICoreStat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class ElementalAffinity extends ElementalStat implements ICoreStat {

    public ElementalAffinity(Elements element) {
        super(element);

    }

    @Override
    public boolean IsShownOnStatGui() {
        return false;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
    }

    @Override
    public Stat newGeneratedInstance(Elements element) {
        return new ElementalAffinity(element);
    }

    @Override
    public float amountToReach100Percent() {
        return 0;
    }

    @Override
    public void addToOtherStats(EntityCap.UnitData unitdata, StatData data) {
        for (StatMod statmod : this.statsThatBenefit()) {
            unitdata.getUnit().getStat(statmod.GetBaseStat()).Flat += data.Value;
        }
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Resist and Spell Damage of that element.";
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "elemental_affinity";
    }

    @Override
    public String GUID() {
        return Element().toString() + "_affinity";
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new ElementalResistFlat(this.Element()), new ElementalSpellDamageFlat(this
                .Element()));
    }

    @Override
    public String locNameForLangFile() {
        return Element().name() + " Affinity";
    }

}
