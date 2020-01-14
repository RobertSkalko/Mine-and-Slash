package com.robertx22.mine_and_slash.database.spells.spell_classes.projectile;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.database.spells.entities.proj.EntityThunderBolt;
import com.robertx22.mine_and_slash.database.spells.items.projectile.ItemThunderBolt;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellThunderBolt extends BaseSpellProjectile {

    public SpellThunderBolt() {
        super();
    }

    @Override
    public Elements getElement() {
        return Elements.Thunder;
    }

    @Override
    public Item getSpellItem() {
        return ItemThunderBolt.ITEM;
    }

    @Override
    public String GUID() {
        return "ThunderBolt";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityThunderBolt(world);
    }

}