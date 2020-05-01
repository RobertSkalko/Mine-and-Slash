package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.data_generation.unique_dungeons.UniqueDungeon;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import net.minecraft.util.math.BlockPos;

public class UniqueDungeons implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        new UniqueDungeon("test", 50, new BlockPos(0, 0, 0), 0).addToSerializables();

    }
}
