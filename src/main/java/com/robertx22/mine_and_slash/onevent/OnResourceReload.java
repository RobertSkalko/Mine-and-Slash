package com.robertx22.mine_and_slash.onevent;

import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RecipesUpdatedEvent;

import java.util.Collection;

public class OnResourceReload {

    public void onGatherData(RecipesUpdatedEvent event) {
    }

    public static void reloadUniqueDungeons() {

        Collection<ResourceLocation> all = MapManager.getServer()
            .getResourceManager()
            .getAllResourceLocations("unique_dungeon", x -> x.contains("unique_dungeon") && x.contains(Ref.MODID));

    }

}
