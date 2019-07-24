package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.world_gen.structures.FloatingIslandPieces;
import com.robertx22.mine_and_slash.world_gen.structures.TowerPieces;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.world_gen.structures.Random1ChunkDunPieces;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

public class StructurePieceRegisters {

    public static IStructurePieceType TOWER;
    public static IStructurePieceType FLOATING_ISLAND;
    public static IStructurePieceType DUNGEON_0;

    public static void reg() {
        TOWER = IStructurePieceType.register(TowerPieces.TowerPiece::new, Ref.MODID + ":tower_piece");
        DUNGEON_0 = IStructurePieceType.register(Random1ChunkDunPieces.Dungeon0Piece::new, Ref.MODID + ":dungeon0_piece");
        FLOATING_ISLAND = IStructurePieceType.register(FloatingIslandPieces.FloatingIslandPiece::new, Ref.MODID + ":floating_island_piece");
    }

}
