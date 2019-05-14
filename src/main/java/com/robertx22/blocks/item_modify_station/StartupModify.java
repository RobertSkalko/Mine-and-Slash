package com.robertx22.blocks.item_modify_station;

import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.gui.GuiHandler;
import com.robertx22.mmorpg.gui.GuiHandlerRegistry;

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
public class StartupModify {
	public static Block blockInventoryAdvanced; // this holds the unique instance of your block
	public static ItemBlock itemBlockInventoryAdvanced; // this holds the unique instance of the ItemBlock corresponding
														// to your block

	public static void preInitCommon(FMLPreInitializationEvent event) {
		blockInventoryAdvanced = new BlockInventoryModify().setUnlocalizedName(Ref.MODID + ":modify_station");
		blockInventoryAdvanced.setRegistryName(Ref.MODID + ":modify_station");
		ForgeRegistries.BLOCKS.register(blockInventoryAdvanced);

		// We also need to create and register an ItemBlock for this block otherwise it
		// won't appear in the inventory
		itemBlockInventoryAdvanced = new ItemBlock(blockInventoryAdvanced);
		itemBlockInventoryAdvanced.setRegistryName(blockInventoryAdvanced.getRegistryName());
		ForgeRegistries.ITEMS.register(itemBlockInventoryAdvanced);

		// Each of your tile entities needs to be registered with a name that is unique
		// to your mod.
		GameRegistry.registerTileEntity(TileInventoryModify.class, Ref.MODID + ":modify_station_entity");

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
		ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(Ref.MODID + ":modify_station",
				"inventory");
		final int DEFAULT_ITEM_SUBTYPE = 0;
		ModelLoader.setCustomModelResourceLocation(itemBlockInventoryAdvanced, DEFAULT_ITEM_SUBTYPE,
				itemModelResourceLocation);
	}

}