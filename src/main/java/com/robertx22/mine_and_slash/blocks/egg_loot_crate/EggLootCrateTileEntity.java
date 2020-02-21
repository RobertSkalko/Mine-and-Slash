package com.robertx22.mine_and_slash.blocks.egg_loot_crate;

import com.robertx22.mine_and_slash.blocks.bases.BaseLootCrateTileEntity;
import com.robertx22.mine_and_slash.blocks.conditions.LootCrateCondition;
import com.robertx22.mine_and_slash.blocks.conditions.NoMobAroundCondition;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.MasterLootGen;
import com.robertx22.mine_and_slash.mmorpg.registers.common.TileEntityRegister;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.List;

public class EggLootCrateTileEntity extends BaseLootCrateTileEntity {

    public EggLootCrateTileEntity() {
        super(TileEntityRegister.EGG_LOOT_CRATE.get());
    }

    @Override
    public List<ItemStack> generateLoot(PlayerEntity player) {
        return MasterLootGen.generateLoot(new LootInfo(player).setMinimum(1));

    }

    public int getTimesToDrop() {
        return 5;
    }

    @Override
    public LootCrateCondition condition() {
        return new NoMobAroundCondition(4);
    }
}
