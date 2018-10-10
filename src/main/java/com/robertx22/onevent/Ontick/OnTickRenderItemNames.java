package com.robertx22.onevent.Ontick;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

import com.robertx22.constants.Tags;
import com.robertx22.mmorpg.ModConfig;
import com.robertx22.utilityclasses.ItemUtils;

public class OnTickRenderItemNames {

	int ticks = 0;

	@SubscribeEvent
	public void onTickRenderItemNames(TickEvent.ClientTickEvent event) {

		Minecraft mc = Minecraft.getMinecraft();

		this.ticks += 1;
		if (this.ticks % 60 != 0) {
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
		} else {
			for (Entity e : entities) {

				if (e instanceof EntityItem) {

					ItemStack item = ((EntityItem) e).getItem();

					if ((!(item.hasTagCompound()))) {
						continue;
					}

					int rarity = item.getTagCompound().getInteger(Tags.RARITY_NUMBER);

					if (ItemUtils.isGear(item)) {

						if (rarity >= ModConfig.Options.RENDER_ONLY_RARITIES_GEAR) {
							render(e);
						} else {
							e.removeTag(e.getCustomNameTag());
						}

					}
					if (ItemUtils.isSocket(item.getItem())) {

						if (rarity >= ModConfig.Options.RENDER_ONLY_RARITIES_SOCKETS) {
							render(e);
						} else {
							e.removeTag(e.getCustomNameTag());
						}
					}
					if (ItemUtils.isPowder(item.getItem())) {
						if (ModConfig.Options.HIDE_POWDER_NAME_RENDER) {
							e.removeTag(e.getCustomNameTag());
						} else {
							render(e);
						}
					}
				}

			}
		}
	}

	void render(Entity e) {

		e.setCustomNameTag(((EntityItem) e).getItem().getDisplayName());
		e.setAlwaysRenderNameTag(true);

	}

}
