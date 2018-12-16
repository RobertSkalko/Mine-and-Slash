package com.robertx22.generation;

import java.util.List;

import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class RarityGen {

    public static <T extends Rarity> T Random(int MagicFind, List<IWeighted> list) {

	Rarity rarity = (Rarity) RandomUtils.WeightedRandom(list);

	return (T) rarity;

    }
}
