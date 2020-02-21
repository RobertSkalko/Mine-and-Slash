package com.robertx22.mine_and_slash.database.spells.spell_tree.data;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_tree.SpellPerkEffect;
import com.robertx22.mine_and_slash.database.spells.spell_tree.SynergyPerkEffect;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.talent_tree.PerkType;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class SpellPerkEffects {

    public static SpellPerkEffect START;

    public static void register() {

        START = new SpellPerkEffect(
            "start", new ExactStatData(0, StatModTypes.Flat, CriticalHit.GUID), "starts/mage").setStart()
            .setGameChanger()
            .type(PerkType.MAJOR);

        SlashRegistry.Spells()
            .getList()
            .forEach(x -> spell(x)); // registers all spells as perks

        synergy(Synergies.FROSTBALL_EXTRA_DMG);
        synergy(Synergies.FROSTBALL_FROST_ESSENCE_GEN);
        synergy(Synergies.BLIZZARD_FROST);
        synergy(Synergies.WHIRLPOOL_FROST_DMG);
        synergy(Synergies.HEART_OF_ICE_FROST);
        synergy(Synergies.WHIRLPOOL_SHIVER);
        synergy(Synergies.GEYSER_ATTACK);
        synergy(Synergies.HEART_OF_ICE_MAGIC_SHIELD);
        synergy(Synergies.BLIZZARD_FROST_ESSENCE);

        synergy(Synergies.THORN_ARMOR_THORNS);
        synergy(Synergies.THORN_BUSH_MAJOR_TORNS);
        synergy(Synergies.REGEN_AOE);
        synergy(Synergies.REGEN_THORNS);
        synergy(Synergies.POISONED_WEAPONS_THORNS);

        synergy(Synergies.THUNDER_SPEAR_LIGHTNING);
        synergy(Synergies.LIGHTNING_TOTEM_STATIC);
        synergy(Synergies.THUNDER_SPEAR_ESSENCE);
        synergy(Synergies.THUNDER_DASH_ENERGY);

        synergy(Synergies.RIGHTEOUS_FURY_AOE);
        synergy(Synergies.INSTANT_HEAL_REMOVE_DEBUFF);
        synergy(Synergies.INSTANT_HEAL_MAGIC_SHIELD);

        synergy(Synergies.FIREBALL_BURN_CONSUME);
        synergy(Synergies.VOLCANO_BURN);
        synergy(Synergies.MAGMA_FLOWER_HEAL);
        synergy(Synergies.MAGMA_FLOWER_ENHANCED);

        synergy(Synergies.TRIPLE_SHOT_HUNTER);
        synergy(Synergies.IMBUE_CRIT_HUNTER);
        synergy(Synergies.RECOIL_ADD_HUNTER);
        synergy(Synergies.RECOIL_ADD_WOUNDS);

        synergy(Synergies.STEALTH_DISAPPEAR);

    }

    static SpellPerkEffect spell(BaseSpell spell) {
        return new SpellPerkEffect(spell).setGameChanger()
            .type(PerkType.MAJOR);
    }

    static SynergyPerkEffect synergy(Synergy synergy) {
        return new SynergyPerkEffect(synergy).setGameChanger()
            .type(PerkType.BIG);
    }

}
