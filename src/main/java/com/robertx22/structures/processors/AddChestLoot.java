package com.robertx22.structures.processors;

import java.util.List;

import com.robertx22.loot.MasterLootGen;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.monster.EntityZombie;
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

	    EntityZombie zombie = new EntityZombie(world);
	    UnitData mob = new EntityData.DefaultImpl();
	    UnitData player = new EntityData.DefaultImpl();
	    mob.setLevel(data.getLevel(), zombie);
	    mob.setRarity(4);
	    player.setLevel(data.getLevel(), zombie);

	    List<ItemStack> items = MasterLootGen.gen(mob, player, data, new EntityZombie(world));

	    while (items.size() < 2) {
		for (ItemStack stack : MasterLootGen.gen(mob, player, data, new EntityZombie(world))) {
		    items.add(stack);
		}
	    }

	    TileEntityChest chest = new TileEntityChest();

	    for (int i = 0; i < chest.getSizeInventory(); i++) {
		if (items.size() > i) {
		    chest.setInventorySlotContents(i, items.get(i));
		}
	    }

	    return new BlockInfo(blockInfoIn.pos, Blocks.CHEST.getDefaultState(), chest.getTileData());

	}

	return null;
    }

}
