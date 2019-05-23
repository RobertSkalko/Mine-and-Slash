package com.robertx22.items.gearitems.offhands;

import java.util.HashMap;
import javax.annotation.Nullable;
import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class NormalShield extends ItemShield {

  public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

  public static final String ID = "shields/normal_shield";
  @GameRegistry.ObjectHolder(Ref.MODID + ":" + ID)
  public static final Item ITEM = null;

  ResourceLocation resource = new ResourceLocation("");

  public NormalShield(String name) {
    super();
    this.setMaxDamage(750);
    resource = new ResourceLocation("mmorpg:textures/shield/" + name + ".png");
    Main.proxy.setShieldRenderer(this);
  }

  @Override
  public boolean isShield(ItemStack stack, @Nullable EntityLivingBase entity) {
    return true;
  }


  @SubscribeEvent
  public static void onModelRegistry(ModelRegistryEvent event) {
    for (Item item : Items.values()) {
      registerModel(item);
    }
  }

  public static void registerModel(Item item) {
    registerModel(item, item.getRegistryName().getNamespace());
  }

  public static void registerModel(Item item, String modelName) {
    ModelLoader.setCustomModelResourceLocation(item, 0,
        new ModelResourceLocation(Ref.MODID + ":" + modelName, "inventory"));
  }



}
