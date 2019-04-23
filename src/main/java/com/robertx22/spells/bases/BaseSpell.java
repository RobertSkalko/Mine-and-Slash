package com.robertx22.spells.bases;

import com.robertx22.mmorpg.Main;
import com.robertx22.network.MessagePackage;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.uncommon.SLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.IWeighted;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class BaseSpell implements IWeighted {

    public enum SpellType {
	Single_Target_Projectile, Aoe_Projectile, Self_Heal, Aoe_Bomb_Projectile, Restore_Energy, Aoe_Damage_Nova
    }

    public String typeString() {

	return this.Type().toString().replaceAll("_", " ");

    }

    public boolean hasScalingValue() {
	return true;
    }

    public boolean baseValueScalesWithLevel() {
	return true;
    }

    public abstract SpellType Type();

    public abstract String GUID();

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
	return 1000;
    }

    public abstract boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse, SpellItemData data);

    public boolean CanCast(EntityPlayer caster, SpellItemData data) {

	if (!caster.world.isRemote) {

	    UnitData unit = Load.Unit(caster);

	    if (unit != null) {

		if (data.level > unit.getLevel()) {
		    caster.sendMessage(SLOC.chat("too_low_level"));

		    return false;
		}

		if (unit.hasEnoughMana(data.GetManaCost())) {
		    unit.consumeMana(data.GetManaCost());

		    return true;

		} else {
		    if (caster instanceof EntityPlayerMP) {
			Main.Network.sendTo(new MessagePackage("not_enough_mana", MessagePackage.MessageTypes.NoMana),
				(EntityPlayerMP) caster);

		    }

		}
	    }
	}
	return false;

    }

}
