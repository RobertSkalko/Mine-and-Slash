package com.robertx22.spells.bases;

import com.robertx22.classes.IWeighted;
import com.robertx22.saveclasses.SpellItemData;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class BaseSpell implements IWeighted {

	public abstract String Name();

	public BaseSpell() {

	}

	public abstract boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data);

	public boolean CanCast(EntityPlayer caster, SpellItemData data) {

		return true; // TODO ADD MANA

	}

}
