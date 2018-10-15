package com.robertx22.onevent.combat;

import com.robertx22.database.stats.types.Health;
import com.robertx22.mmorpg.ModConfig;
import com.robertx22.saveclasses.Unit;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnPlayerAttack {

	@SubscribeEvent
	public static void onPlayerAttack(LivingDamageEvent event) {

		if (event.isCanceled()) {
			return;
		}
		if (event.getSource() == null) {
			return;
		}
		if (!(event.getSource().getTrueSource() instanceof EntityLivingBase)) {
			return;
		}
		if (event.getEntityLiving().world.isRemote) {
			return;
		}

		if (ModConfig.Cheats.GOD_MODE) {

			event.setAmount(999999);
			return;

		}

		EntityLivingBase player = (EntityLivingBase) event.getSource().getTrueSource();

		Unit unit = new Unit(player);

		unit.RecalculateStats();

		System.out.println("player hp is :" + unit.Stats.get(Health.class).GetValue(unit));

		/*
		 * 
		 * GearItem geartest = new GearItem(); geartest.name = "name"; geartest.prefix =
		 * new AffixData(Flaming.class, new ArrayList<Integer>(),
		 * SuffixOrPrefix.Prefix);
		 * 
		 * 
		 * 
		 * ItemStack item = player.getHeldItemMainhand(); StopWatch watch = new
		 * StopWatch(); watch.start();
		 * 
		 * for (int i = 0; i < 1; i++) {
		 * 
		 * Saving.Save(item.getTagCompound(), geartest);
		 * item.setTagCompound(Saving.Save(item.getTagCompound(), geartest)); geartest =
		 * Saving.Load(item.getTagCompound(), GearItem.class);
		 * 
		 * } watch.stop(); System.out .println(watch.getTime() +
		 * " miliseconds for full read and write " + item.getTagCompound().toString());
		 * 
		 */

		/*
		 * if (event.getEntityLiving() instanceof EntityMob) {
		 * 
		 * Hashtable<String, Integer> stats = PlayerData.getStats(player);
		 * 
		 * int minDMG = stats.get(Stats.MIN_DAMAGE.name); int maxDMG =
		 * stats.get(Stats.MAX_DAMAGE.name);
		 * 
		 * int attackPower = stats.get(Stats.ATTACK_POWER.name); int magicPower =
		 * stats.get(Stats.MAGIC_POWER.name); int healPower =
		 * stats.get(Stats.HEAL_POWER.name);
		 * 
		 * int DMG = minDMG + ran.nextInt(maxDMG - minDMG);
		 * 
		 * int critChance = stats.get(Stats.CRIT_CHANCE.name); int critDamage =
		 * stats.get(Stats.CRIT_DAMAGE.name);
		 * 
		 * int lifesteal = stats.get(Stats.LIFESTEAL.name);
		 * 
		 * int fireDMG = stats.get(Stats.FIRE_DMG.name); int iceDMG =
		 * stats.get(Stats.ICE_DMG.name); int stormDMG =
		 * stats.get(Stats.STORM_DMG.name); int darkDMG =
		 * stats.get(Stats.DARK_DMG.name); int lightDMG =
		 * stats.get(Stats.LIGHT_DMG.name);
		 * 
		 * int bonusFireDMG = stats.get(Stats.BONUS_FIRE_DMG.name); int bonusIceDMG =
		 * stats.get(Stats.BONUS_ICE_DMG.name); int bonusStormDMG =
		 * stats.get(Stats.BONUS_STORM_DMG.name); int bonusDarkDMG =
		 * stats.get(Stats.BONUS_DARK_DMG.name); int bonusLightDMG =
		 * stats.get(Stats.BONUS_LIGHT_DMG.name);
		 * 
		 * int fireMULTI = stats.get(Stats.FIRE_DMG_MULTI.name); int iceMULTI =
		 * stats.get(Stats.ICE_DMG_MULTI.name); int stormMULTI =
		 * stats.get(Stats.STORM_DMG_MULTI.name); int darkMULTI =
		 * stats.get(Stats.DARK_DMG_MULTI.name); int lightMULTI =
		 * stats.get(Stats.LIGHT_DMG_MULTI.name);
		 * 
		 * // ELEMENTAL DMG
		 * 
		 * fireDMG += fireDMG * bonusFireDMG / 100; fireDMG += fireDMG * (float)
		 * (fireMULTI / 100);
		 * 
		 * iceDMG += iceDMG * bonusIceDMG / 100; iceDMG += iceDMG * (float) (iceMULTI /
		 * 100);
		 * 
		 * stormDMG += stormDMG * bonusStormDMG / 100; stormDMG += stormDMG * (float)
		 * (stormMULTI / 100);
		 * 
		 * darkDMG += darkDMG * bonusDarkDMG / 100; darkDMG += darkDMG * (float)
		 * (darkMULTI / 100);
		 * 
		 * lightDMG += lightDMG * bonusLightDMG / 100; lightDMG += lightDMG * (float)
		 * (lightMULTI / 100);
		 * 
		 * int elementalDMG = fireDMG + stormDMG + iceDMG + darkDMG + lightDMG;
		 * 
		 * //elementalDMG += magicPower * elementalDMG / 100;
		 * 
		 * // attack power doesn't increase elemental dmg DMG = DMG + DMG * attackPower
		 * / 100;
		 * 
		 * int combinedDMG = DMG + elementalDMG;
		 * 
		 * // CRIT if (RandomUtils.roll(critChance)) { combinedDMG += combinedDMG *
		 * critDamage / 100; } // CRIT
		 * 
		 * float mobHPMulti = GeneralUtils.getHpMultiplier(mob.getMaxHealth(),
		 * mob.getCapability(EntityData.Data,
		 * null).getNBT().getInteger(Stats.HEALTH.name));
		 * 
		 * // LIFESTEAL
		 * 
		 * float playerHPMulti = GeneralUtils.getHpMultiplier(player.getMaxHealth(),
		 * PlayerData.getStats(player).get(Stats.HEALTH.name));
		 * 
		 * float lifeStolen = (float) (combinedDMG * lifesteal / 100) * playerHPMulti;
		 * 
		 * lifeStolen += lifeStolen * healPower / 100;
		 * 
		 * player.setHealth(player.getHealth() + lifeStolen);
		 * 
		 * //TESTING PURPOSES //event.setAmount(555555); event.setAmount(combinedDMG *
		 * mobHPMulti);
		 * 
		 * } else { // if its not a mob
		 * event.setAmount(event.getEntityLiving().getMaxHealth() / 5); }
		 */

	}

}
