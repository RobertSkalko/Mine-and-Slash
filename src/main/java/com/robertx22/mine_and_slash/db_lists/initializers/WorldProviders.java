package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.world_providers.*;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;

public class WorldProviders implements ISlashRegistryInit {

    public static IWP byBiome(Biome biome) {
        IWP first = null;

        try {
            first = SlashRegistry.WorldProviders()
                .getList()
                .stream()
                .filter(x -> x.getBiome()
                    .equals(biome))
                .findFirst()
                .get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (first != null) {
            return first;
        } else {
            return new BirchForestIWP(null, null);
        }
    }

    @Override
    public void registerAll() {

        List<BaseWorldProvider> All = new ArrayList<BaseWorldProvider>() {
            {
                {
                    add(new DesertHillsIWP(null, null));
                    add(new SnowyMountainsIWP(null, null));
                    add(new SavannaIWP(null, null));
                    add(new RockDesertIWP(null, null));
                    add(new RockMountainsIWP(null, null));
                    add(new BirchForestIWP(null, null));
                    add(new NetherIWP(null, null));
                    add(new SwampHillsIWP(null, null));
                    add(new RedDesertIWP(null, null));
                    add(new BeachIWP(null, null));
                }

            }

        };

        All.forEach(x -> x.registerToSlashRegistry());

    }

}
