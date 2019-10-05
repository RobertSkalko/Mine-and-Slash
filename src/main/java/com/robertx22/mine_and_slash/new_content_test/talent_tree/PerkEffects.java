package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.database.stats.stat_types.core_stats.*;
import com.robertx22.mine_and_slash.database.stats.stat_types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.stat_types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.*;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class PerkEffects {

    public static PerkEffect NOTHING = new PerkEffect(new ExactStatData(0, StatTypes.Flat, CriticalHit.GUID), null);

    public static PerkEffectsWrapper SPELL_DMG = PerkEffectBuilder.build(SpellDamage.INSTANCE, new ExactStatData(2, StatTypes.Flat, SpellDamage.GUID));
    public static PerkEffectsWrapper CRIT_HIT = PerkEffectBuilder.build(CriticalHit.INSTANCE, new ExactStatData(2, StatTypes.Flat, CriticalHit.GUID));
    public static PerkEffectsWrapper CRIT_DMG = PerkEffectBuilder.build(CriticalDamage.INSTANCE, new ExactStatData(3, StatTypes.Flat, CriticalDamage.GUID));

    static int core_amount = 2;

    public static PerkEffectsWrapper INTELLIGENCE = PerkEffectBuilder.build(Intelligence.INSTANCE, new ExactStatData(core_amount, StatTypes.Flat, Intelligence.GUID));
    public static PerkEffectsWrapper WISDOM = PerkEffectBuilder.build(Wisdom.INSTANCE, new ExactStatData(core_amount, StatTypes.Flat, Wisdom.GUID));

    public static PerkEffectsWrapper STAMINA = PerkEffectBuilder.build(Stamina.INSTANCE, new ExactStatData(core_amount, StatTypes.Flat, Stamina.GUID));
    public static PerkEffectsWrapper DEXTERITY = PerkEffectBuilder.build(Dexterity.INSTANCE, new ExactStatData(core_amount, StatTypes.Flat, Dexterity.GUID));

    public static PerkEffectsWrapper VITALITY = PerkEffectBuilder.build(Vitality.INSTANCE, new ExactStatData(core_amount, StatTypes.Flat, Vitality.GUID));
    public static PerkEffectsWrapper STRENGTH = PerkEffectBuilder.build(Strength.INSTANCE, new ExactStatData(core_amount, StatTypes.Flat, Strength.GUID));

    public static PerkEffectsWrapper MAGIC_SHIELD_PERCENT = PerkEffectBuilder.build(MagicShield.INSTANCE, new ExactStatData(4, StatTypes.Percent, MagicShield.GUID));
    public static PerkEffectsWrapper MANA_PERCENT = PerkEffectBuilder.build(Mana.INSTANCE, new ExactStatData(5, StatTypes.Percent, Mana.GUID));
    public static PerkEffectsWrapper HEALTH_PERCENT = PerkEffectBuilder.build(Health.INSTANCE, new ExactStatData(4, StatTypes.Percent, Health.GUID));
    public static PerkEffectsWrapper ENERGY_PERCENT = PerkEffectBuilder.build(Energy.INSTANCE, new ExactStatData(5, StatTypes.Percent, Energy.GUID));
    public static PerkEffectsWrapper DODGE_PERCENT = PerkEffectBuilder.build(DodgeRating.INSTANCE, new ExactStatData(5, StatTypes.Percent, DodgeRating.GUID));
    public static PerkEffectsWrapper ARMOR_PERCENT = PerkEffectBuilder.build(Armor.INSTANCE, new ExactStatData(5, StatTypes.Percent, Armor.GUID));

    public static PerkEffectsWrapper MANA_REGEN_PERCENT = PerkEffectBuilder.build(ManaRegen.INSTANCE, new ExactStatData(5, StatTypes.Percent, ManaRegen.GUID));
    public static PerkEffectsWrapper HEALTH_REGEN_PERCENT = PerkEffectBuilder.build(HealthRegen.INSTANCE, new ExactStatData(5, StatTypes.Percent, HealthRegen.GUID));
    public static PerkEffectsWrapper ENERGY_REGEN_PERCENT = PerkEffectBuilder.build(EnergyRegen.INSTANCE, new ExactStatData(5, StatTypes.Percent, EnergyRegen.GUID));
    public static PerkEffectsWrapper MAGIC_SHIELD_REGEN_PERCENT = PerkEffectBuilder.build(MagicShieldRegen.INSTANCE, new ExactStatData(5, StatTypes.Percent, MagicShieldRegen.GUID));

    public static PerkEffectsWrapper WAND_DMG_PERCENT = PerkEffectBuilder.build(new WeaponDamage(WeaponTypes.Wand), new ExactStatData(2, StatTypes.Percent, new WeaponDamage(WeaponTypes.Wand)
            .GUID()));
    public static PerkEffectsWrapper STAFF_DMG_PERCENT = PerkEffectBuilder.build(new WeaponDamage(WeaponTypes.Staff), new ExactStatData(2, StatTypes.Percent, new WeaponDamage(WeaponTypes.Staff)
            .GUID()));

    // COMBINED EFFECTS
    public static PerkEffectsWrapper MANA_PERC_PLUS_MAGIC_SHIELD_PERCENT = PerkEffectBuilder
            .build("", MAGIC_SHIELD_PERCENT.small(), MANA_PERCENT.small());

}

