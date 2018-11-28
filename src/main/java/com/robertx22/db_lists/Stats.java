package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.database.stat_types.UnknownStat;
import com.robertx22.database.stat_types.defense.Armor;
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
import com.robertx22.database.stat_types.resources.Energy;
import com.robertx22.database.stat_types.resources.EnergyRegen;
import com.robertx22.database.stat_types.resources.Health;
import com.robertx22.database.stat_types.resources.HealthRegen;
import com.robertx22.database.stat_types.resources.LifeOnHit;
import com.robertx22.database.stat_types.resources.Lifesteal;
import com.robertx22.database.stat_types.resources.Mana;
import com.robertx22.database.stat_types.resources.ManaOnHit;
import com.robertx22.database.stat_types.resources.ManaRegen;
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
import com.robertx22.stats.Stat;

public class Stats {

    public static HashMap<String, Stat> All = new HashMap<String, Stat>() {
	{
	    {

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
		put(new WaterToThunderConversion().Name(), new WaterToThunderConversion());
		put(new WaterToNatureConversion().Name(), new WaterToNatureConversion());
		put(new WaterToFireConversion().Name(), new WaterToFireConversion());

		put(new FireToThunderConversion().Name(), new FireToThunderConversion());
		put(new FireToNatureConversion().Name(), new FireToNatureConversion());
		put(new FireToWaterConversion().Name(), new FireToWaterConversion());

		put(new ThunderToWaterConversion().Name(), new ThunderToWaterConversion());
		put(new ThunderToNatureConversion().Name(), new ThunderToNatureConversion());
		put(new ThunderToFireConversion().Name(), new ThunderToFireConversion());

		put(new NatureToThunderConversion().Name(), new NatureToThunderConversion());
		put(new NatureToWaterConversion().Name(), new NatureToWaterConversion());
		put(new NatureToFireConversion().Name(), new NatureToFireConversion());
		// elemental conversions

		// elemental Transfers
		put(new WaterToThunderTransfer().Name(), new WaterToThunderTransfer());
		put(new WaterToNatureTransfer().Name(), new WaterToNatureTransfer());
		put(new WaterToFireTransfer().Name(), new WaterToFireTransfer());

		put(new FireToThunderTransfer().Name(), new FireToThunderTransfer());
		put(new FireToNatureTransfer().Name(), new FireToNatureTransfer());
		put(new FireToWaterTransfer().Name(), new FireToWaterTransfer());

		put(new ThunderToWaterTransfer().Name(), new ThunderToWaterTransfer());
		put(new ThunderToNatureTransfer().Name(), new ThunderToNatureTransfer());
		put(new ThunderToFireTransfer().Name(), new ThunderToFireTransfer());

		put(new NatureToThunderTransfer().Name(), new NatureToThunderTransfer());
		put(new NatureToWaterTransfer().Name(), new NatureToWaterTransfer());
		put(new NatureToFireTransfer().Name(), new NatureToFireTransfer());
		// elemental Transfers

	    }
	}
    };
}
