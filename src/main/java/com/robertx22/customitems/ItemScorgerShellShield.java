package com.robertx22.customitems;

import javax.annotation.Nullable;

import com.robertx22.db_lists.CreativeTabList;
import com.robertx22.mmorpg.Ref;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class ItemScorgerShellShield extends ItemShield {

    @GameRegistry.ObjectHolder(Ref.MODID + ":scorger_shellshield")
    public static final Item ITEM = null;

    public ItemScorgerShellShield() {
	super();
	this.setMaxDamage(548);
	this.setUnlocalizedName("scorger_shellshield");
	setRegistryName("scorger_shellshield");
	this.setCreativeTab(CreativeTabList.MyModTab);
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable EntityLivingBase entity) {
	return true;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemScorgerShellShield());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	registerModel(ITEM, "scorger_shellshield");
    }

    public static void registerModel(Item item) {
	registerModel(item, item.getRegistryName().getResourcePath());
    }

    public static void registerModel(Item item, String modelName) {
	ModelLoader.setCustomModelResourceLocation(item, 0,
		new ModelResourceLocation(Ref.MODID + ":" + modelName, "inventory"));
    }

}