package com.robertx22.structures;

import com.robertx22.uncommon.SLOC;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

abstract public class BaseStructureGen {

    ResourceLocation res;
    BlockPos pos;
    World world;
    ITemplateProcessor processor;

    abstract String chatUnlocAnnouncement();

    public BaseStructureGen(ResourceLocation structure, BlockPos pos, World world, ITemplateProcessor processor) {
	this.res = structure;
	this.pos = pos;
	this.world = world;
	this.processor = processor;
    }

    public void generate() {

	try {
	    final PlacementSettings settings = new PlacementSettings().setRotation(Rotation.NONE);

	    final Template template = world.getSaveHandler().getStructureTemplateManager()
		    .getTemplate(world.getMinecraftServer(), res);

	    if (processor != null) {
		template.addBlocksToWorld(world, pos, processor, settings, 2);
	    } else {
		template.addBlocksToWorld(world, pos, settings);
	    }

	    announce();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private void announce() {

	for (EntityPlayer player : world.playerEntities) {
	    player.sendMessage(SLOC.chat(this.chatUnlocAnnouncement()));
	}
    }

}
