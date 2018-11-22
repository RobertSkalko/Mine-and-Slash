package com.robertx22.uncommon.commands;

import com.robertx22.dimensions.blocks.MyPortalBlock;
import com.robertx22.dimensions.blocks.TilePortalBlock;
import com.robertx22.generation.MapGen;
import com.robertx22.generation.blueprints.MapBlueprint;
import com.robertx22.uncommon.datasaving.Map;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class PortDimension extends CommandBase {

	@Override
	public String getName() {
		return "port";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/port number_id";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		EntityPlayer player = (EntityPlayer) sender;

		ItemStack testmap = MapGen.Create(new MapBlueprint(1));
		int id = Map.Load(testmap).createDimension(player);

		BlockPos pos = player.getPosition();
		pos = pos.north(3);

		// portla to new dim
		player.world.setBlockState(pos, new MyPortalBlock().getDefaultState(), 2);
		TilePortalBlock portal = new TilePortalBlock(id);
		player.world.setTileEntity(pos, portal);

	}

}