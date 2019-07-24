package com.robertx22.mine_and_slash.database.stats.stat_types.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.ElementalStat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class ElementalSpellDamage extends ElementalStat {

    public ElementalSpellDamage(Elements element) {
        super(element);
    }

    @Override
    public Stat.StatGroup statGroup() {
        return Stat.StatGroup.SpellDamage;
    }

    @Override
    public Stat newGeneratedInstance(Elements element) {
        return new ElementalSpellDamage(element);
    }

    @Override
    public String GUID() {
        return "Spell " + this.Element().name() + " Damage";
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "ele_spell_damage";
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
        return "Spell " + this.Element().name() + " Damage";

    }

    @Override
    public String locDescForLangFile() {
        return "Spell damage is used by spells and some other stats";
    }

}

