package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBlockSpawner {

    public void spawnBlock(LivingEntity caster, World world, BlockPos pos, BaseSpell spell);
}
