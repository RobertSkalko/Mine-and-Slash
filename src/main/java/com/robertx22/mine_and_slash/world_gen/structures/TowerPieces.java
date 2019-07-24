package com.robertx22.mine_and_slash.world_gen.structures;

import com.robertx22.mine_and_slash.world_gen.processors.BiomeProcessor;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.StructurePieceRegisters;
import com.robertx22.mine_and_slash.world_gen.processors.ChestProcessor;
import com.robertx22.mine_and_slash.world_gen.structures.bases.BasePieces;
import com.robertx22.mine_and_slash.world_gen.structures.bases.StructurePieceData;
import com.robertx22.mine_and_slash.world_gen.structures.bases.TemplatePiece;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TowerPieces extends BasePieces {

    static final String tower = "tower/";
    private static final ResourceLocation TOP_LOC = new ResourceLocation(Ref.MODID, tower + "tower_roof0");
    private static final ResourceLocation MIDDLE_LOC = new ResourceLocation(Ref.MODID, tower + "tower_middle0");
    private static final ResourceLocation BOTTOM_LOC = new ResourceLocation(Ref.MODID, tower + "tower_entrance");
    private static final ResourceLocation FOUNDATION_LOC = new ResourceLocation(Ref.MODID, tower + "tower_foundation");

    static int FOUNDATION_HEIGHT = 4;

    public static void init(StructurePieceData data, List<StructurePiece> pieces,
                            Random ran) {

        int middleAmount = ran.nextInt(3) + 1;

        data.height = 0;

        data.lowerIntoGroundBy = FOUNDATION_HEIGHT;

        data.resource(FOUNDATION_LOC);
        pieces.add(new TowerPiece(data).first());
        data.height += height(data, FOUNDATION_LOC);

        data.resource(BOTTOM_LOC);
        pieces.add(new TowerPiece(data));
        data.height += height(data, BOTTOM_LOC);

        data.resource(MIDDLE_LOC);
        for (int i = 0; i < middleAmount; i++) {
            pieces.add(new TowerPiece(data));
            data.height += height(data, MIDDLE_LOC);
        }

        data.resource(TOP_LOC);
        pieces.add(new TowerPiece(data).last());

    }

    public static class TowerPiece extends TemplatePiece {

        public TowerPiece(StructurePieceData data) {
            super(data);

        }

        @Override
        public List<StructureProcessor> processors() {
            return Arrays.asList(new ChestProcessor(20), new BiomeProcessor(iwp));
        }

        public TowerPiece(TemplateManager templateManager, CompoundNBT nbt) {
            super(StructurePieceRegisters.TOWER, templateManager, nbt);

        }

    }

}
