package com.robertx22.customitems.gearitems.weapons;

import java.util.HashMap;

import com.robertx22.customitems.gearitems.MyEntityArrow;
import com.robertx22.customitems.gearitems.bases.BaseBow;
import com.robertx22.customitems.gearitems.bases.BaseWeaponItem;
import com.robertx22.customitems.gearitems.bases.IWeapon;
import com.robertx22.customitems.gearitems.bases.WeaponMechanic;
import com.robertx22.customitems.gearitems.weapon_mechanics.BowWeaponMechanic;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ItemBow extends BaseBow implements IWeapon {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemBow() {

    }

    @Override
    public String Name() {
	return "Bow";
    }

    int arrowCount = 3;

    @Override
    public WeaponMechanic mechanic() {
	return new BowWeaponMechanic();
    }

    // so bow can fire without arrows
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
	ItemStack itemstack = playerIn.getHeldItem(handIn);
	boolean flag = true; // !this.findAmmo(playerIn).isEmpty();

	ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn,
		playerIn, handIn, flag);
	if (ret != null)
	    return ret;

	if (BaseWeaponItem.checkDurability(playerIn, itemstack) == false) {
	    return flag ? new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack)
		    : new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
	} else {
	    playerIn.setActiveHand(handIn);
	    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
	if (entityLiving instanceof EntityPlayer) {

	    EntityPlayer entityplayer = (EntityPlayer) entityLiving;
	    boolean flag = entityplayer.capabilities.isCreativeMode
		    || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;

	    int i = this.getMaxItemUseDuration(stack) - timeLeft;
	    i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, flag);
	    if (i < 0)
		return;

	    float f = getArrowVelocity(i);

	    if ((double) f >= 0.1D) {
		boolean flag1 = entityplayer.capabilities.isCreativeMode;

		if (!worldIn.isRemote) {

		    stack.damageItem(1, entityplayer);

		    UnitData sourcedata = Load.Unit(entityplayer);

		    if (sourcedata.tryUseWeapon(entityplayer, stack) == false) {

			return;

		    }
		    // else shoot

		    // HERE I FIRE 3 ARROS WITH OFFSET SO ITS AOE
		    for (int y = 0; y < arrowCount; y++) {

			float rotaY = entityplayer.rotationYaw;

			if (y == 0) {
			    rotaY -= 10;
			}
			if (y == 1) {
			    rotaY += 10;
			}

			MyEntityArrow entityarrow = new MyEntityArrow(worldIn, entityplayer, sourcedata, stack);
			entityarrow.shoot(entityplayer, entityplayer.rotationPitch, rotaY, 0.0F, f * 3F, 1F);

			int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

			if (k > 0) {
			    entityarrow.setKnockbackStrength(k);
			}

			if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
			    entityarrow.setFire(100);
			}

			worldIn.spawnEntity(entityarrow);
		    }
		}

		worldIn.playSound((EntityPlayer) null, entityplayer.posX, entityplayer.posY, entityplayer.posZ,
			SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F,
			1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

		entityplayer.addStat(StatList.getObjectUseStats(this));
	    }
	}

    }

    // faster loading
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack) {
	return 40000;//// 72000;
    }

}
