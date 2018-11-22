package com.libraries.prospector.traverse.world.features;

import com.libraries.prospector.traverse.world.WorldGenConstants;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class TraverseTreeGenerator extends WorldGenTrees implements WorldGenConstants {

    public boolean isWorldGen = true;

    public TraverseTreeGenerator(boolean isWorldGen) {
        super(!isWorldGen);
        this.isWorldGen = isWorldGen;
    }

    public TraverseTreeGenerator(boolean isWorldGen, int minTreeHeight, IBlockState logState, IBlockState leavesState, boolean vinesGrow) {
        super(!isWorldGen, minTreeHeight, logState, leavesState, vinesGrow);
        this.isWorldGen = isWorldGen;
    }

    public TraverseTreeGenerator(boolean isWorldGen, int minTreeHeight, IBlockState logState, IBlockState leavesState) {
        super(!isWorldGen, minTreeHeight, logState, leavesState, false);
        this.isWorldGen = isWorldGen;
    }

    public TraverseTreeGenerator(boolean isWorldGen, IBlockState logState, IBlockState leavesState) {
        super(!isWorldGen, 4, logState, leavesState, false);
        this.isWorldGen = isWorldGen;
    }
}