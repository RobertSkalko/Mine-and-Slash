package com.robertx22.mine_and_slash.database.spells.spell_classes.aoe_bomb_proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.database.spells.entities.bomb.EntityAcidBomb;
import com.robertx22.mine_and_slash.database.spells.items.aoe_bomb_proj.ItemAcidBomb;
import com.robertx22.mine_and_slash.database.spells.spell_classes.aoe_bomb_proj.bases.BaseBombSpell;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellAcidBomb extends BaseBombSpell {

    private SpellAcidBomb() {
    }

    public static SpellAcidBomb getInstance() {
        return SingletonHolder.INSTANCE;
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

    private static class SingletonHolder {
        private static final SpellAcidBomb INSTANCE = new SpellAcidBomb();
    }
}