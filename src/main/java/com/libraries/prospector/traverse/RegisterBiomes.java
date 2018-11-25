package com.libraries.prospector.traverse;

import java.util.ArrayList;
import java.util.List;

import com.libraries.prospector.traverse.world.biomes.BiomeAridHighland;
import com.libraries.prospector.traverse.world.biomes.BiomeBadlands;
import com.libraries.prospector.traverse.world.biomes.BiomeCliffs;
import com.libraries.prospector.traverse.world.biomes.BiomeRedDesert;
import com.libraries.prospector.traverse.world.biomes.BiomeThicket;
import com.robertx22.mmorpg.Ref;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegisterBiomes {

	public static final BiomeAridHighland ARID_HIGHLAND = new BiomeAridHighland();
	public static final Biome CLIFFS = new BiomeCliffs();
	public static final Biome RED_DESERT = new BiomeRedDesert();
	public static final Biome BADLANDS = new BiomeBadlands();
	public static final Biome THICKET = new BiomeThicket();

	public static List<Biome> biomes = new ArrayList();

	@SubscribeEvent
	public static void registerBiome(RegistryEvent.Register<Biome> event) {

		biomes.add(ARID_HIGHLAND);
		biomes.add(CLIFFS);
		biomes.add(RED_DESERT);
		biomes.add(BADLANDS);
		biomes.add(THICKET);

		/*
		 * biomes.add(new BiomeAridHighland()); biomes.add(new
		 * BiomeAutumnalWoodedHills()); biomes.add(new BiomeAutumnalWoods());
		 * biomes.add(new BiomeBadlands()); biomes.add(new
		 * BiomeConiferousForest(false)); biomes.add(new BiomeDesertShrubland());
		 * biomes.add(new BiomeForestedHills(BiomeForest.Type.BIRCH, "birch_forest"));
		 * biomes.add(new BiomeGlacier(false)); biomes.add(new BiomeLushHills());
		 * biomes.add(new BiomeLushSwamp()); biomes.add(new BiomeMeadow());
		 * biomes.add(new BiomeMiniJungle()); biomes.add(new BiomeMountainousDesert());
		 * biomes.add(new BiomeRedDesert()); biomes.add(new BiomeRockyPlains());
		 * biomes.add(new BiomeRockyPlateau()); biomes.add(new BiomeThicket());
		 * biomes.add(new BiomeWoodlands());
		 */

		for (Biome biome : biomes) {
			event.getRegistry().register(biome.setRegistryName(Ref.MODID, biome.getClass().toString()));
		}

	}

}
