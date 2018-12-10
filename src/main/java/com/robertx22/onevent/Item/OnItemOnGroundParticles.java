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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnItemOnGroundParticles {

    static Random rand = new Random();

    static int ticks = 0;

    static int distance = 9;

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

	try {
	    Minecraft mc = Minecraft.getMinecraft();
	    EntityPlayer p = mc.player;

	    AxisAlignedBB box = new AxisAlignedBB(p.posX - distance, p.posY - distance, p.posZ - distance,
		    p.posX + distance, p.posY + distance, p.posZ + distance);

	    for (Entity en : p.world.getEntitiesWithinAABBExcludingEntity(p, box)) {

		if (en instanceof EntityItem) {

		    ItemStack stack = ((EntityItem) en).getItem();
		    if (stack.hasTagCompound() && stack.getTagCompound().hasKey("rarity")) {

			int rarity = stack.getTagCompound().getInteger("rarity");

			if (Color.RARITY_COLORS.containsKey(rarity) == false) {
			    return;
			}

			float x = Color.RARITY_COLORS.get(rarity).x;
			float y = Color.RARITY_COLORS.get(rarity).y;
			float z = Color.RARITY_COLORS.get(rarity).z;

			int amount = 5;

			for (int i = 0; i < amount; i++) {

			    en.world.spawnParticle(EnumParticleTypes.REDSTONE,
				    en.posX + rand.nextFloat() * radius - 0.1,
				    en.posY + en.height / 2 + rand.nextFloat() * radius - 0.1,
				    en.posZ + rand.nextFloat() * radius - 0.1, x, y, z);

			}

			if (rarity == -1) { // uniques get more particles
			    en.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC,
				    en.posX + rand.nextFloat() * radius - 0.1,
				    en.posY + en.height / 2 + rand.nextFloat() * radius - 0.1,
				    en.posZ + rand.nextFloat() * radius - 0.1, x, y, z);
			}
		    }

		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static float radius = 0.2F;

    static class Color {
	float x;
	float y;
	float z;

	static Color GREEN = new Color(-1, 1, 0);
	static Color BLUE = new Color(-1, -1, 1);
	static Color RED = new Color(0, 0, 0);
	static Color PURPLE = new Color(0, 0, 1);
	static Color YELLOW = new Color(0, 1, 0);
	static Color ORANGE = new Color(0, -0.5F, 0);
	static Color CYAN = new Color(-1, 1, 1);

	public static HashMap<Integer, Color> RARITY_COLORS = new HashMap<Integer, Color>() {
	    {
		put(5, PURPLE);
		put(4, RED);
		put(3, BLUE);
		put(2, YELLOW);
		put(1, GREEN);
		put(-1, YELLOW);

	    }
	};

	public Color(float x, float y, float z) {
	    this.x = x;
	    this.y = y;
	    this.z = z;
	}

    }

}
