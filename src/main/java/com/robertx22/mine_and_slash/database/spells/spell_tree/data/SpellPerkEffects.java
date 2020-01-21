package com.robertx22.mine_and_slash.database.spells.spell_tree.data;

import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.InstantHealSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.BlizzardSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.FrostballSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderspearSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderstormSpell;
import com.robertx22.mine_and_slash.database.spells.spell_tree.SpellPerkEffect;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.talent_tree.PerkType;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class SpellPerkEffects {

    public static SpellPerkEffect START;

    static SpellPerkEffect FROSTBALL;
    static SpellPerkEffect BLIZZARD;

    static SpellPerkEffect THUNDER_STORM;
    static SpellPerkEffect THUNDER_SPEAR;

    static SpellPerkEffect INSTANT_HEAL;

    public static void register() {

        START = new SpellPerkEffect(
                "start", new ExactStatData(0, StatTypes.Flat, CriticalHit.GUID), "starts/mage").setStart();

        FROSTBALL = new SpellPerkEffect(new FrostballSpell()).setGameChanger().type(PerkType.MAJOR);
        BLIZZARD = new SpellPerkEffect(new BlizzardSpell()).setGameChanger().type(PerkType.MAJOR);
        THUNDER_STORM = new SpellPerkEffect(new ThunderstormSpell()).setGameChanger().type(PerkType.MAJOR);
        THUNDER_SPEAR = new SpellPerkEffect(new ThunderspearSpell()).setGameChanger().type(PerkType.MAJOR);
        INSTANT_HEAL = new SpellPerkEffect(new InstantHealSpell()).setGameChanger().type(PerkType.MAJOR);

    }

}
