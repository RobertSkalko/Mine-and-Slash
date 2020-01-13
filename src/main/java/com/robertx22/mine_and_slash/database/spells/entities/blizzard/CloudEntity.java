package com.robertx22.mine_and_slash.database.spells.entities.blizzard;

import com.robertx22.mine_and_slash.database.spells.entities.bases.InvisibleEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public abstract class CloudEntity extends InvisibleEntity {

    public CloudEntity(EntityType type, World world) {
        super(type, world);
    }

}
