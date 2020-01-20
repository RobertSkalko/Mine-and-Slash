package com.robertx22.mine_and_slash.database.spells.spell_classes.projectile;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.database.spells.entities.proj.EntityThunderBolt;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseProjectileSpell;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.world.World;

public class SpellThunderBolt extends BaseProjectileSpell {

    public SpellThunderBolt() {
        super();
    }

    @Override
    public Elements getElement() {
        return Elements.Thunder;
    }

    @Override
    public String GUID() {
        return "ThunderBolt";
    }

    @Override
    public BaseElementalBoltEntity projectile(World world) {
        return new EntityThunderBolt(world);
    }

}