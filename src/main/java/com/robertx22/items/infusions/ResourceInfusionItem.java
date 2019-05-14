package com.robertx22.items.infusions;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stat_mods.flat.resources.LifeOnHitFlat;
import com.robertx22.database.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaOnHitFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class ResourceInfusionItem extends BaseInfusionItem {

    public ResourceInfusionItem() {
	super(name);

    }

    private static final String name = "resource_infusion";

    @GameRegistry.ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ResourceInfusionItem());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

    @Override
    public List<StatMod> weaponInfusions() {
	return Arrays.asList(new ManaOnHitFlat(), new LifeOnHitFlat(), new LifestealFlat());
    }

    @Override
    public List<StatMod> armorInfusions() {
	return Arrays.asList(new ManaRegenFlat(), new EnergyRegenFlat(), new HealthRegenFlat());
    }

    @Override
    public List<StatMod> jewerlyInfusions() {
	return Arrays.asList(new ManaFlat(), new HealthRegenFlat());
    }

    @Override
    public String GUID() {
	return name;
    }

}
