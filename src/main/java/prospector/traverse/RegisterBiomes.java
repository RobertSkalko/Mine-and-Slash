package prospector.traverse;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import prospector.traverse.world.biomes.BiomeAridHighland;

@EventBusSubscriber
public class RegisterBiomes {

	public static final BiomeAridHighland aridHighland = new BiomeAridHighland();

	@SubscribeEvent
	public static void registerBiome(RegistryEvent.Register<Biome> event) {

		event.getRegistry().registerAll(

		// aridHighland.setRegistryName(Ref.MODID, "BiomeAridHighland")

		);

		// BiomeProvider.allowedBiomes.add(aridHighland);

	}

	public static void initBiomeManagerAndDictionary() {
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
