package com.robertx22.onevent.combat;

import java.util.UUID;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnMobSpawn {

	@SubscribeEvent
	public static void onMobSpawn(EntityJoinWorldEvent event) {

		if (!(event.getEntity() instanceof EntityLivingBase)) {
			return;
		}

		EntityLivingBase entity = (EntityLivingBase) event.getEntity();

		if (Minecraft.getMinecraft().player == null) {
			return;
		}
		if (entity.world.isRemote) {
			return;
		}
		if (!entity.hasCapability(EntityData.Data, null)) {
			return;
		}

		EntityPlayer player = GetNearestPlayer(entity);

		if (player != null) {
			Unit check = UnitSaving.Load(entity);

			if (!(entity instanceof EntityPlayer)) {
				if (check == null) {
					Unit unit = Unit.Mob(entity, player);
					unit.RecalculateStats(entity);
					UnitSaving.Save(entity, unit);
				}

			}

			if (entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH)
					.getModifier(UUID.fromString("0bee127e-d6e1-11e8-9f8b-f2801f1b9fd1")) == null) {

				entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH)
						.applyModifier(new AttributeModifier(UUID.fromString("0bee127e-d6e1-11e8-9f8b-f2801f1b9fd1"),
								Ref.MODID + ":hpmod", Integer.MAX_VALUE/* entity.getMaxHealth() * 100 */, 2));
			}

			entity.setHealth(entity.getMaxHealth());

			// System.out.println("hp" + entity.getMaxHealth());
		} else {
			event.setCanceled(true);

			// System.out.println("canceling spawn");

		}

	}

	public static EntityPlayer GetNearestPlayer(Entity entity) {

		if (entity == null || entity.world == null) {
			return null;
		}

		return entity.world.getClosestPlayerToEntity(entity, 50000);

	}

}
