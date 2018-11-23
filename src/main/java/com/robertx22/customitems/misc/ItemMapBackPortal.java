package com.robertx22.customitems.misc;

import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class ItemMapBackPortal extends Item {

	@GameRegistry.ObjectHolder(Ref.MODID + ":map_back_portal")
	public static final Item ITEM = null;

	public ItemMapBackPortal() {

		RegisterItemUtils.RegisterItemName(this, "map_back_portal");
		this.setMaxStackSize(1);
		this.setMaxDamage(0);

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		if (!worldIn.isRemote) {
			try {

				IWorldData data = Load.World(worldIn);
				if (data != null) {
					if (data.isMapWorld()) {

						data.teleportPlayerBack(playerIn);

						return new ActionResult<ItemStack>(EnumActionResult.PASS, ItemStack.EMPTY);

					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new ItemMapBackPortal());
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		RegisterUtils.registerRender(ITEM);
	}

}
