package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.database.map_events.base.MapEvent;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.mobs.*;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.new_content.data_processors.bases.ChunkProcessData;
import com.robertx22.mine_and_slash.new_content.data_processors.bases.SpawnedMob;
import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComplexMobProcessor extends DataProcessor {

    public ComplexMobProcessor() {
        super("spawn", Type.CONTAINS);
    }

    @Override
    public void processImplementation(String key, BlockPos pos, IWorld world, ChunkProcessData data) {

        try {
            String[] parts = StringUtils.split(key, ";");

            MobRarity rarity = null;
            boolean isBoss = false;
            EntityType<? extends MobEntity> type = null;
            boolean addPotion = false;

            Stream<SpawnedMob> filter = null;

            int amount = 1;

            for (String x : parts) {

                int am = 0;
                try {
                    am = Integer.parseInt(x);
                } catch (NumberFormatException e) {
                }

                if (am > 0) {
                    amount = am;
                }

            }

            for (String x : parts) {

                if (x.equals("common")) {
                    rarity = CommonMob.getInstance();
                } else if (x.equals("uncommon")) {
                    rarity = UncommonMob.getInstance();
                } else if (x.equals("rare")) {
                    rarity = RareMob.getInstance();
                } else if (x.equals("epic")) {
                    rarity = EpicMob.getInstance();
                } else if (x.equals("legendary")) {
                    rarity = LegendaryMob.getInstance();
                } else if (x.equals("mythic")) { // TODO
                    rarity = LegendaryMob.getInstance();
                } else if (x.equals("boss")) {
                    rarity = BossMobRarity.getInstance();
                    isBoss = true;
                }

            }

            if (rarity == null) {
                rarity = Rarities.Mobs.random();
            }

            for (String x : parts) {
                ResourceLocation loc = new ResourceLocation(x);
                if (ForgeRegistries.ENTITIES.containsKey(loc)) {
                    type = (EntityType<? extends MobEntity>) ForgeRegistries.ENTITIES.getValue(loc);
                }
            }

            if (type == null) {
                for (String x : parts) {
                    if (x.equals("ranged")) {
                        filter = SpawnedMob.getAll()
                            .stream()
                            .filter(m -> m.isRanged);

                    } else if (x.equals("spider")) {
                        filter = SpawnedMob.getAll()
                            .stream()
                            .filter(m -> m.isSpider);
                    }

                }
            }

            if (filter == null) {
                filter = SpawnedMob.getAll()
                    .stream()
                    .filter(x -> data.getRoom()
                        .canSpawnMob(x));
            }

            if (type == null) {

                if (isBoss) {
                    filter = filter.filter(m -> m.canBeBoss);
                }

                type = RandomUtils.weightedRandom(filter.collect(Collectors.toList())).type;

            }
            for (int i = 0; i < amount; i++) {
                MapEvent.summon(type, world, pos, rarity, addPotion, isBoss);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
