package com.robertx22.onevent.ontick;

import java.util.List;

import com.robertx22.mmorpg.ModConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class OnTickRenderItemNames {

	static int ticks = 0;

	@SubscribeEvent
	public static void onTickRenderItemNames(TickEvent.ClientTickEvent event) {

		Minecraft mc = Minecraft.getMinecraft();

		ticks += 1;
		if (ticks % 60 != 0) {
			return;
		}
		if (mc.world == null) {
			return;
		}
		if (mc.world.loadedEntityList == null) {

			return;
		}
		if (mc.world.loadedEntityList.size() == 0) {

			return;
		}

		ticks = 0;

		mc.mcProfiler.startSection("item_display");

		List<Entity> entities = mc.world.loadedEntityList;

		if (ModConfig.Options.DISABLE_ITEM_NAME_RENDER) {

			for (Entity e : entities) {

				if (e instanceof EntityItem) {
					e.removeTag(e.getCustomNameTag());
				}
			}
		} // else {
			// for (Entity e : entities) {

		// if (e instanceof EntityItem) {

		/*
		 * ItemStack item = ((EntityItem) e).getItem();
		 * 
		 * if ((!(item.hasTagCompound()))) { continue; }
		 * 
		 * int rarity = item.getTagCompound().getInteger(Tags.RARITY_NUMBER);
		 * 
		 * 
		 * if (true/*ItemUtils.isGear(item)){
		 * 
		 * if (rarity >= ModConfig.Options.RENDER_ONLY_RARITIES_GEAR) { render(e); }
		 * else { e.removeTag(e.getCustomNameTag()); }
		 * 
		 * } if (ItemUtils.isSocket(item.getItem())) {
		 * 
		 * if (rarity >= ModConfig.Options.RENDER_ONLY_RARITIES_SOCKETS) { render(e); }
		 * else { e.removeTag(e.getCustomNameTag()); } } if
		 * (ItemUtils.isPowder(item.getItem())) { if
		 * (ModConfig.Options.HIDE_POWDER_NAME_RENDER) {
		 * e.removeTag(e.getCustomNameTag()); } else { render(e); } } }}}
		 */

	}

	void render(Entity e) {

		e.setCustomNameTag(((EntityItem) e).getItem().getDisplayName());
		e.setAlwaysRenderNameTag(true);

	}

}
