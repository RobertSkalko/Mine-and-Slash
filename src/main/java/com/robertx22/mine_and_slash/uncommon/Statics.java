package com.robertx22.mine_and_slash.uncommon;

import com.robertx22.mine_and_slash.entities.IBossMob;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Statics {
    public static BlockPos EMPTY_POS = new BlockPos(0, 0, 0);

    public static List<EntityType> allBosses = new ArrayList<>();

    public static List<EntityType> getBossMobs() {

        if (allBosses.isEmpty()) {
            allBosses = ForgeRegistries.ENTITIES.getValues()
                .stream()
                .filter(x -> x instanceof IBossMob)
                .collect(Collectors.toList());

        }

        return allBosses;

    }

}
