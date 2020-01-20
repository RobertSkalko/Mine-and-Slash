package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.database.spells.entities.proj.EntityFrostBolt;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseProjectileSpell;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.world.World;

public class FrostballSpell extends BaseProjectileSpell {

    public FrostballSpell() {
        super();
    }

    @Override
    public Elements getElement() {
        return Elements.Water;
    }

    @Override
    public String GUID() {
        return "FrostBolt";
    }

    @Override
    public BaseElementalBoltEntity projectile(World world) {
        return new EntityFrostBolt(world);
    }

}