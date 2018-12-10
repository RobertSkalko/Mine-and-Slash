package com.robertx22.onevent.Item;

import java.util.HashMap;
import java.util.Random;

import com.robertx22.mmorpg.ModConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnItemOnGroundParticles {

    static Random rand = new Random();

    static int ticks = 0;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onRenderItemParticles(RenderGameOverlayEvent event) {

	if (event.isCancelable() || event.getType() != ElementType.EXPERIENCE) {
	    return;
	}

	ticks++;

	if (ticks < 15) {
	    return;
	}

	if (ModConfig.Client.RENDER_ITEM_ENTITY_RARITY_PARTICLES == false) {
	    return;
	}

	ticks = 0;

	Minecraft mc = Minecraft.getMinecraft();
	EntityPlayer p = mc.player;

	for (Entity en : mc.player.world.loadedEntityList) {

	    if (en instanceof EntityItem) {

		if (en.isInRangeToRender3d(p.posX, p.posY, p.posZ) == false) {
		    return;
		}

		ItemStack stack = ((EntityItem) en).getItem();

		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("rarity")) {

		    int rarity = stack.getTagCompound().getInteger("rarity");
		    int x = Color.BY_ELEMENT.get(rarity).x;
		    int y = Color.BY_ELEMENT.get(rarity).y;
		    int z = Color.BY_ELEMENT.get(rarity).z;

		    if (rarity != 0) {

			for (int i = 0; i < 5; i++) {

			    en.world.spawnParticle(EnumParticleTypes.REDSTONE, en.posX + rand.nextFloat() * 0.2 - 0.1,
				    en.posY + en.height / 2 + rand.nextFloat() * 0.2 - 0.1,
				    en.posZ + rand.nextFloat() * 0.2 - 0.1, x, y, z);

			}
		    }
		}
	    }
	}

    }

    public static float radius = 0.5F;

    static class Color {
	int x;
	int y;
	int z;

	static Color GREEN = new Color(-1, 1, 0);
	static Color BLUE = new Color(-1, -1, 1);
	static Color RED = new Color(0, 0, 0);
	static Color PURPLE = new Color(0, 0, 1);
	static Color YELLOW = new Color(0, 1, 0);

	public static HashMap<Integer, Color> BY_ELEMENT = new HashMap<Integer, Color>() {
	    {
		put(5, PURPLE);
		put(4, RED);
		put(3, BLUE);
		put(2, YELLOW);
		put(1, GREEN);
		put(0, new Color(0, 0, 0));
		put(-1, YELLOW);

	    }
	};

	public Color(int x, int y, int z) {
	    this.x = x;
	    this.y = y;
	    this.z = z;
	}

    }

}
