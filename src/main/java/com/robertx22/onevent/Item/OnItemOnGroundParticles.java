package com.robertx22.onevent.Item;

import java.util.HashMap;
import java.util.Random;

import com.robertx22.mmorpg.config.ModConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class OnItemOnGroundParticles {

    static Random rand = new Random();

    static int ticks = 0;

    static int distance = 9;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onRenderItemParticles(ClientTickEvent event) {

	ticks++;

	if (ticks < 17) {
	    return;
	}

	if (ModConfig.Client.RENDER_ITEM_ENTITY_RARITY_PARTICLES == false) {
	    return;
	}

	ticks = 0;

	try {
	    Minecraft mc = Minecraft.getMinecraft();

	    if (mc == null || mc.player == null || mc.world == null) {
		return;
	    }

	    EntityPlayer p = mc.player;

	    AxisAlignedBB box = new AxisAlignedBB(p.getPosition()).grow(distance);

	    for (EntityItem en : p.world.getEntitiesWithinAABB(EntityItem.class, box)) {

		ItemStack stack = en.getItem();
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("rarity")) {

		    int rarity = stack.getTagCompound().getInteger("rarity");

		    if (Color.RARITY_COLORS.containsKey(rarity) == false) {
			continue;
		    }

		    float x = Color.RARITY_COLORS.get(rarity).x;
		    float y = Color.RARITY_COLORS.get(rarity).y;
		    float z = Color.RARITY_COLORS.get(rarity).z;

		    int amount = 5;

		    int yhigh = 0;

		    if (rarity == -1) { // if unique
			yhigh = 1;
			amount = 6;

			en.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, en.posX + rand.nextFloat() * radius - 0.1,
				en.posY + en.height / 2 + rand.nextFloat() * radius - 0.1,
				en.posZ + rand.nextFloat() * radius - 0.1, x, y, z);
		    }

		    for (int i = 0; i < amount; i++) {

			en.world.spawnParticle(EnumParticleTypes.REDSTONE, en.posX + rand.nextFloat() * radius - 0.1,
				en.posY + en.height / 2 + rand.nextFloat() * (radius + yhigh),
				en.posZ + rand.nextFloat() * radius - 0.1, x, y, z);

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
