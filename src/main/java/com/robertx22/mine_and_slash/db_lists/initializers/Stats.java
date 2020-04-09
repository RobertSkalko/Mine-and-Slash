package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.UnknownStat;
import com.robertx22.mine_and_slash.database.stats.types.class_based.RogueStealth;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.*;
import com.robertx22.mine_and_slash.database.stats.types.defense.*;
import com.robertx22.mine_and_slash.database.stats.types.elementals.all_damage.AllEleDmg;
import com.robertx22.mine_and_slash.database.stats.types.elementals.all_damage.AllEleSpellDmg;
import com.robertx22.mine_and_slash.database.stats.types.game_changers.*;
import com.robertx22.mine_and_slash.database.stats.types.generated.*;
import com.robertx22.mine_and_slash.database.stats.types.misc.BonusExp;
import com.robertx22.mine_and_slash.database.stats.types.misc.LuckStat;
import com.robertx22.mine_and_slash.database.stats.types.offense.*;
import com.robertx22.mine_and_slash.database.stats.types.resources.*;
import com.robertx22.mine_and_slash.database.stats.types.resources.conversions.EnergyToManaConversion;
import com.robertx22.mine_and_slash.database.stats.types.resources.conversions.ManaToEnergyConversion;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.PlusLevelToAllAbilitiesInSchoolStat;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.ReducedCooldownStat;
import com.robertx22.mine_and_slash.database.stats.types.traits.atronachs.EarthAtronach;
import com.robertx22.mine_and_slash.database.stats.types.traits.atronachs.FireAtronach;
import com.robertx22.mine_and_slash.database.stats.types.traits.atronachs.FrostAtronach;
import com.robertx22.mine_and_slash.database.stats.types.traits.atronachs.ThunderAtronach;
import com.robertx22.mine_and_slash.database.stats.types.traits.bad_and_good.Barbarian;
import com.robertx22.mine_and_slash.database.stats.types.traits.bad_and_good.ClumsyScholar;
import com.robertx22.mine_and_slash.database.stats.types.traits.bad_ones.*;
import com.robertx22.mine_and_slash.database.stats.types.traits.cause_stats.OnDodgeBuffSpeed;
import com.robertx22.mine_and_slash.database.stats.types.traits.ele_lords.LordOfBlizzardsTrait;
import com.robertx22.mine_and_slash.database.stats.types.traits.ele_lords.LordOfEarthquakesTrait;
import com.robertx22.mine_and_slash.database.stats.types.traits.ele_lords.LordOfThunderstormsTrait;
import com.robertx22.mine_and_slash.database.stats.types.traits.ele_lords.LordOfVolcanoesTrait;
import com.robertx22.mine_and_slash.database.stats.types.traits.good.*;
import com.robertx22.mine_and_slash.database.stats.types.traits.major_arcana.*;
import com.robertx22.mine_and_slash.db_lists.bases.AllPreGenMapStats;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
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

                    add(ReducedCooldownStat.getInstance());

                    add(ArmorPenetration.getInstance());

                    // GAME CHANGERS
                    add(ManaBattery.INSTANCE);
                    add(BleedMastery.INSTANCE);
                    add(RefreshingBreeze.INSTANCE);
                    add(BloodMage.INSTANCE);
                    add(Harmony.INSTANCE);
                    add(MagicalLife.INSTANCE);
                    add(SteadyHand.INSTANCE);
                    add(RecklessBlows.INSTANCE);
                    add(TrueHit.INSTANCE);
                    add(ElementalPurity.getInstance());
                    add(OverflowingVitality.getInstance());
                    add(ArcaneDevotion.getInstance());
                    add(FamiliarInstincts.getInstance());

                    add(new LuckStat());

                    add(new PlusLevelToAllAbilitiesInSchoolStat(SpellSchools.OCEAN_MYSTIC));
                    add(new LootTypeBonus(LootType.NormalItem));
                    add(new WeaponDamage(WeaponTypes.None));
                    add(new ElementalAttackDamage(Elements.Physical));
                    add(new ElementalInfusion(Elements.Physical));
                    add(new AllElementalDamage(Elements.Physical));
                    add(new ElementalSpellDamage(Elements.Physical));
                    add(new ElementalResist(Elements.Physical));
                    add(new ElementalPene(Elements.Physical));
                    add(new ElementalFocus(Elements.Physical));
                    add(new BlockReflect(Elements.Physical));

                    // generated

                    add(RogueStealth.getInstance());
                    add(new PhysicalDispersion());
                    add(new AllAttributes());
                    add(new AllEleDmg());
                    add(new AllEleSpellDmg());
                    add(SpellDamage.getInstance());
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
                    add(new TheLovers());
                    add(new TheSun());
                    add(new TheWorld());
                    add(new Tower());
                    add(new WheelOfFortune());
                    add(new TheStar());
                    // major arcana tarot

                    add(Strength.INSTANCE);
                    add(Dexterity.INSTANCE);
                    add(Wisdom.INSTANCE);
                    add(Intelligence.INSTANCE);
                    add(Stamina.INSTANCE);
                    add(Vitality.INSTANCE);
                    add(new BonusExp());

                    add(new EnergyToManaConversion());

                    add(new ManaToEnergyConversion());

                    // lord traits
                    add(new LordOfVolcanoesTrait());
                    add(new LordOfBlizzardsTrait());
                    add(new LordOfThunderstormsTrait());
                    add(new LordOfEarthquakesTrait());
                    // weapon damages

                    add(new UnknownStat());

                    // Resources
                    add(Health.getInstance());
                    add(HealthRegen.getInstance());
                    add(Lifesteal.getInstance());
                    add(LifeOnHit.getInstance());
                    add(MagicSteal.getInstance());
                    add(Mana.getInstance());
                    add(ManaRegen.getInstance());
                    add(ManaOnHit.getInstance());
                    add(Energy.getInstance());
                    add(EnergyRegen.getInstance());
                    add(MagicShield.getInstance());
                    add(MagicShieldRegen.getInstance());
                    // Resources

                    add(new BlockStrength());
                    add(Armor.getInstance());
                    add(CriticalDamage.getInstance());
                    add(CriticalHit.getInstance());
                    add(PhysicalDamage.getInstance());
                    add(DodgeRating.getInstance());
                    add(new SpellDodge());
                    add(DamageShield.getInstance());

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
                    add(new Clumsy());
                    add(new Cursed());
                    add(new Clueless());
                    add(new Devoted());
                    add(new QuickLearner());
                    add(new WeaponMaster());

                    add(HealPower.getInstance());
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

        EleWepDmg.register();

        All.forEach(x -> x.registerToSlashRegistry());

    }

}
