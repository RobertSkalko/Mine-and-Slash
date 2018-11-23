package com.robertx22.onevent.combat;

import java.util.HashMap;

import com.robertx22.mmorpg.ModConfig;
import com.robertx22.onevent.ontick.EntityUpdate;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.UnitSaving;
import com.robertx22.uncommon.enumclasses.AffectedEntities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
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

		if (entity.world.isRemote) {
			return;
		}

		if (!entity.hasCapability(EntityData.Data, null)) {
			return;
		}

		try {
			IWorldData data = event.getWorld().getCapability(WorldData.Data, null);

			if (!(entity instanceof EntityPlayer)) {
				if (entity instanceof IMob || entity instanceof EntityMob) {
					if (event.getWorld().hasCapability(WorldData.Data, null)) {

						Unit check = UnitSaving.Load(entity);
						UnitData endata = entity.getCapability(EntityData.Data, null);

						if (check == null) {
							int level = GetMobLevel(data, entity);
							Unit unit = Unit.Mob(entity, level, data);
							unit = addMapAffixes(data, entity, unit, endata);

							endata.forceSetUnit(unit);
							endata.forceRecalculateStats(entity);

							if (endata.getRarity() == 5 && ModConfig.Client.ANNOUNCE_WORLD_BOSS_SPAWN) {
								AnnounceWorldBossSpawn(entity, unit);
							}

							if (unit != null) {
								EntityUpdate.syncEntityToClient(entity);
							}
						}
					}
				}
			} else {
				Unit unit = UnitSaving.Load(entity);
				UnitData endata = entity.getCapability(EntityData.Data, null);
				unit = addMapAffixes(data, entity, unit, endata);
				endata.setUnit(unit, entity);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Unit addMapAffixes(IWorldData worlddata, EntityLivingBase entity, Unit unit, UnitData endata) {
		if (worlddata.isMapWorld()) {

			unit.mapAffixes = new HashMap<String, MapAffixData>();

			AffectedEntities affected = null;

			if (entity instanceof EntityPlayer) {
				affected = AffectedEntities.Players;
			} else {
				affected = AffectedEntities.Mobs;
			}

			for (MapAffixData affix : worlddata.getMap().getAllAffixesThatAffect(affected)) {
				unit.mapAffixes.put(affix.GUID, affix);
			}

			for (MapAffixData affix : worlddata.getMap().getAllAffixesThatAffect(AffectedEntities.All)) {
				unit.mapAffixes.put(affix.GUID, affix);
			}

		}
		return unit;
	}

	private static int GetMobLevel(IWorldData data, EntityLivingBase entity) {

		if (data != null && data.isMapWorld()) {
			return data.getLevel();
		} else {
			return GetMobLevelByDistanceFromSpawn(entity);
		}

	}

	private static void AnnounceWorldBossSpawn(EntityLivingBase entity, Unit unit) {

		for (EntityPlayer player : entity.world.playerEntities) {
			if (player.getDistance(entity) < 150) {

				player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_GROWL,
						SoundCategory.AMBIENT, 0.5F, 1);

			}

		}

	}

	public static int GetMobLevelByDistanceFromSpawn(Entity entity) {

		double distance = entity.world.getSpawnPoint().distanceSq(entity.posX, entity.posY, entity.posZ);

		int lvl = 1;

		if (distance < ModConfig.Server.MOB_LEVEL_ONE_AREA) {
			lvl = 1;
		} else {
			lvl = (int) (1 + (distance / ModConfig.Server.MOB_LEVEL_PER_DISTANCE));
		}

		return lvl;

	}

}
