package com.robertx22.customitems.infusions;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stat_mods.flat.MajorArmorFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.mmorpg.Ref;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class DefenseInfusionItem extends BaseInfusionItem {

    public DefenseInfusionItem() {
	super(name);

    }

    private static final String name = "defense_infusion";

    @GameRegistry.ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new DefenseInfusionItem());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

    @Override
    public List<StatMod> weaponInfusions() {
	return Arrays.asList(new DodgeFlat(), new MajorArmorFlat());
    }

    @Override
    public List<StatMod> armorInfusions() {
	return Arrays.asList(new ArmorFlat(), new FireResistFlat(), new WaterResistFlat(), new ThunderResistFlat(),
		new NatureResistFlat(), new DodgeFlat());
    }

    @Override
    public List<StatMod> jewerlyInfusions() {
	return armorInfusions();
    }

    @Override
    public String GUID() {
	return name;
    }

}
