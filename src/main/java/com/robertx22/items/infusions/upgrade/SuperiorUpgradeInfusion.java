package com.robertx22.items.infusions.upgrade;

import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class SuperiorUpgradeInfusion extends BaseUpgradeInfusion {

    public SuperiorUpgradeInfusion() {
	super(name);

    }

    private static final String name = "superior_upgrade_infusion";

    @GameRegistry.ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new SuperiorUpgradeInfusion());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

    @Override
    public int Tier() {
	return 9;
    }

    @Override
    public float critOnSuccessChance() {
	return 10F;
    }

    @Override
    public float bonusSuccessChance() {
	return 5F;
    }

    @Override
    public float majorFailureChance() {
	return 4F;
    }

    @Override
    public String GUID() {
	return name;
    }

    @Override
    public int Rank() {
	return 2;
    }

}
