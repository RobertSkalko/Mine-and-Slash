package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.UnknownStat;
import com.robertx22.mine_and_slash.database.stats.types.class_based.RogueStealth;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.*;
import com.robertx22.mine_and_slash.database.stats.types.defense.*;
import com.robertx22.mine_and_slash.database.stats.types.elementals.all_damage.AllEleDmg;
import com.robertx22.mine_and_slash.database.stats.types.generated.*;
import com.robertx22.mine_and_slash.database.stats.types.misc.BonusExp;
import com.robertx22.mine_and_slash.database.stats.types.misc.LuckStat;
import com.robertx22.mine_and_slash.database.stats.types.offense.*;
import com.robertx22.mine_and_slash.database.stats.types.resources.*;
import com.robertx22.mine_and_slash.database.stats.types.resources.conversions.EnergyToManaConversion;
import com.robertx22.mine_and_slash.database.stats.types.resources.conversions.ManaToEnergyConversion;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.*;
import com.robertx22.mine_and_slash.db_lists.bases.AllPreGenMapStats;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.registry.empty_entries.EmptySpell;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
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

                    add(ArmorPenetration.getInstance());

                    add(new LuckStat());

                    add(new PlusAbilityLevelStat(new EmptySpell()));
                    add(new PlusLevelToAllAbilitiesInSchoolStat(Masteries.OCEAN));
                    add(new LootTypeBonus(LootType.NormalItem));
                    add(new SpecificWeaponDamage(WeaponTypes.None));
                    add(new WeaponDamage(Elements.Physical));
                    add(new ElementalDamageBonus(Elements.Physical));
                    add(new ElementalSpellDamage(Elements.Physical));
                    add(new ElementalResist(Elements.Physical));
                    add(new ElementalPenetration(Elements.Physical));
                    add(new ElementalFocus(Elements.Physical));
                    add(new BlockReflect(Elements.Physical));

                    // generated

                    add(ReducedCooldownStat.getInstance());
                    add(ReducedManaCost.getInstance());
                    add(FasterCastRate.getInstance());

                    add(RogueStealth.getInstance());
                    add(new PhysicalDispersion());
                    add(new AllAttributes());
                    add(new AllEleDmg());
                    add(SpellDamage.getInstance());

                    add(Strength.INSTANCE);
                    add(Dexterity.INSTANCE);
                    add(Wisdom.INSTANCE);
                    add(Intelligence.INSTANCE);
                    add(Stamina.INSTANCE);
                    add(Vitality.INSTANCE);
                    add(new BonusExp());

                    add(new EnergyToManaConversion());

                    add(new ManaToEnergyConversion());

                    add(new UnknownStat());

                    // Resources
                    add(BonusMaximumHealth.getInstance());
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

        SpecificElementalWeaponDamage.register();

        All.forEach(x -> x.registerToSlashRegistry());

    }

}
