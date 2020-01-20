package com.robertx22.mine_and_slash.database.spells.spell_classes.projectile;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.database.spells.entities.proj.EntityAcidBolt;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpellProjectile;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.world.World;

public class SpellAcidBolt extends BaseSpellProjectile {

    public SpellAcidBolt() {
        super();
    }

    @Override
    public Elements getElement() {
        return Elements.Nature;
    }

    @Override
    public String GUID() {
        return "AcidBolt";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityAcidBolt(world);
    }

}