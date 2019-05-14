package com.robertx22.blocks.gear_factory_station;

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

public class StartupGearFactory {
    public static Block blockInventoryAdvanced;
    public static ItemBlock itemBlockInventoryAdvanced;

    public static void preInitCommon(FMLPreInitializationEvent event) {
	blockInventoryAdvanced = new BlockGearFactory().setUnlocalizedName(Ref.MODID + ":gear_factory_station");
	blockInventoryAdvanced.setRegistryName(Ref.MODID + ":gear_factory_station");
	ForgeRegistries.BLOCKS.register(blockInventoryAdvanced);

	itemBlockInventoryAdvanced = new ItemBlock(blockInventoryAdvanced);
	itemBlockInventoryAdvanced.setRegistryName(blockInventoryAdvanced.getRegistryName());
	ForgeRegistries.ITEMS.register(itemBlockInventoryAdvanced);

	// GameRegistry.registerTileEntity(TileGearFactory.class,
	// new ResourceLocation(Ref.MODID, "gear_factory_station_entity"));

	GameRegistry.registerTileEntity(TileGearFactory.class, Ref.MODID + ":gear_factory_station_entity");

	NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, GuiHandlerRegistry.getInstance());
	GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandler(), GuiHandler.getGuiID());

	if (event.getSide().equals(Side.CLIENT)) {
	    preInitClientOnly();
	}
    }

    @SideOnly(Side.CLIENT)
    public static void preInitClientOnly() {
	ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(Ref.MODID + ":gear_factory_station",
		"inventory");
	final int DEFAULT_ITEM_SUBTYPE = 0;
	ModelLoader.setCustomModelResourceLocation(itemBlockInventoryAdvanced, DEFAULT_ITEM_SUBTYPE,
		itemModelResourceLocation);
    }

}