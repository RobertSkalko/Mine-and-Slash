package com.robertx22.saveclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.stat_types.offense.PhysicalDamage;
import com.robertx22.database.stat_types.resources.Energy;
import com.robertx22.database.stat_types.resources.Health;
import com.robertx22.database.stat_types.resources.Mana;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.Stats;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData.EffectTypes;
import com.robertx22.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.config.DimensionConfigs;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.network.EntityPackage;
import com.robertx22.saveclasses.effects.StatusEffectData;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.capability.bases.CommonStatUtils;
import com.robertx22.uncommon.capability.bases.MobStatUtils;
import com.robertx22.uncommon.capability.bases.PlayerStatUtils;
import com.robertx22.uncommon.utilityclasses.ListUtils;
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

    }

    public void InitMobStats() {

	if (MyStats == null) {
	    MyStats = new HashMap<String, StatData>();
	    // adds all stats
	    for (Stat stat : com.robertx22.db_lists.Stats.All.values()) {
		MyStats.put(stat.GUID(), new StatData(stat));
	    }

	}

    }

    public void InitPlayerStats() {

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
	try {
	    return MyStats.get(new Health().Guid());
	} catch (Exception e) {
	}
	return null;
    }

    public StatData manaData() {
	try {
	    return MyStats.get(new Mana().Guid());
	} catch (Exception e) {

	}
	return null;
    }

    public StatData energyData() {
	try {
	    return MyStats.get(new Energy().Guid());
	} catch (Exception e) {

	}
	return null;
    }

    public static Unit Mob(EntityLivingBase entity, IWorldData data) {

	Unit mob = new Unit();
	mob.InitMobStats();

	UnitData endata = entity.getCapability(EntityData.Data, null);

	endata.SetMobLevelAtSpawn(data, entity);
	endata.setRarity(randomRarity(entity, endata.getLevel()));

	CommonStatUtils.addMapAffixes(data, entity, mob, endata);
	MobStatUtils.AddRandomMobStatusEffects(entity, mob, Rarities.Mobs.get(endata.getRarity()));

	mob.RecalculateStats(entity, endata, endata.getLevel(), data);

	return mob;

    }

    private static int randomRarity(EntityLivingBase entity, int level) {

	double y = entity.posY;

	int minRarity = 0;

	if (entity.dimension == 0) {

	    if (y < 50) {
		minRarity = 1;
	    }
	    if (y < 30) {
		minRarity = 2;
	    }
	}

	List<MobRarity> rarities = Rarities.Mobs;
	List<MobRarity> after = new ArrayList<MobRarity>();

	DimensionConfigs config = ModConfig.Dimensions.getAll().getConfig(entity.dimension);

	for (MobRarity rar : rarities) {
	    if (rar.Rank() >= minRarity) {
		if (rar.Rank() == 4 && config.LEVEL_FOR_MOBS_TO_BE_LEGENDARY > level) {

		} else if (rar.Rank() == 5 && config.LEVEL_FOR_MOBS_TO_BE_MYTHICAL > level) {

		} else {
		    after.add(rar);
		}
	    }
	}

	MobRarity finalRarity = (MobRarity) RandomUtils.WeightedRandom(ListUtils.CollectionToList(after));

	return finalRarity.Rank();

    }

    protected void ClearStats() {

	if (MyStats == null) {
	    this.InitPlayerStats();
	}

	for (StatData stat : MyStats.values()) {
	    stat.Clear();
	}
    }

    protected void CalcStats(UnitData data) {

	MyStats.values().forEach((StatData stat) -> stat.GetStat().CalcVal(stat, data));
    }

    class DirtyCheck {
	int hp;

	public boolean isDirty(DirtyCheck newcheck) {

	    if (newcheck.hp != hp) {
		return true;
	    }

	    return false;

	}

    }

    /**
     * @return checks if it should be synced to clients. Clients currently only see
     *         health and status effects
     */
    private DirtyCheck getDirtyCheck() {

	DirtyCheck check = new DirtyCheck();

	check.hp = (int) MyStats.get(Health.GUID).Value;

	return check;
    }

    public void RecalculateStats(EntityLivingBase entity, UnitData data, int level, IWorldData world) {

	if (data.getUnit() == null) {
	    data.setUnit(this, entity);
	}

	DirtyCheck old = getDirtyCheck();

	List<GearItemData> gears = PlayerStatUtils.GetEquips(entity); // slow but required

	Unit copy = this.Clone();

	int tier = 0;
	if (world != null) {
	    tier = world.getTier();
	}

	ClearStats();

	MyStats.get(Health.GUID).Flat += entity.getMaxHealth() * data.getLevel();

	if (entity instanceof EntityPlayer) {
	    PlayerStatUtils.AddPlayerBaseStats(data, this);

	} else {
	    MobStatUtils.AddMobcStats(this, data.getLevel());
	    MobStatUtils.SetMobStrengthMultiplier(this, Rarities.Mobs.get(data.getRarity()));
	    MobStatUtils.AddMobTierStats(this, tier);

	}

	PlayerStatUtils.CountWornSets(entity, gears, this);
	PlayerStatUtils.AddAllGearStats(entity, gears, this, level); // slow, but required
	CommonStatUtils.AddStatusEffectStats(this, level);
	PlayerStatUtils.AddAllSetStats(entity, this, level);
	CommonStatUtils.AddMapAffixStats(this, level);
	PlayerStatUtils.CalcStatConversionsAndTransfers(copy, this);
	PlayerStatUtils.CalcTraits(data);

	CalcStats(data);

	DirtyCheck newcheck = getDirtyCheck();

	if (old.isDirty(newcheck)) {
	    Main.Network.sendToAllTracking(new EntityPackage(entity, data), entity);
	}

    }

    private Unit Clone() {

	Unit clone = new Unit();
	if (this.MyStats != null) {
	    clone.MyStats = new HashMap<String, StatData>(this.MyStats);
	} else {
	    clone.MyStats = new HashMap<String, StatData>();
	}

	return clone;

    }

}
