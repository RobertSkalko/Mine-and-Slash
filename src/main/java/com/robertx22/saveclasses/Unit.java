package com.robertx22.saveclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.robertx22.database.stats.types.Armor;
import com.robertx22.database.stats.types.CriticalDamage;
import com.robertx22.database.stats.types.CriticalHit;
import com.robertx22.database.stats.types.Damage;
import com.robertx22.database.stats.types.Health;
import com.robertx22.enums.EntityTypes;
import com.robertx22.saving.Saving;
import com.robertx22.stats.Stat;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Unit implements Serializable {

	private static final long serialVersionUID = -6658683548383891230L;

	public Unit() {

	}

	public static Unit Mob() {

		Unit unit = new Unit();

		unit.entityType = EntityTypes.Mob;

		return unit;
	}

	public EntityTypes entityType = EntityTypes.Player;
	public int rarity = 0;

	public int experience = 0;
	public int level = 0;

	public int GetExpRequiredForLevelUp() {

		return level * 1000;

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

		}
	};

	transient public boolean StatsDirty = true;

	public List<GearItemData> GetEquips(EntityLivingBase entity) {

		List<ItemStack> list = new ArrayList<ItemStack>();

		for (ItemStack stack : entity.getEquipmentAndArmor()) {
			list.add(stack);
		}
		list.add(entity.getHeldItemMainhand());

		List<GearItemData> gearitems = new ArrayList<GearItemData>();

		for (ItemStack stack : list) {

			GearItemData gear = Saving.Load(stack.getTagCompound(), GearItemData.class);

			if (gear != null) {
				gearitems.add(gear);

				// System.out.println("Gearitemfound" + gear.toString());
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

				int val = data.GetActualVal(gear.level);

				Stats.get(data.GetBaseMod().GetBaseStat().getClass()).Add(val, data.type);

			}
		}
	}

	public void RecalculateStats(EntityLivingBase entity) {

		ClearStats();

		AddAllGearStats(entity);

		// StatsDirty = false;
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
