package com.robertx22.mine_and_slash.database.spells.spell_classes.aoe_bomb_proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.database.spells.entities.bomb.EntityFireBomb;
import com.robertx22.mine_and_slash.database.spells.items.aoe_bomb_proj.ItemFireBomb;
import com.robertx22.mine_and_slash.database.spells.spell_classes.aoe_bomb_proj.bases.BaseBombSpell;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellFireBomb extends BaseBombSpell {

    public SpellFireBomb() {
        super();
    }

    @Override
    public Elements getElement() {
        return Elements.Fire;
    }

    @Override
    public Item getSpellItem() {
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