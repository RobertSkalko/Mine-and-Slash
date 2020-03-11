package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.database.spells.entities.bases.MySpriteRenderer;
import com.robertx22.mine_and_slash.database.spells.entities.magic_missiles.MagicMissileRenderer;
import com.robertx22.mine_and_slash.database.spells.entities.special.RangerArrowRenderer;
import com.robertx22.mine_and_slash.gui.trader.TraderRenderer;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.TridentRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderRegister {

    //@SubscribeEvent
    public static void regRenders(/*ModelRegistryEvent evt*/) {

        for (EntityType type : EntityRegister.ENTITY_THAT_USE_ITEM_RENDERS) {
            RenderingRegistry.registerEntityRenderingHandler(type, newRenFac());
        }

        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.THUNDER_SPEAR, TridentRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.MAGIC_MISSILE, MagicMissileRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.RANGER_ARROW, RangerArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.TRADER, TraderRenderer::new);

    }

    private static <T extends Entity> IRenderFactory<? super T> newRenFac() {
        return manager -> new MySpriteRenderer<>(manager, Minecraft.getInstance()
            .getItemRenderer());
    }
}
