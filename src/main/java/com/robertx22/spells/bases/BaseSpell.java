package com.robertx22.spells.bases;

import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.IWeighted;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public abstract class BaseSpell implements IWeighted {

	public abstract String GUID();

	public abstract String Name();

	public abstract int ManaCost();

	public abstract int Cooldown();

	public abstract int BaseDamage();

	public abstract EffectCalculation ScalingDamage();

	public int DamageVariance = 50;

	public abstract Elements Element();

	public boolean ScalesWithLevel = true;

	public abstract Item SpellItem();

	public BaseSpell() {

	}

	public abstract String GetDescription(SpellItemData data);

	public int Weight() {
		return this.NormalWeight;
	}

	public abstract boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data);

	public boolean CanCast(EntityPlayer caster, SpellItemData data) {

		if (!caster.world.isRemote) {
			Unit unit = UnitSaving.Load(caster);

			if (unit.mana().GetCurrentValue() >= data.GetManaCost()) {

				unit.SpendMana(data.GetManaCost());
				UnitSaving.Save(caster, unit);
				return true;
			}

			caster.sendMessage(new TextComponentString("You don't have enough mana!"));

		}
		return false;

	}

}
