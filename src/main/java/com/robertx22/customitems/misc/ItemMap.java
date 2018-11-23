package com.robertx22.customitems.misc;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.customitems.gearitems.bases.BaseRarityItem;
import com.robertx22.database.lists.Rarities;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.dimensions.blocks.MyPortalBlock;
import com.robertx22.dimensions.blocks.TilePortalBlock;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.datasaving.Map;
import com.robertx22.uncommon.enumclasses.AffectedEntities;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
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

			addAffixTypeToTooltip(data, tooltip, AffectedEntities.Mobs);
			addAffixTypeToTooltip(data, tooltip, AffectedEntities.Players);
			addAffixTypeToTooltip(data, tooltip, AffectedEntities.All);

			tooltip.add("");

			tooltip.add(TextFormatting.BLUE + "World Type: " + data.worldGeneratorName);

			tooltip.add("");
			tooltip.add(rarity.Color() + "Rarity: " + rarity.Name());
		}
	}

	private void addAffixTypeToTooltip(MapItemData data, List<String> tooltip, AffectedEntities affected) {

		List<MapAffixData> affixes = data.getAllAffixesThatAffect(affected);

		if (affixes.size() == 0) {
			return;
		}

		tooltip.add(TextFormatting.GREEN + affected.name() + " Affixes::");

		for (MapAffixData affix : affixes) {

			tooltip.add(" - " + (TextFormatting.YELLOW + affix.getAffix().Name()));

			for (StatModData statmod : affix.getAffix().Stats(affix.percent)) {

				String statstring = statmod.GetTooltipString(Rarities.Maps.get(data.rarity).StatPercents(), data.level,
						false);

				tooltip.add(" * " + TextFormatting.RED + statstring);

			}

		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {

		if (!worldIn.isRemote) {
			try {

				MapItemData data = Map.Load(player.getHeldItem(hand));

				if (data != null) {

					int id = data.createDimension(player);

					BlockPos pos = player.getPosition();
					pos = pos.north(3);

					// portla to new dim
					player.world.setBlockState(pos, new MyPortalBlock().getDefaultState(), 2);
					TilePortalBlock portal = new TilePortalBlock(id);
					player.world.setTileEntity(pos, portal);

					return new ActionResult<ItemStack>(EnumActionResult.PASS, ItemStack.EMPTY);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
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