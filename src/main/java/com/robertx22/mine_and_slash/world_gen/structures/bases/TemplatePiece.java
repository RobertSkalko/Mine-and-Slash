package com.robertx22.mine_and_slash.world_gen.structures.bases;

import com.robertx22.mine_and_slash.database.world_providers.IWP;
import com.robertx22.mine_and_slash.db_lists.initializers.WorldProviders;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.StructureData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.*;

import java.util.List;
import java.util.Random;

public abstract class TemplatePiece extends TemplateStructurePiece {
    public ResourceLocation resourceLocation;
    public Rotation rotation;
    public int height = 0;
    public IWP iwp;
    public boolean isFirst = false;
    public boolean isLast = false;
    public String guid = "";
    public boolean canBeInWater = true;

    int lowerIntoGroundBy = 0;

    public TemplatePiece last() {
        isLast = true;
        return this;
    }

    public TemplatePiece first() {
        isFirst = true;
        return this;
    }

    public abstract List<StructureProcessor> processors();

    public TemplatePiece(IStructurePieceType type, TemplateManager templateManager,
                         CompoundNBT nbt) {
        super(type, nbt);
        loadFromNBT(nbt);
        this.setupTemplateManager(templateManager);

    }

    public void loadFromNBT(CompoundNBT nbt) {
        this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
        this.rotation = Rotation.valueOf(nbt.getString("Rot"));
        this.height = nbt.getInt("num");
        this.iwp = SlashRegistry.WorldProviders().get(nbt.getString("iwp"));
        this.isFirst = nbt.getBoolean("isfirst");
        this.isLast = nbt.getBoolean("islast");
        this.lowerIntoGroundBy = nbt.getInt("lowerby");
        this.guid = nbt.getString("guid");
        this.canBeInWater = nbt.getBoolean("canInWater");

    }

    public TemplatePiece(StructurePieceData data) {
        super(data.type, 0);
        this.resourceLocation = data.resourceLocation;
        this.height = data.height;
        this.templatePosition = data.blockPos.add(0, data.height, 0);
        this.rotation = data.rotation;
        this.iwp = WorldProviders.byBiome(data.biome);
        this.guid = data.guid;
        this.lowerIntoGroundBy = data.lowerIntoGroundBy;
        this.setupTemplateManager(data.templateManager);

    }

    public PlacementSettings setupPlacementSettings() {

        PlacementSettings placementSettings = (new PlacementSettings()).setRotation(this.rotation)
                .setMirror(Mirror.NONE)
                .setCenterOffset(new BlockPos(0, height, 0))
                .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);

        for (StructureProcessor proc : processors()) {
            placementSettings.addProcessor(proc);
        }

        return placementSettings;
    }

    public void setupTemplateManager(TemplateManager templateManager) {
        Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
        PlacementSettings placementSettings = setupPlacementSettings();
        this.setup(template, this.templatePosition, placementSettings);

    }

    @Override
    protected void readAdditional(CompoundNBT nbt) {
        super.readAdditional(nbt);
        nbt.putString("Template", this.resourceLocation.toString());
        nbt.putString("Rot", this.rotation.name());
        nbt.putInt("num", this.height);
        nbt.putString("iwp", this.iwp.GUID());
        nbt.putBoolean("isfirst", isFirst);
        nbt.putBoolean("islast", isLast);
        nbt.putInt("lowerby", lowerIntoGroundBy);
        nbt.putString("guid", guid);
        nbt.putBoolean("canInWater", canBeInWater);

    }

    @Override
    public boolean addComponentParts(IWorld iworld, Random ran,
                                     MutableBoundingBox boundingbox, ChunkPos chunkPos) {

        IWP iwp = WorldUtils.getIWP(iworld);

        if (iwp != null) {

            PlacementSettings placeSettings = this.setupPlacementSettings();

            BlockPos pos = this.templatePosition.add(Template.transformedBlockPos(placeSettings, new BlockPos(0, 0, 0)));

            if (canBeInWater == false) {
                if (WorldUtils.surfaceIsWater(iworld, pos)) {
                    return false; // TODO ALSO SOMEHOW DISABLE OTHER COMPONENTS
                }
            }

            StructureData data = Load.mapData(iworld.getWorld())
                    .getStructuresData()
                    .getData(this);

            int y = data.getY(iworld, pos);

            BlockPos templatePosition = this.templatePosition;

            this.templatePosition = this.templatePosition.add(0, y - 90 - this.lowerIntoGroundBy, 0);

            boolean addedParts = super.addComponentParts(iworld, ran, boundingbox, chunkPos);

            this.templatePosition = templatePosition;

            return addedParts;
        }

        return false;

    }

    @Override
    protected void handleDataMarker(String s, BlockPos blockPos, IWorld iWorld,
                                    Random random,
                                    MutableBoundingBox mutableBoundingBox) {

    }
}