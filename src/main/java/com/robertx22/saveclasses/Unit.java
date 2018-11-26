package com.robertx22.saveclasses;

import java.util.HashMap;
import java.util.UUID;

import com.robertx22.database.stats.types.offense.PhysicalDamage;
import com.robertx22.database.stats.types.resources.Energy;
import com.robertx22.database.stats.types.resources.Health;
import com.robertx22.database.stats.types.resources.Mana;
import com.robertx22.db_lists.Rarities;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData.EffectTypes;
import com.robertx22.onevent.combat.OnHealDecrease;
import com.robertx22.saveclasses.effects.StatusEffectData;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.capability.bases.CommonStatUtils;
import com.robertx22.uncommon.capability.bases.MobStatUtils;
import com.robertx22.uncommon.capability.bases.PlayerStatUtils;
import com.robertx22.uncommon.utilityclasses.HealthUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

@Storable
public class Unit {

	@Store
	public HashMap<String, StatData> MyStats = null;

	@Store
	public HashMap<String, Integer> WornSets = new HashMap<String, Integer>();

	@Store
	public HashMap<String, StatusEffectData> statusEffects = new HashMap<String, StatusEffectData>();

	@Store
	public HashMap<String, MapAffixData> mapAffixes = new HashMap<String, MapAffixData>();

	@Store
	public String GUID = UUID.randomUUID().toString();

	public Unit() {
		InitStats();
	}

	public Unit(EntityLivingBase entity) {

		InitStats();

	}

	private void InitStats() {
		if (MyStats == null) {
			MyStats = new HashMap<String, StatData>();

			for (Stat stat : com.robertx22.db_lists.Stats.All.values()) {
				MyStats.put(stat.GUID(), new StatData(stat));
			}

		} else {
			for (Stat stat : com.robertx22.db_lists.Stats.All.values()) {
				if (!MyStats.containsKey(stat.Name())) {
					MyStats.put(stat.GUID(), new StatData(stat));
				}
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj instanceof Unit) {
			return ((Unit) obj).GUID == this.GUID;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return GUID.hashCode();
	}

	public static final float MAXIMUM_EVENT_DMG_MULTI = 30;

	public void MobBasicAttack(EntityLivingBase source, EntityLivingBase target, Unit unitsource, float event_damage) {

		if (event_damage > MAXIMUM_EVENT_DMG_MULTI) {
			event_damage = MAXIMUM_EVENT_DMG_MULTI;
		}

		int num = (int) (unitsource.MyStats.get(PhysicalDamage.GUID).Value * event_damage);
		DamageEffect dmg = new DamageEffect(source, target, num);
		dmg.Type = EffectTypes.BASIC_ATTACK;
		dmg.Activate();
	}

	// new stat data format, don't ever rename this to "Stats" Or compatibility
	// problems with last version will arrive

	// Stat shortcuts
	public Health health() {
		return (Health) MyStats.get(new Health().Name()).GetStat();
	}

	public Mana mana() {
		return (Mana) MyStats.get(new Mana().Name()).GetStat();
	}

	public Energy energy() {
		return (Energy) MyStats.get(new Energy().Name()).GetStat();
	}

	public StatData healthData() {
		return MyStats.get(new Health().Name());
	}

	public StatData manaData() {
		return MyStats.get(new Mana().Name());
	}

	public StatData energyData() {
		return MyStats.get(new Energy().Name());
	}

	public void SpendMana(int i) {
		manaData().Decrease(i);
	}

	public void SpendEnergy(int i) {
		energyData().Decrease(i);
	}

	public void RestoreMana(int i) {
		manaData().Increase(i);
	}

	public void RestoreEnergy(int i) {
		energyData().Increase(i);
	}

	public static Unit Mob(EntityLivingBase entity, int level, IWorldData data) {

		Unit mob = new Unit(entity);

		UnitData endata = entity.getCapability(EntityData.Data, null);

		endata.SetMobLevel(data, level);
		endata.setName(entity);
		endata.setRarity(RandomUtils.RandomWithMinRarity(entity).Rank());

		mob.MyStats.get(Health.GUID).BaseFlat = (int) entity.getMaxHealth();
		mob.uid = entity.getUniqueID();

		MobStatUtils.AddRandomMobStatusEffects(entity, mob, Rarities.Mobs.get(endata.getRarity()));
		// mob.RecalculateStats(entity, endata.getLevel());

		return mob;

	}

	protected void ClearStats() {
		for (StatData stat : MyStats.values()) {
			stat.Clear();
		}
	}

	protected void CalcStats(EntityLivingBase entity) {

		MyStats.values()
				.forEach((StatData stat) -> stat.GetStat().CalcVal(stat, entity.getCapability(EntityData.Data, null)));
	}

	public void RecalculateStats(EntityLivingBase entity, int level) {

		UnitData data = entity.getCapability(EntityData.Data, null);

		if (entity instanceof EntityPlayer) {
			// StopWatch watch = new StopWatch();
			// watch.start();
			ClearStats();
			PlayerStatUtils.CountWornSets(entity, this);
			PlayerStatUtils.AddPlayerBaseStats(this);
			PlayerStatUtils.AddAllGearStats(entity, this, level);
			CommonStatUtils.AddStatusEffectStats(this, level);
			PlayerStatUtils.AddAllSetStats(entity, this, level);
			CommonStatUtils.AddMapAffixStats(this, level);
			CalcStats(entity);
			PlayerStatUtils.CalcTraits(this);
			CalcStats(entity);
			// watch.stop();

		} else {
			ClearStats();
			MobStatUtils.AddMobcStats(this, data.getLevel());
			MobStatUtils.SetMobStrengthMultiplier(this, Rarities.Mobs.get(data.getRarity()));
			CommonStatUtils.AddStatusEffectStats(this, level);
			CommonStatUtils.AddMapAffixStats(this, level);
			CalcStats(entity);

		}

	}

	public UUID uid;

	public void Heal(EntityLivingBase entity, int healthrestored) {
		entity.heal(HealthUtils.DamageToMinecraftHealth(healthrestored * OnHealDecrease.HEAL_DECREASE, entity));
	}

}
