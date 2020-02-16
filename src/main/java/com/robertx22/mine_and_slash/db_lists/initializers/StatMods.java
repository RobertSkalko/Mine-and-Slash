package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.AllTraitMods;
import com.robertx22.mine_and_slash.database.stats.mods.all_stats.CripplePercent;
import com.robertx22.mine_and_slash.database.stats.mods.all_stats.LessPercent;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.AllAttributesFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.HighCoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.*;
import com.robertx22.mine_and_slash.database.stats.mods.flat.elemental.AllEleDmgFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.elemental.AllEleSpellDmgFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.less.LessHealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.BonusExpFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.*;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.*;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.conversions.EnergyToManaConvFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.conversions.ManaToEnergyConvFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.*;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.bases.LessWeaponDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus.BonusEleDamageMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus.BonusEleResistMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus.BonusHealthMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus.BonusLifestealMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.minus.*;
import com.robertx22.mine_and_slash.database.stats.mods.multi.MajorEleResistMinus;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.*;
import com.robertx22.mine_and_slash.database.stats.mods.multi.offence.LessPhysicalDamageMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.offence.PhysicalDamageMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.resources.LessHealthRegenMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.resources.LessManaMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.resources.ManaMulti;
import com.robertx22.mine_and_slash.database.stats.mods.percent.*;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.*;
import com.robertx22.mine_and_slash.database.stats.types.BaseTrait;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Dexterity;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.empty_entries.EmptyStat;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializedRegistryEntry;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class StatMods implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<StatMod> list = new ArrayList<StatMod>() {
            {
                {
                    add(new ElementalSpellToAttackDMGPercent(Elements.Nature));
                    add(new ElementalConversionFlat(Elements.Nature, Elements.Nature));
                    add(new ElementalTransferFlat(Elements.Nature, Elements.Nature));
                    add(new ElementalAffinityFlat(Elements.Nature));
                    add(new LootTypeBonusFlat(LootType.NormalItem));
                    add(new WeaponDamageFlat(WeaponTypes.None));
                    add(new LowWeaponDamageFlat(WeaponTypes.None));
                    add(new LessWeaponDamageFlat(WeaponTypes.None));
                    add(new ElementalAttackDamageFlat(Elements.Nature));
                    add(new AllElementalDamageMulti(Elements.Nature));
                    add(new ElementalSpellToAttackDMGFlat(Elements.Nature));
                    add(new ElementalSpellDamagePercent(Elements.Nature));
                    add(new ElementalSpellDamageFlat(Elements.Nature));
                    add(new ElementalResistFlat(Elements.Nature));
                    add(new ElementalSpellDamageMulti(Elements.Nature));
                    add(new ElementalPeneFlat(Elements.Nature));
                    add(new ElementalPenePercent(Elements.Nature));
                    add(new ElementalFocusFlat(Elements.Nature));
                    add(new BlockReflectFlat(Elements.Nature));

                    add(new BonusEleDamageMap(Elements.Nature));
                    add(new BonusEleResistMap(Elements.Nature));
                    add(new LessEleDmgMap(Elements.Nature));

                    add(new MajorEleResistMinus(Elements.Nature));
                    add(new CripplePercent(EmptyStat.getInstance()));
                    add(new LessPercent(EmptyStat.getInstance()));

                    add(new CoreStatFlat(Dexterity.INSTANCE));
                    add(new HighCoreStatFlat(Dexterity.INSTANCE));
                    add(new CoreStatPercent(Dexterity.INSTANCE));

                    add(new LowElementalResistFlat(Elements.Nature));
                    add(new LowElementalAffinityFlat(Elements.Nature));
                    add(new MediumElementalAttackDamageFlat(Elements.Nature));
                    add(new LowElementalAttackDamageFlat(Elements.Nature));
                    add(new HighElementalSpellToAttackDMGFlat(Elements.Nature));
                    add(new HighElementalResistFlat(Elements.Nature));
                    add(new HighElementalPeneFlat(Elements.Nature));

                    add(new CompletePhysDispersionFlat());
                    add(new HealPowerFlat());
                    add(new AllEleDmgFlat());
                    add(new AllEleSpellDmgFlat());
                    add(new HighHealthRegenFlat());

                    add(new BlockStrengthPercent());
                    add(new SpellDamageFlat());
                    add(new SpellDamagePercent());

                    add(new HighManaFlat());
                    add(new LowArmorFlat());
                    add(new HighHealthFlat());
                    add(new HighEnergyRegenFlat());
                    add(new HighMagicShieldRegenFlat());

                    add(new BonusExpFlat());

                    // spell buffs

                    add(new LowHealthFlat());

                    add(new ManaMulti());
                    add(new LessManaMulti());
                    add(new LessHealthRegenMulti());

                    add(new PhysicalDamageMulti());
                    add(new LessPhysicalDamageMulti());

                    add(new LessArmorMulti());
                    add(new HealthMulti());
                    add(new DodgeMulti());
                    add(new CriticalHitMulti());
                    add(new ArmorMulti());

                    //

                    add(new EnergyToManaConvFlat());
                    add(new ManaToEnergyConvFlat());

                    // weapon damages

                    // less stats

                    add(new LessHealthRegenFlat());

                    add(new MajorCriticalHitPercent());
                    add(new MajorCriticalDamagePercent());

                    add(new HighDodgeFlat());
                    add(new HighArmorFlat());
                    add(new ArmorFlat());
                    add(new CriticalHitFlat());
                    add(new CriticalDamageFlat());
                    add(new HighCriticalDamageFlat());
                    add(new PhysicalDamageFlat());
                    add(new CriticalHitPercent());
                    add(new PhysicalDamagePercent());
                    add(new CriticalDamagePercent());
                    add(new DodgePercent());
                    add(new BlockStrengthFlat());

                    // Resources
                    add(new HighManaRegenFlat());
                    add(new HealthFlat());
                    add(new HealthPercent());
                    add(new HealthRegenPercent());
                    add(new HealthRegenFlat());
                    add(new ManaRegenFlat());
                    add(new EnergyRegenFlat());
                    add(new EnergyRegenPercent());
                    add(new ManaRegenPercent());
                    add(new EnergyFlat());
                    add(new MagicShieldFlat());
                    add(new MagicShieldRegenFlat());
                    add(new MagicShieldPercent());
                    add(new MagicShieldRegenPercent());

                    add(new LifestealFlat());
                    add(new LifestealPercent());
                    add(new LifeOnHitFlat());
                    add(new LifeOnHitPercent());
                    add(new ManaFlat());
                    add(new ManaOnHitFlat());
                    // Resources

                    add(new ArmorPercent());
                    add(new DodgeRatingFlat());

                    // bonus dmg

                    // Map mods

                    add(new BonusHealthMap());
                    add(new BonusLifestealMap());

                    add(new LessCriticalHitMap());
                    add(new LessDodgeMap());

                    add(new LessEnergyRegenMap());
                    add(new LessManaRegenMap());
                    add(new LessHealthRegenMap());
                    add(new LessLifeOnHitMap());
                    add(new LessLifestealMap());
                    add(new LessHealthMap());
                    add(new LessManaOnHitMap());

                    add(new AllAttributesFlat());

                }
            }
        };

        List<ISerializedRegistryEntry> All = new ArrayList<>();

        for (StatMod stat : list) {
            if (stat instanceof IGenerated) {
                IGenerated<StatMod> gen = (IGenerated<StatMod>) stat;
                for (StatMod statmod : gen.generateAllPossibleStatVariations()) {
                    All.add(statmod);
                }
            } else {
                All.add(stat);
            }
        }

        // this makes all StatMod classes for traits cus they are all the same!
        for (Stat stat : SlashRegistry.Stats().getList()) {
            if (stat instanceof BaseTrait) {
                BaseTrait trait = (BaseTrait) stat;
                AllTraitMods traitMod = new AllTraitMods(trait);
                All.add(traitMod);
            }
        }

        All.forEach(x -> x.registerToSlashRegistry());

        //new StatModManager().registerAll();

    }

}
