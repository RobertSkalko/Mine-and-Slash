package com.robertx22.mine_and_slash.database.spells.aoe_bomb_proj;

import com.robertx22.mine_and_slash.database.items.spell_items.aoe_bomb_proj.ItemAcidBomb;
import com.robertx22.mine_and_slash.database.spells.aoe_bomb_proj.bases.BaseBombSpell;
import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.database.spells.entities.bases.bomb.EntityAcidBomb;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellAcidBomb extends BaseBombSpell {

    public SpellAcidBomb() {
        super();
    }

    @Override
    public Elements Element() {
        return Elements.Nature;
    }

    @Override
    public Item SpellItem() {
        return ItemAcidBomb.ITEM;
    }

    @Override
    public String GUID() {
        return "AcidBomb";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityAcidBomb(world);
    }

}