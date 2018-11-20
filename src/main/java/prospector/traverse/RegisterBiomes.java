package prospector.traverse;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.mmorpg.Ref;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import prospector.traverse.world.biomes.BiomeAridHighland;
import prospector.traverse.world.biomes.BiomeAutumnalWoodedHills;
import prospector.traverse.world.biomes.BiomeAutumnalWoods;
import prospector.traverse.world.biomes.BiomeBadlands;
import prospector.traverse.world.biomes.BiomeCliffs;
import prospector.traverse.world.biomes.BiomeConiferousForest;
import prospector.traverse.world.biomes.BiomeDesertShrubland;
import prospector.traverse.world.biomes.BiomeForestedHills;
import prospector.traverse.world.biomes.BiomeGlacier;
import prospector.traverse.world.biomes.BiomeLushHills;
import prospector.traverse.world.biomes.BiomeLushSwamp;
import prospector.traverse.world.biomes.BiomeMeadow;
import prospector.traverse.world.biomes.BiomeMiniJungle;
import prospector.traverse.world.biomes.BiomeMountainousDesert;
import prospector.traverse.world.biomes.BiomeRedDesert;
import prospector.traverse.world.biomes.BiomeRockyPlains;
import prospector.traverse.world.biomes.BiomeRockyPlateau;
import prospector.traverse.world.biomes.BiomeThicket;
import prospector.traverse.world.biomes.BiomeWoodlands;

@EventBusSubscriber
public class RegisterBiomes {

	public static final BiomeAridHighland aridHighland = new BiomeAridHighland();
	public static final Biome biomeCliffs = new BiomeCliffs();

	public static List<Biome> biomes = new ArrayList();

	@SubscribeEvent
	public static void registerBiome(RegistryEvent.Register<Biome> event) {

		biomes.add(new BiomeAridHighland());
		biomes.add(new BiomeAutumnalWoodedHills());
		biomes.add(new BiomeAutumnalWoods());
		biomes.add(new BiomeBadlands());
		biomes.add(new BiomeConiferousForest(false));
		biomes.add(new BiomeDesertShrubland());
		biomes.add(new BiomeForestedHills(BiomeForest.Type.BIRCH, "birch_forest"));
		biomes.add(new BiomeGlacier(false));
		biomes.add(new BiomeLushHills());
		biomes.add(new BiomeLushSwamp());
		biomes.add(new BiomeMeadow());
		biomes.add(new BiomeMiniJungle());
		biomes.add(new BiomeMountainousDesert());
		biomes.add(new BiomeRedDesert());
		biomes.add(new BiomeRockyPlains());
		biomes.add(new BiomeRockyPlateau());
		biomes.add(new BiomeThicket());
		biomes.add(new BiomeWoodlands());

		for (Biome biome : biomes) {
			event.getRegistry().register(biome.setRegistryName(Ref.MODID, biome.getClass().toString()));
		}

		biomeCliffs.setRegistryName(Ref.MODID, biomeCliffs.getClass().toString());

	}

	public static void initBiomeManagerAndDictionary() {

		for (Biome biome : biomes) {
			BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(biome, 1));
			BiomeDictionary.addTypes(biome, BiomeDictionary.Type.WASTELAND);
		}
		/*
		 * BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(aridHighland, 10));
		 * BiomeManager.addSpawnBiome(aridHighland);
		 * BiomeManager.addStrongholdBiome(aridHighland);
		 * BiomeManager.addVillageBiome(aridHighland, true);
		 * BiomeDictionary.addTypes(aridHighland, BiomeDictionary.Type.COLD,
		 * BiomeDictionary.Type.DRY, BiomeDictionary.Type.MAGICAL);
		 */
	}
}
