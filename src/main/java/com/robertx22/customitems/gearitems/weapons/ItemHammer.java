package com.robertx22.customitems.gearitems.weapons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.gearitems.bases.BaseSwordItem;
import com.robertx22.customitems.gearitems.bases.IWeapon;
import com.robertx22.database.stats.types.offense.PhysicalDamage;
import com.robertx22.db_lists.Rarities;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData.EffectTypes;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ItemHammer extends BaseSwordItem implements IWeapon {
	public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

	public ItemHammer(int rarity, HashMap<Integer, Item> map) {
		super(rarity, map);

	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		Rarities.Items.forEach((x) -> Items.put(x.Rank(), new ItemHammer(x.Rank(), Items)));
		Items.values().forEach((x) -> event.getRegistry().register(x));
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		Items.values().forEach((x) -> RegisterUtils.registerRender(x));
	}

	@Override
	public String Name() {
		return "Hammer";
	}

	@Override
	public int GetEnergyCost() {
		return 10;
	}

	float radius = 1.5F;

	@Override
	public boolean Attack(EntityLivingBase source, EntityLivingBase target, UnitData unitsource, UnitData targetUnit) {

		List<EntityLivingBase> entities = new ArrayList<EntityLivingBase>();

		for (Entity en : target.world.getEntitiesWithinAABBExcludingEntity(source,
				new AxisAlignedBB(target.posX - radius, target.posY - radius, target.posZ - radius,
						target.posX + radius, target.posY + radius, target.posZ + radius))) {
			if (en instanceof EntityLivingBase) {
				entities.add((EntityLivingBase) en);
			}
		}

		for (EntityLivingBase entity : entities) {
			int num = (int) unitsource.getUnit().MyStats.get(PhysicalDamage.GUID).Value;
			DamageEffect dmg = new DamageEffect(source, entity, num, unitsource, targetUnit);
			dmg.Type = EffectTypes.BASIC_ATTACK;
			dmg.Activate();
		}

		return true;
	}

}
