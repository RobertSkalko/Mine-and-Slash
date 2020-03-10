package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.items.misc.IdentifyTomeItem;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RecipeRegisters {

    public static IRecipeSerializer<?> IDENTIFY;

    @SubscribeEvent
    public static void reg(final RegistryEvent.Register<IRecipeSerializer<?>> event) {

        IDENTIFY = new SpecialRecipeSerializer<>(IdentifyTomeItem.Recipe::new)
            .setRegistryName(Ref.MODID, "identify_tome_recipe");

        event.getRegistry()
            .register(IDENTIFY);

    }

}
