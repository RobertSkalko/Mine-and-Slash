package com.robertx22.mine_and_slash.spells.aoe_bomb_proj;

import com.robertx22.mine_and_slash.items.spells.aoe_bomb_proj.ItemFireBomb;
import com.robertx22.mine_and_slash.spells.aoe_bomb_proj.bases.BaseBombSpell;
import com.robertx22.mine_and_slash.spells.entities.bases.bomb.EntityFireBomb;
import com.robertx22.mine_and_slash.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellFireBomb extends BaseBombSpell {

    public SpellFireBomb() {
        super();
    }

    @Override
    public Elements Element() {
        return Elements.Fire;
    }

    @Override
    public Item SpellItem() {
        return ItemFireBomb.ITEM;
    }

    @Override
    public String GUID() {
        return "FireBomb";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityFireBomb(world);
    }

}