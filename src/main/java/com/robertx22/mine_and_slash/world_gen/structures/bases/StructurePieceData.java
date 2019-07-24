package com.robertx22.mine_and_slash.world_gen.structures.bases;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.UUID;

public class StructurePieceData {

    public BlockPos initialPos;
    public IStructurePieceType type;
    public TemplateManager templateManager;
    public ResourceLocation resourceLocation;
    public BlockPos blockPos;
    public Rotation rotation;
    public int height;
    public Biome biome;
    public int lowerIntoGroundBy = 0;
    public String guid = "";

    private StructurePieceData generateGUID() {
        this.guid = UUID.randomUUID().toString();
        return this;
    }

    public StructurePieceData(IStructurePieceType type, TemplateManager templateManager,
                              BlockPos blockPos, Rotation rotation, Biome biome) {

        this.type = type;
        this.templateManager = templateManager;
        this.blockPos = blockPos;
        this.rotation = rotation;
        this.biome = biome;
        this.initialPos = new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ());

        generateGUID();

    }

    public StructurePieceData resource(ResourceLocation loc) {
        this.resourceLocation = loc;
        return this;
    }

    public StructurePieceData height(int height) {
        this.height = height;
        return this;
    }

}
