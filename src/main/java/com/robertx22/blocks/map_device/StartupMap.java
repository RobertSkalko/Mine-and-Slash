package com.robertx22.blocks.map_device;

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

public class StartupMap {
    public static Block blockMap; // this holds the unique instance of your block
    public static ItemBlock itemBlockMap; // this holds the unique instance of the ItemBlock corresponding
					  // to your block

    public static void preInitCommon(FMLPreInitializationEvent event) {
	blockMap = new BlockMap().setTranslationKey(Ref.MODID + ":map_device");
	blockMap.setRegistryName(Ref.MODID + ":map_device");
	ForgeRegistries.BLOCKS.register(blockMap);

	itemBlockMap = new ItemBlock(blockMap);
	itemBlockMap.setRegistryName(blockMap.getRegistryName());
	ForgeRegistries.ITEMS.register(itemBlockMap);
	GameRegistry.registerTileEntity(TileMap.class, Ref.MODID + ":map_device_entity");

	NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, GuiHandlerRegistry.getInstance());
	GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandler(), GuiHandler.getGuiID());

	if (event.getSide().equals(Side.CLIENT)) {
	    preInitClientOnly();
	}
    }

    @SideOnly(Side.CLIENT)
    public static void preInitClientOnly() {
	ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(Ref.MODID + ":map_device",
		"inventory");
	final int DEFAULT_ITEM_SUBTYPE = 0;
	ModelLoader.setCustomModelResourceLocation(itemBlockMap, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
    }

}