package com.robertx22.saveclasses;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import com.robertx22.database.stat_types.offense.PhysicalDamage;
import com.robertx22.database.stat_types.resources.Energy;
import com.robertx22.database.stat_types.resources.Health;
import com.robertx22.database.stat_types.resources.Mana;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.Stats;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData.EffectTypes;
import com.robertx22.effectdatas.interfaces.WeaponTypes;
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

    public void InitStats() {
	if (MyStats == null) {
	    MyStats = new HashMap<String, StatData>();

	    // adds all stats
	    for (Stat stat : com.robertx22.db_lists.Stats.All.values()) {
		MyStats.put(stat.GUID(), new StatData(stat));
	    }

	} else {
	    // adds new stats
	    for (Stat stat : com.robertx22.db_lists.Stats.All.values()) {
		if (!MyStats.containsKey(stat.Guid())) {
		    MyStats.put(stat.GUID(), new StatData(stat));
		}
	    }
	    // removes stats that were deleted or renamed
	    HashMap<String, StatData> stats = new HashMap<String, StatData>(MyStats);
	    for (Entry<String, StatData> entry : stats.entrySet()) {
		if (!Stats.All.containsKey(entry.getKey())) {
		    MyStats.remove(entry.getKey());
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
	dmg.setEffectType(EffectTypes.BASIC_ATTACK, WeaponTypes.None);
	dmg.Activate();
    }

    // new stat data format, don't ever rename this to "Stats" Or compatibility
    // problems with last version will arrive

    // Stat shortcuts
    public Health health() {
	return (Health) MyStats.get(new Health().Guid()).GetStat();
    }

    public Mana mana() {
	return (Mana) MyStats.get(new Mana().Guid()).GetStat();
    }

    public Energy energy() {
	return (Energy) MyStats.get(new Energy().Guid()).GetStat();
    }

    public StatData healthData() {
	return MyStats.get(new Health().Guid());
    }

    public StatData manaData() {
	return MyStats.get(new Mana().Guid());
    }

    public StatData energyData() {
	return MyStats.get(new Energy().Guid());
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

    public boolean hasEnoughEnergy(int i) {
	return energyData().CurrentValue >= i;
    }

    public boolean hasEnoughMana(int i) {
	return manaData().CurrentValue >= i;
    }

    public static Unit Mob(EntityLivingBase entity, int level, IWorldData data) {

	Unit mob = new Unit(entity);

	UnitData endata = entity.getCapability(EntityData.Data, null);

	endata.SetMobLevel(data, level);
	endata.setRarity(RandomUtils.RandomWithMinRarity(entity).Rank());
	endata.setName(entity);

	mob.MyStats.get(Health.GUID).BaseFlat = (int) entity.getMaxHealth();
	mob.uid = entity.getUniqueID();

	CommonStatUtils.addMapAffixes(data, entity, mob, endata);
	MobStatUtils.AddRandomMobStatusEffects(entity, mob, Rarities.Mobs.get(endata.getRarity()));

	mob.RecalculateMobStats(entity, endata, level, data);

	return mob;

    }

    protected void ClearStats() {
	for (StatData stat : MyStats.values()) {
	    stat.Clear();
	}
    }

    protected void CalcStats(UnitData data) {

	MyStats.values().forEach((StatData stat) -> stat.GetStat().CalcVal(stat, data));
    }

    public void RecalculateStats(EntityLivingBase entity, UnitData data, int level) {

	if (entity instanceof EntityPlayer) {

	    List<GearItemData> gears = PlayerStatUtils.GetEquips(entity); // slow but required

	    ClearStats();
	    PlayerStatUtils.CountWornSets(entity, gears, this);
	    PlayerStatUtils.AddPlayerBaseStats(this);
	    PlayerStatUtils.AddAllGearStats(entity, gears, this, level); // slow, but required
	    CommonStatUtils.AddStatusEffectStats(this, level);
	    PlayerStatUtils.AddAllSetStats(entity, this, level);
	    CommonStatUtils.AddMapAffixStats(this, level);
	    PlayerStatUtils.CalcStatConversionsAndTransfers(this);
	    PlayerStatUtils.CalcTraits(this);
	    CalcStats(data);

	}

    }

    public void RecalculateMobStats(EntityLivingBase entity, UnitData endata, int level, IWorldData world) {

	int tier = 0;
	if (world != null) {
	    tier = world.getTier();
	}

	ClearStats();
	MobStatUtils.AddMobcStats(this, endata.getLevel());
	MobStatUtils.SetMobStrengthMultiplier(this, Rarities.Mobs.get(endata.getRarity()));
	CommonStatUtils.AddStatusEffectStats(this, level);
	CommonStatUtils.AddMapAffixStats(this, level);
	MobStatUtils.AddMobTierStats(this, tier);
	CalcStats(endata);

    }

    public UUID uid;

    public void Heal(EntityLivingBase entity, int healthrestored) {
	entity.heal(HealthUtils.DamageToMinecraftHealth(healthrestored * OnHealDecrease.HEAL_DECREASE, entity));
    }

}
