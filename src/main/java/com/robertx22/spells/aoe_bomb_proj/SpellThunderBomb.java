package com.robertx22.spells.aoe_bomb_proj;

import com.robertx22.customitems.spells.aoe_bomb_proj.ItemThunderBomb;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.spells.aoe_bomb_proj.bases.BaseBombSpell;
import com.robertx22.spells.aoe_bomb_proj.bases.EntityBombProjectile;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellThunderBomb extends BaseBombSpell {

    public SpellThunderBomb() {
	super();
    }

    static public class EntityThunderBomb extends EntityBombProjectile {

	public EntityThunderBomb(World worldIn) {
	    super(worldIn);

	}

	@Override
	public Elements element() {
	    return Elements.Thunder;
	}
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(new SpellThunderDamage().Guid(), this.damageScaling);
    }

    @Override

    public Elements Element() {
	return Elements.Thunder;
    }

    @Override
    public Item SpellItem() {
	return ItemThunderBomb.ITEM;
    }

    @Override
    public String GUID() {
	return "ThunderBomb";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
	return new EntityThunderBomb(world);
    }

}