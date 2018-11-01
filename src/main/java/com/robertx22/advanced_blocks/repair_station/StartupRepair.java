package com.robertx22.advanced_blocks.repair_station;

import com.robertx22.mmorpg.GuiHandler;
import com.robertx22.mmorpg.GuiHandlerRegistry;
import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * User: brandon3055 Date: 06/01/2015
 *
 * The Startup class for this example is called during startup, in the following
 * order: preInitCommon preInitClientOnly initCommon initClientOnly
 * postInitCommon postInitClientOnly See MinecraftByExample class for more
 * information
 */
public class StartupRepair {
	public static Block blockInventoryAdvanced; // this holds the unique instance of your block
	public static ItemBlock itemBlockInventoryAdvanced; // this holds the unique instance of the ItemBlock corresponding
														// to your block

	public static void preInitCommon(FMLPreInitializationEvent event) {
		// each instance of your block should have two names:
		// 1) a registry name that is used to uniquely identify this block. Should be
		// unique within your mod. use lower case.
		// 2) an 'unlocalised name' that is used to retrieve the text name of your block
		// in the player's language. For example-
		// the unlocalised name might be "water", which is printed on the user's screen
		// as "Wasser" in German or
		// "aqua" in Italian.
		//
		// Multiple blocks can have the same unlocalised name - for example
		// +----RegistryName----+---UnlocalisedName----+
		// | flowing_water + water |
		// | stationary_water + water |
		// +--------------------+----------------------+
		//
		blockInventoryAdvanced = new BlockInventoryRepair().setUnlocalizedName("Repair Station");
		blockInventoryAdvanced.setRegistryName(Ref.MODID + ":repair_station");
		ForgeRegistries.BLOCKS.register(blockInventoryAdvanced);

		// We also need to create and register an ItemBlock for this block otherwise it
		// won't appear in the inventory
		itemBlockInventoryAdvanced = new ItemBlock(blockInventoryAdvanced);
		itemBlockInventoryAdvanced.setRegistryName(blockInventoryAdvanced.getRegistryName());
		ForgeRegistries.ITEMS.register(itemBlockInventoryAdvanced);

		// Each of your tile entities needs to be registered with a name that is unique
		// to your mod.
		GameRegistry.registerTileEntity(TileInventoryRepair.class, Ref.MODID + ":repair_station_entity");

		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, GuiHandlerRegistry.getInstance());
		GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandler(), GuiHandler.getGuiID());

		if (event.getSide().equals(Side.CLIENT)) {
			preInitClientOnly();
		}
	}

	@SideOnly(Side.CLIENT)
	public static void preInitClientOnly() {
		// This step is necessary in order to make your block render properly when it is
		// an item (i.e. in the inventory
		// or in your hand or thrown on the ground).
		// It must be done on client only, and must be done after the block has been
		// created in Common.preinit().
		ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(Ref.MODID + ":repair_station",
				"inventory");
		final int DEFAULT_ITEM_SUBTYPE = 0;
		ModelLoader.setCustomModelResourceLocation(StartupRepair.itemBlockInventoryAdvanced, DEFAULT_ITEM_SUBTYPE,
				itemModelResourceLocation);
	}

}