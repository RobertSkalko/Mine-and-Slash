package com.robertx22.spells.aoe_bomb_proj;

import com.robertx22.customitems.spells.aoe_bomb_proj.ItemAcidBomb;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.spells.aoe_bomb_proj.bases.BaseBombSpell;
import com.robertx22.spells.aoe_bomb_proj.bases.EntityBombProjectile;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellAcidBomb extends BaseBombSpell {

    public SpellAcidBomb() {
	super();
    }

    static public class EntityAcidBomb extends EntityBombProjectile {

	public EntityAcidBomb(World worldIn) {
	    super(worldIn);

	}

	@Override
	public Elements element() {
	    return Elements.Nature;
	}
    }

    @Override
    public EffectCalculation ScalingValue() {
	return new EffectCalculation(new SpellNatureDamage().Guid(), this.damageScaling);
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