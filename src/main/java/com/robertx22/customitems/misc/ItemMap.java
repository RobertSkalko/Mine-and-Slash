package com.robertx22.customitems.misc;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.customitems.gearitems.bases.BaseRarityItem;
import com.robertx22.database.lists.Rarities;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.datasaving.Map;
import com.robertx22.uncommon.enumclasses.AffectedEntities;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class ItemMap extends BaseRarityItem {
	public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

		MapItemData data = Map.Load(stack);

		if (data != null) {

			ItemRarity rarity = Rarities.Items.get(data.rarity);

			tooltip.clear();

			tooltip.add(Rarities.Items.get(data.rarity).Color() + data.name);
			tooltip.add(TextFormatting.YELLOW + "Level: " + data.level);
			tooltip.add("");

			tooltip.add(TextFormatting.GREEN + "Mob Affixes::");

			for (MapAffixData affix : data.getAllAffixesThatAffect(AffectedEntities.Mobs)) {
				
				tooltip.add(" * "
						+ (TextFormatting.YELLOW + affix.affectedEntities.toString() + " " + affix.getAffix().Name()));
				
				for (StatModData statmod : affix.getAffix().Stats(affix.percent)) {
					
					tooltip.add(" * "
							+ (TextFormatting.RED + statmod.GetTooltipString(data.level, false));
				
				}
				
			}

			tooltip.add("");

			tooltip.add(rarity.Color() + "Rarity: " + rarity.Name());
		}
	}

	public ItemMap(int i, HashMap<Integer, Item> map) {
		super(i, map);

	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		Rarities.Items.forEach((x) -> Items.put(x.Rank(), new ItemMap(x.Rank(), Items)));
		Items.values().forEach((x) -> event.getRegistry().register(x));
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		Items.values().forEach((x) -> RegisterUtils.registerRender(x));
	}

	@Override
	public String Name() {
		return "Map";
	}
}