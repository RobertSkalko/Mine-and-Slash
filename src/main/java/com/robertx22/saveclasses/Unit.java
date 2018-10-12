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

	public int Experience;
	public int Level;

	public int GetExpRequiredForLevelUp() {

		return Level * 1000;

	}

	public void updateClientExpGUI(EntityPlayer player) {

		EntityPlayer clientPlayer = Minecraft.getMinecraft().player;

		float percentXPFilled = (float) Experience / (float) GetExpRequiredForLevelUp();

		clientPlayer.experienceLevel = Level;
		clientPlayer.experience = percentXPFilled;

		player.experienceLevel = Level;
		player.experience = percentXPFilled;
	}

	public HashMap<Class, Stat> Stats = new HashMap<Class, Stat>() {
		{
			put(Health.class, new Health());
			put(Damage.class, new Damage());
			put(Armor.class, new Armor());
			put(CriticalHit.class, new CriticalHit());
			put(CriticalDamage.class, new CriticalDamage());

		}
	};

	public boolean StatsDirty = true;

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

				System.out.println("Gearitemfound" + gear.toString());
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

	}

	public void GiveExp(EntityPlayer player, int i) {

		Experience += i;

		CheckIfLevelUp();

	}

	private void CheckIfLevelUp() {

		if (Experience >= GetExpRequiredForLevelUp()) {
			Experience = 0;
			LevelUp();
		}

	}

	private void LevelUp() {
		Level++;

	}

}
