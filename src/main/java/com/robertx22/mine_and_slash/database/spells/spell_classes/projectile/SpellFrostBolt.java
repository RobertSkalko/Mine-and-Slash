package com.robertx22.mine_and_slash.database.spells.spell_classes.projectile;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.database.spells.entities.proj.EntityFrostBolt;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpellProjectile;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.world.World;

public class SpellFrostBolt extends BaseSpellProjectile {

    public SpellFrostBolt() {
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
    public EntityElementalBolt projectile(World world) {
        return new EntityFrostBolt(world);
    }

}