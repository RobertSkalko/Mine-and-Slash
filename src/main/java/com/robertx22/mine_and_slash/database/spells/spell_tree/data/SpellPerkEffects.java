package com.robertx22.mine_and_slash.database.spells.spell_tree.data;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_tree.SpellPerkEffect;
import com.robertx22.mine_and_slash.database.spells.spell_tree.SynergyPerkEffect;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.talent_tree.PerkType;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class SpellPerkEffects {

    public static SpellPerkEffect START;

    public static void register() {

        START = new SpellPerkEffect(
                "start", new ExactStatData(0, StatTypes.Flat, CriticalHit.GUID), "starts/mage").setStart()
                .setGameChanger()
                .type(PerkType.MAJOR);

        SlashRegistry.Spells().getList().forEach(x -> spell(x)); // registers all spells as perks

        synergy(Synergies.FROSTBALL_EXTRA_DMG);
        synergy(Synergies.FROSTBALL_FROST_ESSENCE_GEN);
        synergy(Synergies.BLIZZARD_FROST);
        synergy(Synergies.WHIRLPOOL_FROST_DMG);
        synergy(Synergies.HEART_OF_ICE_FROST);

        synergy(Synergies.THORN_ARMOR_THORNS);

        synergy(Synergies.THUNDER_SPEAR_LIGHTNING);
        synergy(Synergies.LIGHTNING_TOTEM_STATIC);

        synergy(Synergies.RIGHTEOUS_FURY_AOE);

        synergy(Synergies.FIREBALL_BURN_CONSUME);
        synergy(Synergies.VOLCANO_BURN);

    }

    static SpellPerkEffect spell(BaseSpell spell) {
        return new SpellPerkEffect(spell).setGameChanger().type(PerkType.MAJOR);
    }

    static SynergyPerkEffect synergy(Synergy synergy) {
        return new SynergyPerkEffect(synergy).setGameChanger().type(PerkType.BIG);
    }

}
