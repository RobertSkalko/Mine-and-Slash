package com.robertx22.structures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class GenStructure {

    public static BlockPos findFreeAirSpaceFor(Template template, World world, BlockPos pos) {

	return pos;
    }

    public static void gen(ResourceLocation structure, BlockPos pos, World world, ITemplateProcessor processor) {

	final PlacementSettings settings = new PlacementSettings().setRotation(Rotation.NONE);

	final Template template = world.getSaveHandler().getStructureTemplateManager()
		.getTemplate(world.getMinecraftServer(), structure);

	if (processor != null) {
	    template.addBlocksToWorld(world, pos, processor, settings, 2);
	} else {
	    template.addBlocksToWorld(world, pos, settings);

	}
	// System.out.println(template.getSize() + " ");

    }

}
