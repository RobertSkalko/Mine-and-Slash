package com.robertx22.mine_and_slash.blocks.conditions;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityTypeUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class NoMobAroundCondition extends LootCrateCondition {

    int radius = 1;

    public NoMobAroundCondition(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean canOpenCrate(PlayerEntity player, TileEntity box) {
        return EntityFinder.start(player, LivingEntity.class, new Vec3d(box.getPos()))
                .radius(radius)
                .addPredicate(x -> EntityTypeUtils.isMob(x))
                .build()
                .size() < 1;
    }

    @Override
    public ITextComponent tellCondition() {
        return new StringTextComponent("You cannot open this crate while mobs are around.");
    }
}
