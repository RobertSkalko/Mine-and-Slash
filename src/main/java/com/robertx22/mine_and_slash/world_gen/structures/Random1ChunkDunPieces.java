package com.robertx22.mine_and_slash.world_gen.structures;

import com.robertx22.mine_and_slash.db_lists.Templates;
import com.robertx22.mine_and_slash.mmorpg.registers.common.StructurePieceRegisters;
import com.robertx22.mine_and_slash.world_gen.processors.BiomeProcessor;
import com.robertx22.mine_and_slash.world_gen.processors.ChestProcessor;
import com.robertx22.mine_and_slash.world_gen.structures.bases.BasePieces;
import com.robertx22.mine_and_slash.world_gen.structures.bases.StructurePieceData;
import com.robertx22.mine_and_slash.world_gen.structures.bases.TemplatePiece;
import com.robertx22.mine_and_slash.world_gen.types.FeatureType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Random1ChunkDunPieces {

    private static final List<FeatureType> LIST = Arrays.asList(Templates.dun0, Templates.dun1);

    public static void init(StructurePieceData data, List<StructurePiece> pieces,
                            Random ran) {

        int rannum = ran.nextInt(LIST.size());

        FeatureType dun = LIST.get(rannum);

        data.lowerIntoGroundBy = BasePieces.height(data.templateManager, dun.structureResourceLocation) - dun.lowerByXBlocks;
        data.height = 0;

        data.resource(dun.structureResourceLocation);
        pieces.add(new Dungeon0Piece(data));

    }

    public static class Dungeon0Piece extends TemplatePiece {

        public Dungeon0Piece(StructurePieceData data) {
            super(data);
            this.canBeInWater = false;
        }

        @Override
        public List<StructureProcessor> processors() {
            return Arrays.asList(new ChestProcessor(25), new BiomeProcessor(iwp));
        }

        public Dungeon0Piece(TemplateManager templateManager, CompoundNBT nbt) {
            super(StructurePieceRegisters.DUNGEON_0, templateManager, nbt);

        }

    }

}
