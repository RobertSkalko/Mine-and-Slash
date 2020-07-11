package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.UnknownStat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.AllAttributes;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Dexterity;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Intelligence;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Strength;
import com.robertx22.mine_and_slash.database.stats.types.defense.*;
import com.robertx22.mine_and_slash.database.stats.types.elementals.all_damage.AllEleDmg;
import com.robertx22.mine_and_slash.database.stats.types.generated.*;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.*;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.FasterCastRate;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.ReducedCooldownStat;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.ReducedManaCost;
import com.robertx22.mine_and_slash.db_lists.bases.AllPreGenMapStats;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
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

                    add(ArmorPenetration.getInstance());

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

                    add(AllAttributes.getInstance());
                    add(new AllEleDmg());
                    add(SpellDamage.getInstance());

                    add(Strength.INSTANCE);
                    add(Dexterity.INSTANCE);
                    add(Intelligence.INSTANCE);

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
                    add(MagicShield.getInstance());
                    add(MagicShieldRegen.getInstance());
                    // Resources

                    add(new BlockStrength());
                    add(Armor.getInstance());
                    add(CriticalDamage.getInstance());
                    add(CriticalHit.getInstance());
                    add(DodgeRating.getInstance());
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
