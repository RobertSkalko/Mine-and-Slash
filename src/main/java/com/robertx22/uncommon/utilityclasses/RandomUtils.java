package com.robertx22.uncommon.utilityclasses;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.robertx22.database.rarities.MobRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.Spells;

import net.minecraft.entity.EntityLivingBase;

public class RandomUtils {

	private static Random ran = new Random();

	public static int RandomRange(int min, int max) {

		int result = ran.nextInt(max - min);

		return result + min;

	}

	public static MobRarity RandomWithMinRarity(EntityLivingBase entity) {

		double y = entity.posY;

		int minRarity = 0;

		if (y < 50) {
			minRarity = 1;
		}
		if (y < 30) {
			minRarity = 2;
		}

		List<MobRarity> rarities = Rarities.Mobs;
		List<MobRarity> after = new ArrayList();

		for (MobRarity rar : rarities) {
			if (rar.Rank() >= minRarity) {
				after.add(rar);
			}
		}

		MobRarity finalRarity = (MobRarity) RandomUtils.WeightedRandom(ListUtils.CollectionToList(after));

		return finalRarity;

	}

	public static boolean roll(int chance) {

		Random ran = new Random();

		double ranNum = ran.nextDouble() * 100;

		if (chance > ranNum) {
			return true;
		}

		return false;
	}

	public static boolean roll(float chance) {

		Random ran = new Random();

		double ranNum = ran.nextDouble() * 100;

		if (chance > ranNum) {
			return true;
		}

		return false;
	}

	public static IWeighted WeightedRandom(List<IWeighted> lootTable) {

		double value = Total(lootTable) * Math.random();
		double weight = 0;

		for (IWeighted item : lootTable) {
			weight += item.Weight();
			if (value < weight)
				return item;
		}

		return null;

	}

	private static int Total(List<IWeighted> list) {

		int total = 0;

		for (IWeighted w : list) {
			total += w.Weight();
		}
		return total;

	}

	public static IWeighted WeightedRandom(Collection<IWeighted> lootTable) {
		List<IWeighted> slots = ListUtils.CollectionToList(Spells.All.values());

		return WeightedRandom(slots);

	}

}
