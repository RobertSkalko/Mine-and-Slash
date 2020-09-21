package com.robertx22.mine_and_slash.new_content.dimension;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BiomeRegister {

    @ObjectHolder("mmorpg:dungeon_biome")
    public static Biome DUNGEON_BIOME;

    @SubscribeEvent
    public static void registerAllBiomes(RegistryEvent.Register<Biome> event) {
        event.getRegistry()
            .register(new DungeonBiome().setRegistryName(Ref.MODID, "dungeon_biome"));
    }
}


