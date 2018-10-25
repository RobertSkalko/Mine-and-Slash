package com.robertx22.saveclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.time.StopWatch;

import com.robertx22.customitems.bases.IWeapon;
import com.robertx22.database.lists.Rarities;
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
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData.EffectTypes;
import com.robertx22.saveclasses.gearitem.GearItemData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.Stat;
import com.robertx22.stats.Trait;
import com.robertx22.uncommon.datasaving.GearSaving;
import com.robertx22.uncommon.datasaving.UnitSaving;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Unit implements Serializable {

	private static final long serialVersionUID = -6658683548383891230L;
	public int level = 1;

	public Unit() {

		if (Stats == null) {
			Stats = com.robertx22.database.lists.Stats.All;
		}

	}

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

	public void BasicAttack(EntityLivingBase source, EntityLivingBase target, Unit unitsource) {
		int num = (int) unitsource.Stats.get(PhysicalDamage.GUID).Value;
		DamageEffect dmg = new DamageEffect(source, target, num);
		dmg.Type = EffectTypes.BASIC_ATTACK;
		dmg.Activate();
	}

	public HashMap<String, Stat> Stats = null;

	// Stat shortcuts
	public Health health() {
		return (Health) Stats.get(new Health().Name());
	}

	public Mana mana() {
		return (Mana) Stats.get(new Mana().Name());
	}

	public Energy energy() {
		return (Energy) Stats.get(new Energy().Name());
	}

	public void SpendMana(int i) {
		mana().Decrease(i);
	}

	public void SpendEnergy(int i) {
		energy().Decrease(i);
	}

	public void RestoreMana(int i) {
		mana().Increase(i);
	}

	public void RestoreEnergy(int i) {
		energy().Increase(i);
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
		for (Stat stat : Stats.values()) {
			stat.Clear();
		}
	}

	private void AddAllGearStats(EntityLivingBase entity) {

		List<GearItemData> gears = GetEquips(entity);

		for (GearItemData gear : gears) {
			List<StatModData> datas = gear.GetAllStats();
			for (StatModData data : datas) {
				Stat stat = Stats.get(data.GetBaseMod().GetBaseStat().Name());
				if (stat == null) {
					System.out.println("Error! can't load a stat called: " + data.GetBaseMod().GetBaseStat().Name());
				} else {
					stat.Add(data);

				}
			}
		}
	}

	public void RecalculateStats(EntityLivingBase entity) {

		if (entity instanceof EntityPlayer) {
			StopWatch watch = new StopWatch();
			watch.start();
			ClearStats();
			AddAllGearStats(entity);
			CalcStats();
			CalcTraits();
			CalcStats();
			watch.stop();

		} else {
			ClearStats();
			AddMobcStats();
			SetMobStrengthMultiplier();
			CalcStats();
		}

	}

	protected void CalcTraits() {
		for (Stat stat : Stats.values()) {

			if (stat instanceof Trait && stat instanceof IAffectsOtherStats) {
				if (stat.Value > 0) {
					IAffectsOtherStats affects = (IAffectsOtherStats) stat;
					affects.TryAffectOtherStats(this);

				}
			}
		}

	}

	protected void CalcStats() {

		Stats.values().forEach((Stat stat) -> stat.CalcVal(this));
	}

	public int vanillaHP;
	public int rarity = 0;

	public static Unit Mob(EntityLivingBase en, EntityPlayer player) {

		Unit playerUnit = UnitSaving.Load(player);
		Unit mob = new Unit();

		mob.level = playerUnit.level;
		mob.Stats.get(Health.GUID).BaseFlat = (int) en.getMaxHealth();
		mob.rarity = ((MobRarity) RandomUtils.WeightedRandom(ListUtils.CollectionToList(Rarities.Mobs))).Rank();
		mob.vanillaHP = (int) en.getMaxHealth();

		mob.SetName(en);

		return mob;

	}

	private void SetName(EntityLivingBase entity) {

//entity.setCustomNameTag(GetRarity().Color() + GetRarity().Name() + " " + entity.getName());
		// entity.setAlwaysRenderNameTag(true);
	}

	private MobRarity GetRarity() {
		return Rarities.Mobs.get(rarity);
	}

	private void AddMobcStats() {

		this.Stats.get(Health.GUID).Flat += 10 * level;
		this.Stats.get(Armor.GUID).Flat += 10 * level;
		this.Stats.get(CriticalHit.GUID).Flat += 5;
		this.Stats.get(CriticalDamage.GUID).Flat += 20;

		this.Stats.get(WaterResist.GUID).Flat += 10 * level;
		this.Stats.get(FireResist.GUID).Flat += 10 * level;
		this.Stats.get(ThunderResist.GUID).Flat += 10 * level;
		this.Stats.get(NatureResist.GUID).Flat += 10 * level;

		this.Stats.get(WaterDamage.GUID).Flat += 10 * level;
		this.Stats.get(FireDamage.GUID).Flat += 10 * level;
		this.Stats.get(ThunderDamage.GUID).Flat += 10 * level;
		this.Stats.get(NatureDamage.GUID).Flat += 10 * level;

		this.Stats.get(PhysicalDamage.GUID).Flat += 2 * level;

	}

	private void SetMobStrengthMultiplier() {
		float totalMulti = this.vanillaHP / 10 + GetRarity().StatMultiplier();

		for (Stat stat : Stats.values()) {
			stat.Flat *= totalMulti;
		}

	}

	public int experience = 0;

	public int GetExpRequiredForLevelUp() {

		return level * 1000;

	}

	public void GiveExp(EntityPlayer player, int i) {

		experience += i;

		CheckIfLevelUp();

	}

	private void CheckIfLevelUp() {

		if (experience >= GetExpRequiredForLevelUp()) {
			experience = 0;
			LevelUp();
		}

	}

	private void LevelUp() {
		level++;

	}

}
