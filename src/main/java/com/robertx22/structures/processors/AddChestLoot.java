package com.robertx22.structures.processors;

import java.util.List;

import com.robertx22.loot.MasterLootGen;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.Template.BlockInfo;

public class AddChestLoot implements ITemplateProcessor {

    @Override
    public BlockInfo processBlock(World world, BlockPos pos, BlockInfo blockInfoIn) {

	if (blockInfoIn.blockState.getBlock().equals(Blocks.CHEST.getDefaultState().getBlock())) {

	    IWorldData data = Load.World(world);

	    List<ItemStack> items = MasterLootGen.gen(5F, data, data.getLevel());

	    while (items.size() < 2) {
		for (ItemStack stack : MasterLootGen.gen(1F, data, data.getLevel())) {
		    items.add(stack);
		}
	    }

	    TileEntityChest chest = new TileEntityChest();

	    for (int i = 0; i < chest.getSizeInventory(); i++) {
		if (items.size() > i) {
		    chest.setInventorySlotContents(i, items.get(i));
		}
	    }

	    return new BlockInfo(blockInfoIn.pos, Blocks.CHEST.getDefaultState(), chest.serializeNBT());

	}

	return blockInfoIn;
    }

}
