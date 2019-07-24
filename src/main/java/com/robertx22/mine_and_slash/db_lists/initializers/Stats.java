package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.UnknownStat;
import com.robertx22.mine_and_slash.database.stats.stat_types.core_stats.*;
import com.robertx22.mine_and_slash.database.stats.stat_types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.stat_types.defense.BlockStrength;
import com.robertx22.mine_and_slash.database.stats.stat_types.defense.Dodge;
import com.robertx22.mine_and_slash.database.stats.stat_types.defense.SpellDodge;
import com.robertx22.mine_and_slash.database.stats.stat_types.elementals.all_damage.AllEleDmg;
import com.robertx22.mine_and_slash.database.stats.stat_types.elementals.all_damage.AllEleSpellDmg;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.*;
import com.robertx22.mine_and_slash.database.stats.stat_types.misc.BonusExp;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.*;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.*;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.conversions.EnergyToManaConversion;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.conversions.ManaToEnergyConversion;
import com.robertx22.mine_and_slash.database.stats.stat_types.spell_buff_traits.*;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.*;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.atronachs.EarthAtronach;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.atronachs.FireAtronach;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.atronachs.FrostAtronach;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.atronachs.ThunderAtronach;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.bad_and_good.Barbarian;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.bad_and_good.ClumsyScholar;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.bad_ones.Crippled;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.bad_ones.Diseased;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.cause_stats.OnDodgeBuffSpeed;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.ele_lords.LordOfBlizzardsTrait;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.ele_lords.LordOfEarthquakesTrait;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.ele_lords.LordOfThunderstormsTrait;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.ele_lords.LordOfVolcanoesTrait;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.high_crit.HighCritAddArmor;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.high_crit.HighCritAddLifesteal;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.high_dodge.HighDodgeAddCritDamage;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.high_dodge.HighDodgeAddPhysDamage;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.low_crit_hit.LowCritHitAddDodge;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.low_crit_hit.LowCritHitAddHealth;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.low_dodge.LowDodgeAddArmor;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.low_dodge.LowDodgeAddCritHit;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.major_arcana.*;
import com.robertx22.mine_and_slash.db_lists.bases.AllPreGenMapStats;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.spells.projectile.SpellAcidBolt;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class Stats implements ISlashRegistryInit {

    public static AllPreGenMapStats allPreGenMapStatLists = new AllPreGenMapStats();

    @Override
    public void registerAll() {

        List<Stat> All = new ArrayList<>();

        List<Stat> generated = new ArrayList<Stat>() {
            {
                {
                    add(new ElementalBonusDmgOnBasic(Elements.Physical));
                    add(new ElementalConversion(Elements.Physical, Elements.Physical));
                    add(new ElementalTransfer(Elements.Physical, Elements.Physical));
                    add(new ElementalAffinity(Elements.Physical));
                    add(new LootTypeBonusFlat(LootType.NormalItem));
                    add(new WeaponDamage(WeaponTypes.None));
                    add(new ElementalAttackDamage(Elements.Physical));
                    add(new ElementalSpellToAttackDMG(Elements.Physical));
                    add(new AllElementalDamage(Elements.Physical));
                    add(new ElementalSpellDamage(Elements.Physical));
                    add(new ElementalResist(Elements.Physical));
                    add(new ElementalPene(Elements.Physical));
                    add(new ElementalFocus(Elements.Physical));
                    add(new BlockReflect(Elements.Physical));
                    add(new BonusSpecificSpell(new SpellAcidBolt()));

                    // generated

                    add(new PhysicalDispersion());
                    add(new AllAttributes());
                    add(new AllEleDmg());
                    add(new AllEleSpellDmg());
                    add(new SpellDamage());
                    // major arcana tarot
                    add(new Judgement());
                    add(new StrengthArcana());
                    add(new Hermit());
                    add(new Chariot());
                    add(new Death());
                    add(new HangedMan());
                    add(new Justice());
                    add(new HighPriestess());
                    add(new Temperance());
                    add(new TheEmperor());
                    add(new TheDevil());
                    add(new TheEmpress());
                    add(new TheFool());
                    add(new TheHierophant());
                    add(new TheMagician());
                    add(new TheMoon());
                    add(new TheSun());
                    add(new TheWorld());
                    add(new Tower());
                    add(new WheelOfFortune());
                    add(new TheStar());
                    // major arcana tarot

                    add(new Strength());
                    add(new Dexterity());
                    add(new Wisdom());
                    add(new Intelligence());
                    add(new Stamina());
                    add(new Vitality());
                    add(new BonusExp());

                    // spell buffs
                    add(new HomingTrait());
                    add(new GhostProjectileTrait());
                    add(new ZephyrTrait());
                    add(new LightTrait());
                    add(new PurityTrait());
                    add(new BuffEnergyRegenTrait());
                    add(new BuffManaRegenTrait());

                    // spell buffs

                    add(new EnergyToManaConversion());

                    add(new ManaToEnergyConversion());

                    // conditional traits
                    add(new HighCritAddArmor());
                    add(new HighCritAddLifesteal());
                    add(new HighDodgeAddCritDamage());
                    add(new HighDodgeAddPhysDamage());
                    add(new LowDodgeAddArmor());
                    add(new LowDodgeAddCritHit());
                    add(new LowCritHitAddDodge());
                    add(new LowCritHitAddHealth());

                    // lord traits
                    add(new LordOfVolcanoesTrait());
                    add(new LordOfBlizzardsTrait());
                    add(new LordOfThunderstormsTrait());
                    add(new LordOfEarthquakesTrait());
                    // weapon damages

                    add(new UnknownStat());

                    // Resources
                    add(new Health());
                    add(new HealthRegen());
                    add(new Lifesteal());
                    add(new LifeOnHit());
                    add(new Mana());
                    add(new ManaRegen());
                    add(new ManaOnHit());
                    add(new Energy());
                    add(new EnergyRegen());
                    // Resources

                    add(new BlockStrength());
                    add(new Armor());
                    add(new CriticalDamage());
                    add(new CriticalHit());
                    add(new PhysicalDamage());
                    add(new Dodge());
                    add(new SpellDodge());

                    // traits
                    add(new Golem());
                    add(new Elemental());
                    add(new Lucky());
                    add(new Barbarian());
                    add(new Stealthy());
                    add(new ClumsyScholar());
                    add(new Crippled());
                    add(new Diseased());
                    add(new Armored());
                    add(new EarthAtronach());
                    add(new FrostAtronach());
                    add(new FireAtronach());
                    add(new ThunderAtronach());
                    add(new OnDodgeBuffSpeed());
                    add(new HealPower());
                    // traits

                }
            }
        };

        for (Stat stat : generated) {
            if (stat instanceof IGenerated) {
                for (Stat gen : ((IGenerated<Stat>) stat).generateAllPossibleStatVariations()) {
                    All.add(gen);
                }
            } else {
                All.add(stat);
            }

        }

        All.forEach(x -> SlashRegistry.getRegistry(SlashRegistryType.STAT).register(x));

    }

}
