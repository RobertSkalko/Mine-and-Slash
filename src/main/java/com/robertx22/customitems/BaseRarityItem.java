package com.robertx22.customitems;

import com.robertx22.database.lists.Rarities;
import com.robertx22.gearitem.ItemRarity;
import com.robertx22.utilityclasses.RegisterUtils;

import baubles.api.IBauble;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@EventBusSubscriber
public abstract class BaseRarityItem  extends Item {
	
	public abstract String Name();	
	
	public abstract Item GetRarity(ItemRarity rarity);
		
	
	public BaseRarityItem(String rarity) {
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setCreativeTab(CreativeTabs.TOOLS);
		this.setUnlocalizedName(Name());
		this.setRegistryName(Name().toLowerCase() + "_" + rarity.toLowerCase());
		
		System.out.println("Created item: " + this.getRegistryName());
		
		RegisterUtils.Add(this);
	}	


}
