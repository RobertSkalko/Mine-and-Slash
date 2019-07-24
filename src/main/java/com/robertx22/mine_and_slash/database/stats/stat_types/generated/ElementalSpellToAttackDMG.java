package com.robertx22.mine_and_slash.database.stats.stat_types.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_effects.offense.SpellToBasicDamageEffect;
import com.robertx22.mine_and_slash.database.stats.stat_types.ElementalStat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class ElementalSpellToAttackDMG extends ElementalStat implements IStatEffects {

    public ElementalSpellToAttackDMG(Elements element) {
        super(element);
    }

    @Override
    public Stat.StatGroup statGroup() {
        return Stat.StatGroup.EleAttackDamage;
    }

    @Override
    public Stat newGeneratedInstance(Elements element) {
        return new ElementalAttackDamage(element);
    }

    @Override
    public String locDescForLangFile() {
        return "Adds a % of your spell DMG as DMG to your every weapon hit";
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new SpellToBasicDamageEffect());
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "ele_spell_to_attack_dmg";
    }

    public Stat StatThatGiveDamage() {
        return new ElementalSpellDamage(this.Element());
    }

    @Override
    public String locNameForLangFile() {
        return this.Element().name() + " Spell to Atk Dmg";
    }

    @Override
    public String GUID() {
        return Element().name() + " Spell to Attack DMG";
    }
}