package com.robertx22.customitems;

import javax.annotation.Nullable;

import com.robertx22.db_lists.CreativeTabList;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class MyShield extends ItemShield {

    @GameRegistry.ObjectHolder(Ref.MODID + ":shield0")
    public static final Item ITEM = null;

    public MyShield() {

	super();

	this.setMaxStackSize(1);
	this.setMaxDamage(0);
	this.setCreativeTab(CreativeTabList.MyModTab);
	RegisterItemUtils.RegisterItemName(this, "shield0");

	// this.setTileEntityItemStackRenderer(new
	// ShieldRenderer(TileEntityItemStackRenderer.instance));

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new MyShield());
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable EntityLivingBase entity) {
	return true;
    }
}