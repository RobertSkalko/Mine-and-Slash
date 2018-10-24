package com.robertx22.saveclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.time.StopWatch;

import com.robertx22.customitems.bases.IWeapon;
import com.robertx22.database.stats.types.resources.Energy;
import com.robertx22.database.stats.types.resources.Health;
import com.robertx22.database.stats.types.resources.Mana;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.saveclasses.gearitem.GearItemData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.datasaving.GearSaving;
import com.robertx22.uncommon.datasaving.UnitSaving;
import com.robertx22.uncommon.enumclasses.EntityTypes;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Unit implements Serializable {

	private static final long serialVersionUID = -6658683548383891230L;

	public Unit() {

		if (Stats == null) {
			Stats = com.robertx22.database.lists.Stats.All;
		}

	}

	// transient public EntityLivingBase entity;

	public static Unit Mob(EntityLivingBase en, EntityPlayer player) {

		Unit unit = new Unit();
		unit.entityType = EntityTypes.Mob;

		Unit playerUnit = UnitSaving.Load(player);

		unit.level = playerUnit.level;

		unit.Stats.get("Health").BaseFlat = (int) en.getMaxHealth();

		unit.vanillaHP = (int) en.getMaxHealth();

		return unit;
	}

	public String GUID = UUID.randomUUID().toString();

	public EntityTypes entityType = EntityTypes.Player;
	public int rarity = 0;

	public int experience = 0;
	public int level = 1;

	public int vanillaHP;

	public int GetExpRequiredForLevelUp() {

		return level * 1000;

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

	public void BasicAttack(EntityLivingBase source, EntityLivingBase target, Unit unitsource) {
		int num = (int) unitsource.Stats.get("Damage").Value;
		DamageEffect dmg = new DamageEffect(source, target, num);
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
	/*
	 * public void TakeDamage(int i) { health().Decrease(i); }
	 */

	public void RestoreMana(int i) {
		mana().Increase(i);
	}

	public void RestoreEnergy(int i) {
		energy().Increase(i);
	}

	/*
	 * public void RestoreHealth(int i) { health().Increase(i); }
	 */
	// Stat shortcuts

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

	private void ClearStats() {
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

		StopWatch watch = new StopWatch();
		watch.start();

		ClearStats();

		if (entity instanceof EntityPlayer) {
			AddAllGearStats(entity);
		}

		CalcStats();

		watch.stop();

		// StatsDirty = false;
	}

	private void CalcStats() {

		Stats.values().forEach((Stat stat) -> stat.CalcVal(this));
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
