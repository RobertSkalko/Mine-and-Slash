package com.robertx22.spells.bases;

import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.IWeighted;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public abstract class BaseSpell implements IWeighted {

    public abstract String GUID();

    public abstract String Name();

    public abstract int ManaCost();

    // public abstract int Cooldown();

    public abstract int BaseValue();

    public abstract EffectCalculation ScalingValue();

    public int DamageVariance = 50;

    public abstract Elements Element();

    public boolean ScalesWithLevel = true;

    public abstract Item SpellItem();

    public BaseSpell() {

    }

    public abstract String GetDescription(SpellItemData data);

    public int Weight() {
	return this.UncommonWeight;
    }

    public abstract boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data);

    public boolean CanCast(EntityPlayer caster, SpellItemData data) {

	if (!caster.world.isRemote) {

	    UnitData unit = Load.Unit(caster);

	    if (unit != null) {

		if (data.level > unit.getLevel()) {
		    caster.sendMessage(new TextComponentString(
			    TextFormatting.RED + "You aren't high enough level to cast this spell!"));

		    return false;
		}

		if (unit.getUnit().manaData().CurrentValue >= data.GetManaCost()) {
		    unit.getUnit().SpendMana(data.GetManaCost());
		    // UnitSaving.Save(caster, unit);

		    return true;

		} else {
		    caster.sendMessage(new TextComponentString(TextFormatting.RED + "You don't have enough mana!"));

		}
	    }
	}
	return false;

    }

}
