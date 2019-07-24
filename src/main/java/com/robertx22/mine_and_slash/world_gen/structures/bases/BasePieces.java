package com.robertx22.mine_and_slash.world_gen.structures.bases;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class BasePieces {

    public static int height(TemplateManager manager, ResourceLocation loc) {
        return manager.getTemplateDefaulted(loc).getSize().getY();

    }

    public static int height(StructurePieceData data, ResourceLocation loc) {
        return data.templateManager.getTemplateDefaulted(loc).getSize().getY();

    }
}
