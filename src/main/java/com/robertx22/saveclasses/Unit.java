package com.robertx22.saveclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.time.StopWatch;

import com.robertx22.database.stats.types.Armor;
import com.robertx22.database.stats.types.CriticalDamage;
import com.robertx22.database.stats.types.CriticalHit;
import com.robertx22.database.stats.types.Damage;
import com.robertx22.database.stats.types.Health;
import com.robertx22.database.stats.types.elementals.damage.FireDamage;
import com.robertx22.enums.EntityTypes;
import com.robertx22.saving.Saving;
import com.robertx22.stats.Stat;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
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

		return unit;
	}

	public HashMap<String, StatModData> BaseStats = new HashMap<String, StatModData>();

	public String GUID = UUID.randomUUID().toString();

	public EntityTypes entityType = EntityTypes.Player;
	public int rarity = 0;

	public int experience = 0;
	public int level = 0;

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

	transient public HashMap<Class, Stat> Stats = new HashMap<Class, Stat>() {
		{
			put(Health.class, new Health());
			put(Damage.class, new Damage());
			put(Armor.class, new Armor());
			put(CriticalHit.class, new CriticalHit());
			put(CriticalDamage.class, new CriticalDamage());
			put(FireDamage.class, new FireDamage());

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

			GearItemData gear = Saving.Load(stack, GearItemData.class);

			if (gear != null) {
				gearitems.add(gear);

			}

		}

		System.out.println("Gearitemsfound" + gearitems.size());

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

				Stats.get(data.GetBaseMod().GetBaseStat().getClass()).Add(data);

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

		AddAllBaseStats();

		CalcStats();

		System.out.println(Stats.values().toString());

		watch.stop();
		System.out.println("Recalc stats takes " + watch.getTime());

		// StatsDirty = false;
	}

	private void AddAllBaseStats() {

		for (java.util.Map.Entry<String, StatModData> entry : BaseStats.entrySet()) {

			this.Stats.get(entry.getValue().GetBaseMod().GetBaseStat()).Add(entry.getValue());

		}

	}

	private void CalcStats() {

		Stats.values().forEach((Stat stat) -> stat.CalcVal());
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
