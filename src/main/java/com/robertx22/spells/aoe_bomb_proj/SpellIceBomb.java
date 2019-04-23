package com.robertx22.spells.aoe_bomb_proj;

import com.robertx22.customitems.spells.aoe_bomb_proj.ItemIceBomb;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.spells.aoe_bomb_proj.bases.BaseBombSpell;
import com.robertx22.spells.aoe_bomb_proj.bases.EntityBombProjectile;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellIceBomb extends BaseBombSpell {

    public SpellIceBomb() {
	super();
    }

    static public class EntityIceBomb extends EntityBombProjectile {

	public EntityIceBomb(World worldIn) {
	    super(worldIn);

	}

	@Override
	public Elements element() {
	    return Elements.Water;
	}
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(new SpellWaterDamage().Guid(), this.damageScaling);
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