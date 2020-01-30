package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.AllTraitMods;
import com.robertx22.mine_and_slash.database.stats.mods.PotionBonusDmgAmountFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.*;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.*;
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
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus.BonusHealthMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus.BonusLifestealMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus.ele_dmg.BonusFireDamageMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus.ele_dmg.BonusNatureDamageMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus.ele_dmg.BonusThunderDamageMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus.ele_dmg.BonusWaterDamageMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus.ele_res.BonusFireResistMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus.ele_res.BonusNatureResistMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus.ele_res.BonusThunderResistMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus.ele_res.BonusWaterResistMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.minus.*;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.minus.all_ele_dmg.LessAllFireDamageMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.minus.all_ele_dmg.LessAllNatureDamageMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.minus.all_ele_dmg.LessAllThunderDamageMap;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.minus.all_ele_dmg.LessAllWaterDamageMap;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.*;
import com.robertx22.mine_and_slash.database.stats.mods.multi.ele_minus.MajorMinusFireResistMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.ele_minus.MajorMinusNatureResistMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.ele_minus.MajorMinusThunderResistMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.ele_minus.MajorMinusWaterResistMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.offence.LessPhysicalDamageMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.offence.PhysicalDamageMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.resources.LessHealthRegenMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.resources.LessManaMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.resources.ManaMulti;
import com.robertx22.mine_and_slash.database.stats.mods.percent.*;
import com.robertx22.mine_and_slash.database.stats.mods.percent.less.*;
import com.robertx22.mine_and_slash.database.stats.mods.percent.much_less.*;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.*;
import com.robertx22.mine_and_slash.database.stats.types.BaseTrait;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
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
                    add(new PotionBonusDmgAmountFlat(Elements.Nature));
                    add(new ElementalConversionFlat(Elements.Nature, Elements.Nature));
                    add(new ElementalTransferFlat(Elements.Nature, Elements.Nature));
                    add(new ElementalAffinityFlat(Elements.Nature));
                    add(new LootTypeBonusFlat(LootType.NormalItem));
                    add(new WeaponDamageFlat(WeaponTypes.None));
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

                    add(new CompletePhysDispersionFlat());
                    add(new HealPowerFlat());
                    add(new AllEleDmgFlat());
                    add(new AllEleSpellDmgFlat());

                    add(new BlockStrengthPercent());
                    add(new SpellDamageFlat());
                    add(new SpellDamagePercent());

                    add(new BonusExpFlat());

                    // spell buffs

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
                    add(new LessCriticalDamagePercent());
                    add(new LessCriticalHitPercent());
                    add(new LessDodgePercent());
                    add(new LessHealthRegenPercent());
                    add(new LessManaRegenPercent());
                    add(new LessManaOnHitPercent());
                    add(new LessLifestealPercent());
                    add(new LessLifeOnHitPercent());
                    // less stats

                    // cripple stats (much less)
                    add(new CrippleCriticalDamagePercent());
                    add(new CrippleCriticalHitPercent());
                    add(new CrippleDodgePercent());
                    add(new CrippleHealthRegenPercent());
                    add(new CrippleManaRegenPercent());
                    add(new CrippleManaOnHitPercent());
                    add(new CrippleLifestealPercent());
                    add(new CrippleLifeOnHitPercent());
                    // cripple

                    add(new MajorCriticalHitPercent());
                    add(new MajorCriticalDamagePercent());

                    add(new MajorDodgeFlat());
                    add(new MajorArmorFlat());
                    add(new ArmorFlat());
                    add(new CriticalHitFlat());
                    add(new CriticalDamageFlat());
                    add(new PhysicalDamageFlat());
                    add(new CriticalHitPercent());
                    add(new PhysicalDamagePercent());
                    add(new CriticalDamagePercent());
                    add(new DodgePercent());
                    add(new BlockStrengthFlat());

                    // Resources
                    add(new MajorManaRegenFlat());
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

                    add(new BonusFireDamageMap());
                    add(new BonusNatureDamageMap());
                    add(new BonusThunderDamageMap());
                    add(new BonusWaterDamageMap());

                    add(new BonusFireResistMap());
                    add(new BonusNatureResistMap());
                    add(new BonusThunderResistMap());
                    add(new BonusWaterResistMap());

                    add(new LessAllFireDamageMap());
                    add(new LessAllNatureDamageMap());
                    add(new LessAllThunderDamageMap());
                    add(new LessAllWaterDamageMap());

                    add(new LessEnergyRegenMap());
                    add(new LessManaRegenMap());
                    add(new LessHealthRegenMap());
                    add(new LessLifeOnHitMap());
                    add(new LessLifestealMap());
                    add(new LessHealthMap());
                    add(new LessManaOnHitMap());
                    // Map mods
                    add(new MajorMinusFireResistMulti());
                    add(new MajorMinusWaterResistMulti());
                    add(new MajorMinusThunderResistMulti());
                    add(new MajorMinusNatureResistMulti());

                    add(new AllAttributesFlat());
                    add(new StrengthFlat());
                    add(new DexterityFlat());
                    add(new WisdomFlat());
                    add(new IntelligenceFlat());
                    add(new StaminaFlat());
                    add(new VitalityFlat());

                }
            }
        };

        List<StatMod> All = new ArrayList<>();

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

    }

}
