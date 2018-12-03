package com.robertx22.customitems.gearitems.weapons;

import java.util.HashMap;

import com.robertx22.customitems.gearitems.bases.BaseBow;
import com.robertx22.customitems.gearitems.bases.IWeapon;
import com.robertx22.customitems.gearitems.bases.WeaponMechanic;
import com.robertx22.customitems.gearitems.weapon_mechanics.BowWeaponMechanic;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
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

    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
	if (entityLiving instanceof EntityPlayer) {
	    EntityPlayer entityplayer = (EntityPlayer) entityLiving;
	    boolean flag = entityplayer.capabilities.isCreativeMode
		    || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
	    ItemStack itemstack = findAmmo(entityplayer);

	    int i = this.getMaxItemUseDuration(stack) - timeLeft;
	    i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i,
		    !itemstack.isEmpty() || flag);
	    if (i < 0)
		return;

	    if (!itemstack.isEmpty() || flag) {
		if (itemstack.isEmpty()) {
		    itemstack = new ItemStack(net.minecraft.init.Items.ARROW);
		}

		float f = getArrowVelocity(i);

		if ((double) f >= 0.1D) {
		    boolean flag1 = entityplayer.capabilities.isCreativeMode
			    || (itemstack.getItem() instanceof ItemArrow
				    && ((ItemArrow) itemstack.getItem()).isInfinite(itemstack, stack, entityplayer));

		    if (!worldIn.isRemote) {
			ItemArrow itemarrow = (ItemArrow) (itemstack.getItem() instanceof ItemArrow
				? itemstack.getItem()
				: net.minecraft.init.Items.ARROW);

			// HERE I FIRE 3 ARROS WITH OFFSET SO ITS AOE
			for (int y = 0; y < arrowCount; y++) {

			    float rotaY = entityplayer.rotationYaw;

			    if (y == 0) {
				rotaY -= 10;
			    }
			    if (y == 1) {
				rotaY += 10;
			    }

			    EntityArrow entityarrow = itemarrow.createArrow(worldIn, itemstack, entityplayer);
			    entityarrow.shoot(entityplayer, entityplayer.rotationPitch, rotaY, 0.0F, f * 3F, 0.5F);

			    int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

			    if (j > 0) {
				entityarrow.setDamage(entityarrow.getDamage() + (double) j * 0.5D + 0.5D);
			    }

			    int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

			    if (k > 0) {
				entityarrow.setKnockbackStrength(k);
			    }

			    if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
				entityarrow.setFire(100);
			    }

			    stack.damageItem(1, entityplayer);

			    if (flag1 || entityplayer.capabilities.isCreativeMode
				    && (itemstack.getItem() == net.minecraft.init.Items.SPECTRAL_ARROW
					    || itemstack.getItem() == net.minecraft.init.Items.TIPPED_ARROW)) {
				entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
			    }

			    worldIn.spawnEntity(entityarrow);
			}
		    }

		    worldIn.playSound((EntityPlayer) null, entityplayer.posX, entityplayer.posY, entityplayer.posZ,
			    SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F,
			    1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

		    if (!flag1 && !entityplayer.capabilities.isCreativeMode) {
			itemstack.shrink(1);

			if (itemstack.isEmpty()) {
			    entityplayer.inventory.deleteStack(itemstack);
			}
		    }

		    entityplayer.addStat(StatList.getObjectUseStats(this));
		}
	    }
	}
    }

    private ItemStack findAmmo(EntityPlayer player) {
	if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND))) {
	    return player.getHeldItem(EnumHand.OFF_HAND);
	} else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND))) {
	    return player.getHeldItem(EnumHand.MAIN_HAND);
	} else {
	    for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
		ItemStack itemstack = player.inventory.getStackInSlot(i);

		if (this.isArrow(itemstack)) {
		    return itemstack;
		}
	    }

	    return ItemStack.EMPTY;
	}
    }

    protected boolean isArrow(ItemStack stack) {
	return stack.getItem() instanceof ItemArrow;
    }

    // faster loading
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack) {
	return 7500;//// 72000;
    }

}
