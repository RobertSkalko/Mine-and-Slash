package com.robertx22.mine_and_slash.database.spells.spell_classes.projectile;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.database.spells.entities.proj.EntityFireBolt;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseProjectileSpell;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.world.World;

public class SpellFireBolt extends BaseProjectileSpell {

    public SpellFireBolt() {
        super();
    }

    @Override
    public Elements getElement() {
        return Elements.Fire;
    }

    @Override
    public String GUID() {
        return "FireBolt";
    }

    @Override
    public BaseElementalBoltEntity projectile(World world) {
        return new EntityFireBolt(world);
    }

}