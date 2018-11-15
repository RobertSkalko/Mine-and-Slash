package com.robertx22.saveclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import com.robertx22.customitems.gearitems.bases.IWeapon;
import com.robertx22.database.lists.Rarities;
import com.robertx22.database.lists.Sets;
import com.robertx22.database.lists.StatusEffects;
import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.stats.types.defense.Armor;
import com.robertx22.database.stats.types.elementals.damage.FireDamage;
import com.robertx22.database.stats.types.elementals.damage.NatureDamage;
import com.robertx22.database.stats.types.elementals.damage.ThunderDamage;
import com.robertx22.database.stats.types.elementals.damage.WaterDamage;
import com.robertx22.database.stats.types.elementals.resist.FireResist;
import com.robertx22.database.stats.types.elementals.resist.NatureResist;
import com.robertx22.database.stats.types.elementals.resist.ThunderResist;
import com.robertx22.database.stats.types.elementals.resist.WaterResist;
import com.robertx22.database.stats.types.offense.CriticalDamage;
import com.robertx22.database.stats.types.offense.CriticalHit;
import com.robertx22.database.stats.types.offense.PhysicalDamage;
import com.robertx22.database.stats.types.resources.Energy;
import com.robertx22.database.stats.types.resources.Health;
import com.robertx22.database.stats.types.resources.Mana;
import com.robertx22.database.status.effects.bases.BaseStatusEffect;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData.EffectTypes;
import com.robertx22.mmorpg.ModConfig;
import com.robertx22.onevent.combat.OnHealDecrease;
import com.robertx22.saveclasses.effects.StatusEffectData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.Set;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;
import com.robertx22.uncommon.datasaving.GearSaving;
import com.robertx22.uncommon.datasaving.UnitSaving;
import com.robertx22.uncommon.utilityclasses.HealthUtils;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class Unit implements Serializable {

	public HashMap<String, Integer> WornSets = new HashMap<String, Integer>();

	public void Save(EntityLivingBase entity) {

		if (entity instanceof EntityPlayer) {
			UnitSaving.Save(entity, this);
		} else {
			if (this.InitialMobSave == false) {
				this.InitialMobSave = true;
				UnitSaving.Save(entity, this);
			}

		}

	}

	public void ReloadStatsAndSave(EntityLivingBase entity) {

		if (entity instanceof EntityPlayer) {
			this.RecalculateStats(entity);
			UnitSaving.Save(entity, this);
		}

	}

	public void ReloadStatsAndDontSave(EntityLivingBase entity) {

		if (entity instanceof EntityPlayer) {
			this.RecalculateStats(entity);
		}

	}

	public boolean InitialMobSave = false;

	private static final long serialVersionUID = -6658683548383891230L;
	private int level = 1;

	public int GetLevel() {
		return level;
	}

	public void SetLevel(int lvl) {

		if (lvl < 1) {
			lvl = 1;

		}
		if (lvl > ModConfig.Server.MAXIMUM_LEVEL) {
			lvl = ModConfig.Server.MAXIMUM_LEVEL;
		}

		this.level = lvl;
	}

	public Unit() {

		if (MyStats == null) {
			MyStats = new HashMap<String, StatData>();

			for (Stat stat : com.robertx22.database.lists.Stats.All.values()) {
				MyStats.put(stat.GUID(), new StatData(stat));
			}

		} else {
			for (Stat stat : com.robertx22.database.lists.Stats.All.values()) {
				if (!MyStats.containsKey(stat.Name())) {
					MyStats.put(stat.GUID(), new StatData(stat));
				}
			}
		}

	}

	public HashMap<String, StatusEffectData> statusEffects = new HashMap<String, StatusEffectData>();
	public String GUID = UUID.randomUUID().toString();

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
	public HashMap<String, StatData> MyStats = null;

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

	transient public boolean StatsDirty = true;

	public List<GearItemData> GetEquips(EntityLivingBase entity) {

		List<ItemStack> list = new ArrayList<ItemStack>();

		for (ItemStack stack : entity.getArmorInventoryList()) {
			if (stack != null) {
				list.add(stack);
			}
		}
		ItemStack weapon = entity.getHeldItemMainhand();
		if (weapon.getItem() instanceof IWeapon) {
			list.add(weapon);
		}

		IBaublesItemHandler baubles = BaublesApi.getBaublesHandler((EntityPlayer) entity);

		for (int i = 0; i < baubles.getSlots(); i++) {
			ItemStack stack = baubles.getStackInSlot(i);
			if (stack != null) {
				list.add(stack);
			}

		}

		List<GearItemData> gearitems = new ArrayList<GearItemData>();

		for (ItemStack stack : list) {

			GearItemData gear = GearSaving.Load(stack);

			if (gear != null) {
				gearitems.add(gear);

			}

		}

		return gearitems;

	}

	protected void ClearStats() {
		for (StatData stat : MyStats.values()) {
			stat.Clear();
		}
	}

	private void CountWornSets(EntityLivingBase entity) {

		this.WornSets = new HashMap<String, Integer>();

		List<GearItemData> gears = GetEquips(entity);

		for (GearItemData gear : gears) {
			if (gear.set != null) {
				String set = gear.set.baseSet;

				if (gear.set != null) {
					if (this.WornSets.containsKey(set)) {
						this.WornSets.put(set, this.WornSets.get(set) + 1);
					} else {
						this.WornSets.put(set, 1);
					}
				}
			}

		}

	}

	private void AddAllSetStats() {

		for (Entry<String, Integer> entry : this.WornSets.entrySet()) {

			Set set = Sets.All.get(entry.getKey());

			for (StatMod mod : set.GetObtainedMods(this)) {

				StatModData data = StatModData.Load(mod, set.StatPercent);

				String name = mod.GetBaseStat().Name();
				if (this.MyStats.containsKey(name)) {
					this.MyStats.get(name).Add(data, this.level);
				}
			}

		}

	}

	private void AddAllGearStats(EntityLivingBase entity) {

		List<GearItemData> gears = GetEquips(entity);

		for (GearItemData gear : gears) {
			if (gear.level > this.level) {
				entity.sendMessage(
						new TextComponentString(gear.GetDisplayName() + " is too high level for you, no stats added!"));
			} else {

				List<StatModData> datas = gear.GetAllStats(gear.level);
				for (StatModData data : datas) {
					StatData stat = MyStats.get(data.GetBaseMod().GetBaseStat().Name());
					if (stat == null) {
						System.out
								.println("Error! can't load a stat called: " + data.GetBaseMod().GetBaseStat().Name());
					} else {
						stat.Add(data, gear.level);

					}
				}
			}
		}
	}

	public void RecalculateStats(EntityLivingBase entity) {

		if (entity instanceof EntityPlayer) {
			// StopWatch watch = new StopWatch();
			// watch.start();
			CountWornSets(entity);
			ClearStats();
			AddPlayerBaseStats();
			AddAllGearStats(entity);
			AddStatusEffectStats();
			AddAllSetStats();
			CalcStats();
			CalcTraits();
			CalcStats();
			// watch.stop();

		} else {
			ClearStats();
			AddMobcStats();
			SetMobStrengthMultiplier();
			AddStatusEffectStats();
			CalcStats();

		}

	}

	private void AddPlayerBaseStats() {

		MyStats.get(Health.GUID).Flat += 10;
		MyStats.get(PhysicalDamage.GUID).Flat += 2;

	}

	protected void AddStatusEffectStats() {

		for (StatusEffectData status : this.statusEffects.values()) {
			List<StatModData> datas = status.GetAllStats(this.level);
			for (StatModData data : datas) {
				StatData stat = MyStats.get(data.GetBaseMod().GetBaseStat().Name());
				if (stat == null) {
					System.out.println("Error! can't load a stat called: " + data.GetBaseMod().GetBaseStat().Name());
				} else {
					stat.Add(data, level);

				}
			}
		}

	}

	protected void CalcTraits() {
		for (StatData stat : MyStats.values()) {

			if (stat.GetStat() instanceof Trait && stat instanceof IAffectsOtherStats) {
				if (stat.Value > 0) {
					IAffectsOtherStats affects = (IAffectsOtherStats) stat;
					affects.TryAffectOtherStats(this);

				}
			}
		}

	}

	protected void CalcStats() {

		MyStats.values().forEach((StatData stat) -> stat.GetStat().CalcVal(stat, this));
	}

	public int vanillaHP;
	public int rarity = 0;

	public static Unit Mob(EntityLivingBase en, int level) {

		Unit mob = new Unit();

		mob.SetLevel(level);
		mob.MyStats.get(Health.GUID).BaseFlat = (int) en.getMaxHealth();
		mob.rarity = RandomUtils.RandomWithMinRarity(en).Rank();
		mob.vanillaHP = (int) en.getMaxHealth();
		mob.uid = en.getUniqueID();

		mob.AddRandomMobStatusEffects();
		mob.RecalculateStats(en);

		return mob;

	}

	private void AddRandomMobStatusEffects() {

		int max = this.GetRarity().MaxMobEffects();

		if (max > 0) {
			if (this.GetRarity().MaxMobEffects() > StatusEffects.All.values().size()) {
				System.out.println("ERROR! Can't have more unique effects than there are effects!");
				max = StatusEffects.All.values().size() - 1;
			}

			int amount = RandomUtils.RandomRange(0, max);

			while (amount > 0) {

				BaseStatusEffect effect = null;

				while (effect == null || this.statusEffects.containsKey(effect.GUID())) {
					effect = (BaseStatusEffect) RandomUtils
							.WeightedRandom(ListUtils.CollectionToList(StatusEffects.All.values()));
				}
				amount--;
				this.statusEffects.put(effect.GUID(), new StatusEffectData(effect));

			}
		}
	}

	public UUID uid;

	public String GetName(EntityLivingBase entity) {
		return TextFormatting.YELLOW + "[Lv:" + this.level + "] " + GetRarity().Color() + GetRarity().Name() + " "
				+ entity.getName();

	}

	private MobRarity GetRarity() {
		return Rarities.Mobs.get(rarity);
	}

	private void AddMobcStats() {

		this.MyStats.get(Health.GUID).Flat += 10 * level;
		this.MyStats.get(Armor.GUID).Flat += 10 * level;
		this.MyStats.get(CriticalHit.GUID).Flat += 5;
		this.MyStats.get(CriticalDamage.GUID).Flat += 20;

		this.MyStats.get(WaterResist.GUID).Flat += 10 * level;
		this.MyStats.get(FireResist.GUID).Flat += 10 * level;
		this.MyStats.get(ThunderResist.GUID).Flat += 10 * level;
		this.MyStats.get(NatureResist.GUID).Flat += 10 * level;

		this.MyStats.get(WaterDamage.GUID).Flat += 10 * level;
		this.MyStats.get(FireDamage.GUID).Flat += 10 * level;
		this.MyStats.get(ThunderDamage.GUID).Flat += 10 * level;
		this.MyStats.get(NatureDamage.GUID).Flat += 10 * level;

		this.MyStats.get(PhysicalDamage.GUID).Flat += 0.4F * level;

	}

	private void SetMobStrengthMultiplier() {

		float stat_multi = GetRarity().StatMultiplier();
		float hpmulti = GetRarity().HealthMultiplier();
		float damagemulti = GetRarity().DamageMultiplier();

		for (StatData stat : MyStats.values()) {
			if (stat.GetStat() instanceof PhysicalDamage) {
				stat.Flat *= damagemulti;
			} else if (stat.GetStat() instanceof Health) {
				stat.Flat *= hpmulti;
			} else {
				stat.Flat *= stat_multi;
			}
		}

	}

	public int experience = 0;

	public int GetExpRequiredForLevelUp() {

		int tens = level / 10;

		if (level < 5) {
			return 250 * level;
		}

		return level * 1000 + (tens * 5000);

	}

	public int GiveExp(EntityPlayer player, int i) {

		i *= ModConfig.Server.EXPERIENCE_MULTIPLIER;

		experience += i;

		if (experience > this.GetExpRequiredForLevelUp()) {

			experience = this.GetExpRequiredForLevelUp();

			if (ModConfig.Server.LEVEL_UPS_COST_TOKEN == false) {

				if (this.CheckIfCanLevelUp() && this.CheckLevelCap()) {
					this.LevelUp(player);
				}
			}

			return i;
		}

		if (CheckIfCanLevelUp()) {
			player.sendMessage(new TextComponentString(
					TextFormatting.YELLOW + "Exp bar full, craft a level up token and use it to level up."));
		}

		return i;

	}

	public boolean CheckIfCanLevelUp() {

		return experience >= GetExpRequiredForLevelUp();

	}

	private boolean CheckLevelCap() {
		return level + 1 <= ModConfig.Server.MAXIMUM_LEVEL;
	}

	public boolean LevelUp(EntityPlayer player) {

		if (!CheckIfCanLevelUp()) {
			player.sendMessage(
					new TextComponentString(TextFormatting.RED + "You don't have enough experience to Level Up."));
		} else if (!CheckLevelCap()) {
			player.sendMessage(new TextComponentString(TextFormatting.RED + "You have already reached maximum level."));
		}

		if (CheckIfCanLevelUp() && CheckLevelCap()) {

			this.SetLevel(level + 1);
			experience = 0;

			player.sendMessage(
					new TextComponentString(TextFormatting.GREEN + "You have Leveled up! Current lvl: " + GetLevel()));

			return true;
		}
		return false;
	}

	public void Heal(EntityLivingBase entity, int healthrestored) {
		entity.heal(HealthUtils.DamageToMinecraftHealth(healthrestored * OnHealDecrease.HEAL_DECREASE, entity));
	}

}
