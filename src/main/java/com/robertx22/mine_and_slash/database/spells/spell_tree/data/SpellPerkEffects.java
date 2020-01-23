package com.robertx22.mine_and_slash.database.spells.spell_tree.data;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.InstantHealSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.RegenerateSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.BlazingInfernoSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.FireballSpell;
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

    static SpellPerkEffect REGENERATE;

    static SpellPerkEffect BLAZING_INFERNO;
    static SpellPerkEffect FIREBALL;

    public static void register() {

        START = new SpellPerkEffect(
                "start", new ExactStatData(0, StatTypes.Flat, CriticalHit.GUID), "starts/mage").setStart();

        FROSTBALL = spell(new FrostballSpell());
        BLIZZARD = spell(new BlizzardSpell());

        THUNDER_STORM = spell(new ThunderstormSpell());
        THUNDER_SPEAR = spell(new ThunderspearSpell());

        INSTANT_HEAL = spell(new InstantHealSpell());

        REGENERATE = spell(new RegenerateSpell());

        BLAZING_INFERNO = spell(new BlazingInfernoSpell());
        FIREBALL = spell(new FireballSpell());

    }

    static SpellPerkEffect spell(BaseSpell spell) {
        return new SpellPerkEffect(spell).setGameChanger().type(PerkType.MAJOR);
    }

}
