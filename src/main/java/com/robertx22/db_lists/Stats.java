package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.database.stat_types.UnknownStat;
import com.robertx22.database.stat_types.defense.Armor;
import com.robertx22.database.stat_types.defense.BlockStrength;
import com.robertx22.database.stat_types.defense.Dodge;
import com.robertx22.database.stat_types.defense.SpellDodge;
import com.robertx22.database.stat_types.elementals.all_damage.AllFireDamage;
import com.robertx22.database.stat_types.elementals.all_damage.AllNatureDamage;
import com.robertx22.database.stat_types.elementals.all_damage.AllThunderDamage;
import com.robertx22.database.stat_types.elementals.all_damage.AllWaterDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stat_types.elementals.conversion.fire_to.FireToNatureConversion;
import com.robertx22.database.stat_types.elementals.conversion.fire_to.FireToThunderConversion;
import com.robertx22.database.stat_types.elementals.conversion.fire_to.FireToWaterConversion;
import com.robertx22.database.stat_types.elementals.conversion.nature_to.NatureToFireConversion;
import com.robertx22.database.stat_types.elementals.conversion.nature_to.NatureToThunderConversion;
import com.robertx22.database.stat_types.elementals.conversion.nature_to.NatureToWaterConversion;
import com.robertx22.database.stat_types.elementals.conversion.thunder_to.ThunderToFireConversion;
import com.robertx22.database.stat_types.elementals.conversion.thunder_to.ThunderToNatureConversion;
import com.robertx22.database.stat_types.elementals.conversion.thunder_to.ThunderToWaterConversion;
import com.robertx22.database.stat_types.elementals.conversion.water_to.WaterToFireConversion;
import com.robertx22.database.stat_types.elementals.conversion.water_to.WaterToNatureConversion;
import com.robertx22.database.stat_types.elementals.conversion.water_to.WaterToThunderConversion;
import com.robertx22.database.stat_types.elementals.pene.FirePene;
import com.robertx22.database.stat_types.elementals.pene.NaturePene;
import com.robertx22.database.stat_types.elementals.pene.ThunderPene;
import com.robertx22.database.stat_types.elementals.pene.WaterPene;
import com.robertx22.database.stat_types.elementals.resist.FireResist;
import com.robertx22.database.stat_types.elementals.resist.NatureResist;
import com.robertx22.database.stat_types.elementals.resist.ThunderResist;
import com.robertx22.database.stat_types.elementals.resist.WaterResist;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.database.stat_types.elementals.spell_to_attack.FireSpellToAttackDMG;
import com.robertx22.database.stat_types.elementals.spell_to_attack.NatureSpellToAttackDMG;
import com.robertx22.database.stat_types.elementals.spell_to_attack.ThunderSpellToAttackDMG;
import com.robertx22.database.stat_types.elementals.spell_to_attack.WaterSpellToAttackDMG;
import com.robertx22.database.stat_types.elementals.transfers.fire_to.FireToNatureTransfer;
import com.robertx22.database.stat_types.elementals.transfers.fire_to.FireToThunderTransfer;
import com.robertx22.database.stat_types.elementals.transfers.fire_to.FireToWaterTransfer;
import com.robertx22.database.stat_types.elementals.transfers.nature_to.NatureToFireTransfer;
import com.robertx22.database.stat_types.elementals.transfers.nature_to.NatureToThunderTransfer;
import com.robertx22.database.stat_types.elementals.transfers.nature_to.NatureToWaterTransfer;
import com.robertx22.database.stat_types.elementals.transfers.thunder_to.ThunderToFireTransfer;
import com.robertx22.database.stat_types.elementals.transfers.thunder_to.ThunderToNatureTransfer;
import com.robertx22.database.stat_types.elementals.transfers.thunder_to.ThunderToWaterTransfer;
import com.robertx22.database.stat_types.elementals.transfers.water_to.WaterToFireTransfer;
import com.robertx22.database.stat_types.elementals.transfers.water_to.WaterToNatureTransfer;
import com.robertx22.database.stat_types.elementals.transfers.water_to.WaterToThunderTransfer;
import com.robertx22.database.stat_types.offense.ArmorPenetration;
import com.robertx22.database.stat_types.offense.CriticalDamage;
import com.robertx22.database.stat_types.offense.CriticalHit;
import com.robertx22.database.stat_types.offense.PhysicalDamage;
import com.robertx22.database.stat_types.offense.weapon_types.AxeDamage;
import com.robertx22.database.stat_types.offense.weapon_types.BowDamage;
import com.robertx22.database.stat_types.offense.weapon_types.HammerDamage;
import com.robertx22.database.stat_types.offense.weapon_types.StaffDamage;
import com.robertx22.database.stat_types.offense.weapon_types.SwordDamage;
import com.robertx22.database.stat_types.resources.Energy;
import com.robertx22.database.stat_types.resources.EnergyRegen;
import com.robertx22.database.stat_types.resources.Health;
import com.robertx22.database.stat_types.resources.HealthRegen;
import com.robertx22.database.stat_types.resources.LifeOnHit;
import com.robertx22.database.stat_types.resources.Lifesteal;
import com.robertx22.database.stat_types.resources.Mana;
import com.robertx22.database.stat_types.resources.ManaOnHit;
import com.robertx22.database.stat_types.resources.ManaRegen;
import com.robertx22.database.stat_types.resources.conversions.EnergyToManaConversion;
import com.robertx22.database.stat_types.resources.conversions.ManaToEnergyConversion;
import com.robertx22.database.stat_types.spell_buff_traits.BuffEnergyRegenTrait;
import com.robertx22.database.stat_types.spell_buff_traits.BuffManaRegenTrait;
import com.robertx22.database.stat_types.spell_buff_traits.GhostProjectileTrait;
import com.robertx22.database.stat_types.spell_buff_traits.HomingTrait;
import com.robertx22.database.stat_types.spell_buff_traits.LightTrait;
import com.robertx22.database.stat_types.spell_buff_traits.PurityTrait;
import com.robertx22.database.stat_types.spell_buff_traits.ZephyrTrait;
import com.robertx22.database.stat_types.traits.Armored;
import com.robertx22.database.stat_types.traits.Elemental;
import com.robertx22.database.stat_types.traits.Golem;
import com.robertx22.database.stat_types.traits.Lucky;
import com.robertx22.database.stat_types.traits.Stealthy;
import com.robertx22.database.stat_types.traits.atronachs.EarthAtronach;
import com.robertx22.database.stat_types.traits.atronachs.FireAtronach;
import com.robertx22.database.stat_types.traits.atronachs.FrostAtronach;
import com.robertx22.database.stat_types.traits.atronachs.ThunderAtronach;
import com.robertx22.database.stat_types.traits.bad_and_good.Barbarian;
import com.robertx22.database.stat_types.traits.bad_and_good.ClumsyScholar;
import com.robertx22.database.stat_types.traits.bad_ones.Crippled;
import com.robertx22.database.stat_types.traits.bad_ones.Diseased;
import com.robertx22.database.stat_types.traits.ele_lords.LordOfBlizzardsTrait;
import com.robertx22.database.stat_types.traits.ele_lords.LordOfEarthquakesTrait;
import com.robertx22.database.stat_types.traits.ele_lords.LordOfThunderstormsTrait;
import com.robertx22.database.stat_types.traits.ele_lords.LordOfVolcanoesTrait;
import com.robertx22.database.stat_types.traits.high_crit.HighCritAddArmor;
import com.robertx22.database.stat_types.traits.high_crit.HighCritAddLifesteal;
import com.robertx22.database.stat_types.traits.high_dodge.HighDodgeAddCritDamage;
import com.robertx22.database.stat_types.traits.high_dodge.HighDodgeAddPhysDamage;
import com.robertx22.database.stat_types.traits.low_crit_hit.LowCritHitAddDodge;
import com.robertx22.database.stat_types.traits.low_crit_hit.LowCritHitAddHealth;
import com.robertx22.database.stat_types.traits.low_dodge.LowDodgeAddArmor;
import com.robertx22.database.stat_types.traits.low_dodge.LowDodgeAddCritHit;
import com.robertx22.stats.Stat;

public class Stats {

    public static HashMap<String, Stat> All = new HashMap<String, Stat>() {
	{
	    {

		// spell buffs
		put(new HomingTrait().GUID(), new HomingTrait());
		put(new GhostProjectileTrait().GUID(), new GhostProjectileTrait());
		put(new ZephyrTrait().GUID(), new ZephyrTrait());
		put(new LightTrait().GUID(), new LightTrait());
		put(new PurityTrait().GUID(), new PurityTrait());
		put(new BuffEnergyRegenTrait().GUID(), new BuffEnergyRegenTrait());
		put(new BuffManaRegenTrait().GUID(), new BuffManaRegenTrait());

		// spell buffs

		put(new EnergyToManaConversion().GUID(), new EnergyToManaConversion());
		put(new ManaToEnergyConversion().GUID(), new ManaToEnergyConversion());

		// conditional traits
		put(new HighCritAddArmor().GUID(), new HighCritAddArmor());
		put(new HighCritAddLifesteal().GUID(), new HighCritAddLifesteal());
		put(new HighDodgeAddCritDamage().GUID(), new HighDodgeAddCritDamage());
		put(new HighDodgeAddPhysDamage().GUID(), new HighDodgeAddPhysDamage());

		put(new LowDodgeAddArmor().GUID(), new LowDodgeAddArmor());
		put(new LowDodgeAddCritHit().GUID(), new LowDodgeAddCritHit());
		put(new LowCritHitAddDodge().GUID(), new LowCritHitAddDodge());
		put(new LowCritHitAddHealth().GUID(), new LowCritHitAddHealth());

		// lord traits
		put(new LordOfVolcanoesTrait().GUID(), new LordOfVolcanoesTrait());
		put(new LordOfBlizzardsTrait().GUID(), new LordOfBlizzardsTrait());
		put(new LordOfThunderstormsTrait().GUID(), new LordOfThunderstormsTrait());
		put(new LordOfEarthquakesTrait().GUID(), new LordOfEarthquakesTrait());

		// weapon damages

		put(new HammerDamage().GUID(), new HammerDamage());
		put(new SwordDamage().GUID(), new SwordDamage());
		put(new BowDamage().GUID(), new BowDamage());
		put(new AxeDamage().GUID(), new AxeDamage());
		put(new StaffDamage().GUID(), new StaffDamage());

		put(UnknownStat.GUID, new UnknownStat());
		// Resources
		put(Health.GUID, new Health());
		put(HealthRegen.GUID, new HealthRegen());
		put(Lifesteal.GUID, new Lifesteal());
		put(LifeOnHit.GUID, new LifeOnHit());

		put(Mana.GUID, new Mana());
		put(ManaRegen.GUID, new ManaRegen());
		put(ManaOnHit.GUID, new ManaOnHit());

		put(Energy.GUID, new Energy());
		put(EnergyRegen.GUID, new EnergyRegen());
		// Resources

		put(BlockStrength.GUID, new BlockStrength());

		put(Armor.GUID, new Armor());
		put(ArmorPenetration.GUID, new ArmorPenetration());
		put(CriticalDamage.GUID, new CriticalDamage());
		put(CriticalHit.GUID, new CriticalHit());
		put(PhysicalDamage.GUID, new PhysicalDamage());

		// Elemental
		put(SpellFireDamage.GUID, new SpellFireDamage());
		put(SpellWaterDamage.GUID, new SpellWaterDamage());
		put(SpellThunderDamage.GUID, new SpellThunderDamage());
		put(SpellNatureDamage.GUID, new SpellNatureDamage());

		put(AttackFireDamage.GUID, new AttackFireDamage());
		put(AttackWaterDamage.GUID, new AttackWaterDamage());
		put(AttackThunderDamage.GUID, new AttackThunderDamage());
		put(AttackNatureDamage.GUID, new AttackNatureDamage());

		put(AllFireDamage.GUID, new AllFireDamage());
		put(AllWaterDamage.GUID, new AllWaterDamage());
		put(AllThunderDamage.GUID, new AllThunderDamage());
		put(AllNatureDamage.GUID, new AllNatureDamage());

		put(FireResist.GUID, new FireResist());
		put(NatureResist.GUID, new NatureResist());
		put(WaterResist.GUID, new WaterResist());
		put(ThunderResist.GUID, new ThunderResist());

		put(FirePene.GUID, new FirePene());
		put(NaturePene.GUID, new NaturePene());
		put(WaterPene.GUID, new WaterPene());
		put(ThunderPene.GUID, new ThunderPene());
		// Elemental

		put(Dodge.GUID, new Dodge());
		put(SpellDodge.GUID, new SpellDodge());

		// traits
		put(Golem.GUID, new Golem());
		put(Elemental.GUID, new Elemental());
		put(Lucky.GUID, new Lucky());
		put(Barbarian.GUID, new Barbarian());
		put(Stealthy.GUID, new Stealthy());
		put(ClumsyScholar.GUID, new ClumsyScholar());
		put(Crippled.GUID, new Crippled());
		put(Diseased.GUID, new Diseased());
		put(Armored.GUID, new Armored());

		put(EarthAtronach.GUID, new EarthAtronach());
		put(FrostAtronach.GUID, new FrostAtronach());
		put(FireAtronach.GUID, new FireAtronach());
		put(ThunderAtronach.GUID, new ThunderAtronach());

		put(FireSpellToAttackDMG.GUID, new FireSpellToAttackDMG());
		put(WaterSpellToAttackDMG.GUID, new WaterSpellToAttackDMG());
		put(ThunderSpellToAttackDMG.GUID, new ThunderSpellToAttackDMG());
		put(NatureSpellToAttackDMG.GUID, new NatureSpellToAttackDMG());

		// traits

		// elemental conversions
		put(new WaterToThunderConversion().Guid(), new WaterToThunderConversion());
		put(new WaterToNatureConversion().Guid(), new WaterToNatureConversion());
		put(new WaterToFireConversion().Guid(), new WaterToFireConversion());

		put(new FireToThunderConversion().Guid(), new FireToThunderConversion());
		put(new FireToNatureConversion().Guid(), new FireToNatureConversion());
		put(new FireToWaterConversion().Guid(), new FireToWaterConversion());

		put(new ThunderToWaterConversion().Guid(), new ThunderToWaterConversion());
		put(new ThunderToNatureConversion().Guid(), new ThunderToNatureConversion());
		put(new ThunderToFireConversion().Guid(), new ThunderToFireConversion());

		put(new NatureToThunderConversion().Guid(), new NatureToThunderConversion());
		put(new NatureToWaterConversion().Guid(), new NatureToWaterConversion());
		put(new NatureToFireConversion().Guid(), new NatureToFireConversion());
		// elemental conversions

		// elemental Transfers
		put(new WaterToThunderTransfer().Guid(), new WaterToThunderTransfer());
		put(new WaterToNatureTransfer().Guid(), new WaterToNatureTransfer());
		put(new WaterToFireTransfer().Guid(), new WaterToFireTransfer());

		put(new FireToThunderTransfer().Guid(), new FireToThunderTransfer());
		put(new FireToNatureTransfer().Guid(), new FireToNatureTransfer());
		put(new FireToWaterTransfer().Guid(), new FireToWaterTransfer());

		put(new ThunderToWaterTransfer().Guid(), new ThunderToWaterTransfer());
		put(new ThunderToNatureTransfer().Guid(), new ThunderToNatureTransfer());
		put(new ThunderToFireTransfer().Guid(), new ThunderToFireTransfer());

		put(new NatureToThunderTransfer().Guid(), new NatureToThunderTransfer());
		put(new NatureToWaterTransfer().Guid(), new NatureToWaterTransfer());
		put(new NatureToFireTransfer().Guid(), new NatureToFireTransfer());
		// elemental Transfers

	    }
	}
    };
}
