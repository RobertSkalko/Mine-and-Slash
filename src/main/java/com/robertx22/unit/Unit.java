package com.robertx22.unit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.*;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;

public class Unit {

	public EntityLiving Entityliving;
	
	public Unit(EntityLiving entity) {
		this.Entityliving = entity;
	}
	
	
	
	
	public List<ItemStack> GetEquips(){
		
		List<ItemStack> list = new ArrayList<ItemStack>();
		
		list.addAll((Collection<? extends ItemStack>) Entityliving.getArmorInventoryList());
		list.add(Entityliving.getHeldItemMainhand());
		
		return  list;
		
	}
	
}
