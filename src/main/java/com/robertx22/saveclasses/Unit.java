package com.robertx22.saveclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.time.StopWatch;

import com.robertx22.baubles.api.BaublesApi;
import com.robertx22.baubles.api.cap.IBaublesItemHandler;
import com.robertx22.database.stats.types.Armor;
import com.robertx22.database.stats.types.CriticalDamage;
import com.robertx22.database.stats.types.CriticalHit;
import com.robertx22.database.stats.types.Damage;
import com.robertx22.database.stats.types.Health;
import com.robertx22.database.stats.types.elementals.damage.FireDamage;
import com.robertx22.datasaving.Saving;
import com.robertx22.enumclasses.EntityTypes;
import com.robertx22.stats.Stat;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Unit implements Serializable {

	private static final long serialVersionUID = -6658683548383891230L;

	public Unit(EntityLivingBase en) {
		this.entity = en;
	}

	transient public EntityLivingBase entity;

	public static Unit Mob(EntityLivingBase en) {

		Unit unit = new Unit(en);
		unit.entityType = EntityTypes.Mob;

		unit.Stats.get("Health").BaseFlat = (int) en.getMaxHealth();

		return unit;
	}

	public String GUID = UUID.randomUUID().toString();

	public EntityTypes entityType = EntityTypes.Player;
	public int rarity = 0;

	public int experience = 0;
	public int level = 1;

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

	public void updateClientExpGUI(EntityPlayer player) {

		EntityPlayer clientPlayer = Minecraft.getMinecraft().player;

		float percentXPFilled = (float) experience / (float) GetExpRequiredForLevelUp();

		clientPlayer.experienceLevel = level;
		clientPlayer.experience = percentXPFilled;

		player.experienceLevel = level;
		player.experience = percentXPFilled;
	}

	public HashMap<String, Stat> Stats = new HashMap<String, Stat>() {
		{
			put(new Health().Name(), new Health());
			put(new Damage().Name(), new Damage());
			put(new Armor().Name(), new Armor());
			put(new CriticalHit().Name(), new CriticalHit());
			put(new CriticalDamage().Name(), new CriticalDamage());
			put(new FireDamage().Name(), new FireDamage());

		}

	};

	transient public boolean StatsDirty = true;

	public List<GearItemData> GetEquips() {

		List<ItemStack> list = new ArrayList<ItemStack>();

		for (ItemStack stack : entity.getArmorInventoryList()) {
			if (stack != null) {
				list.add(stack);
			}
		}
		list.add(entity.getHeldItemMainhand());

		IBaublesItemHandler baubles = BaublesApi.getBaublesHandler((EntityPlayer) entity);

		for (int i = 0; i < baubles.getSlots(); i++) {
			ItemStack stack = baubles.getStackInSlot(i);
			if (stack != null) {
				list.add(stack);
			}

		}

		List<GearItemData> gearitems = new ArrayList<GearItemData>();

		for (ItemStack stack : list) {

			GearItemData gear = Saving.Load(stack);

			if (gear != null) {
				gearitems.add(gear);

			}

		}

		// System.out.println("Gearitemsfound" + gearitems.size());

		return gearitems;

	}

	private void ClearStats() {
		for (Stat stat : Stats.values()) {
			stat.Clear();
		}
	}

	private void AddAllGearStats() {

		List<GearItemData> gears = GetEquips();

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

	public void RecalculateStats() {

		StopWatch watch = new StopWatch();
		watch.start();

		ClearStats();

		if (entity instanceof EntityPlayer) {
			AddAllGearStats();
		}

		CalcStats();

		watch.stop();
		// System.out.println(Stats().toString());

		System.out.println(Stats.get("Critical Hit").GetValue(this));

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
