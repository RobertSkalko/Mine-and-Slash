package com.robertx22.spells.aoe_bomb_proj;

import com.robertx22.customitems.spells.aoe_bomb_proj.ItemFireBomb;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.spells.aoe_bomb_proj.bases.BaseBombSpell;
import com.robertx22.spells.aoe_bomb_proj.bases.EntityBombProjectile;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellFireBomb extends BaseBombSpell {

    public SpellFireBomb() {
	super();
    }

    static public class EntityFireBomb extends EntityBombProjectile {

	public EntityFireBomb(World worldIn) {
	    super(worldIn);

	}

	@Override
	public Elements element() {
	    return Elements.Fire;
	}
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(new SpellFireDamage().Guid(), this.damageScaling);
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