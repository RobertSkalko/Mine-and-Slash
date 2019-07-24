package com.robertx22.mine_and_slash.spells.aoe_bomb_proj;

import com.robertx22.mine_and_slash.items.spells.aoe_bomb_proj.ItemIceBomb;
import com.robertx22.mine_and_slash.spells.aoe_bomb_proj.bases.BaseBombSpell;
import com.robertx22.mine_and_slash.spells.entities.bases.bomb.EntityIceBomb;
import com.robertx22.mine_and_slash.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellIceBomb extends BaseBombSpell {

    public SpellIceBomb() {
        super();
    }

    @Override
    public Elements Element() {
        return Elements.Water;
    }

    @Override
    public Item SpellItem() {
        return ItemIceBomb.ITEM;
    }

    @Override
    public String GUID() {
        return "IceBomb";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityIceBomb(world);
    }

}