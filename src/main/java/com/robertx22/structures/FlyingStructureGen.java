package com.robertx22.structures;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.Template;

public class FlyingStructureGen extends BaseStructureGen {

    public FlyingStructureGen(ResourceLocation structure, BlockPos pos, World world, ITemplateProcessor processor) {
	super(structure, pos, world, processor);

	EntityPlayer player = world.playerEntities.get(0);

	if (player != null) {

	    final Template template = world.getSaveHandler().getStructureTemplateManager()
		    .getTemplate(world.getMinecraftServer(), structure);

	    this.pos = StructureUtils.findEmptySpaceInAirForStructure(player.getPosition(), world, template.getSize());

	}

    }

    @Override
    String chatUnlocAnnouncement() {
	return "flying_building_spawned";

    }

}
