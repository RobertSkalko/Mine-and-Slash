package com.robertx22.mine_and_slash.spells.projectile;

import com.robertx22.mine_and_slash.items.spells.projectile.ItemAcidBolt;
import com.robertx22.mine_and_slash.spells.entities.bases.proj.EntityAcidBolt;
import com.robertx22.mine_and_slash.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellAcidBolt extends BaseSpellProjectile {

    public SpellAcidBolt() {
        super();
    }

    @Override
    public Elements Element() {
        return Elements.Nature;
    }

    @Override
    public Item SpellItem() {
        return ItemAcidBolt.ITEM;
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