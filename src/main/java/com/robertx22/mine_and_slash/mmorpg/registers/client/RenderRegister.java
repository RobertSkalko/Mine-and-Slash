package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.database.spells.entities.bases.MySpriteRenderer;
import com.robertx22.mine_and_slash.database.spells.entities.special.RangerArrowRenderer;
import com.robertx22.mine_and_slash.database.spells.entities.summons.SpiderPetRenderer;
import com.robertx22.mine_and_slash.database.spells.entities.summons.WolfPetRenderer;
import com.robertx22.mine_and_slash.database.spells.entities.trident.HolyTridentRenderer;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mobs.renderer.ModZombieRenderer;
import com.robertx22.mine_and_slash.new_content.trader.TraderRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
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

        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.HOLY_SPEAR, HolyTridentRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.THUNDER_SPEAR, TridentRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.RANGER_ARROW, RangerArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.TRADER, TraderRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.FAST_BABY_ZOMBIE, (EntityRendererManager manager) -> new ModZombieRenderer(manager, "fast_baby_zombie"));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.THUNDER_ZOMBIE_BOSS, (EntityRendererManager manager) -> new ModZombieRenderer(manager, "boss_thunder_zombie").scaleBy(1.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.SPIDER_PET, SpiderPetRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegister.SPIRIT_WOLF_PET, WolfPetRenderer::new);

    }

    private static <T extends Entity> IRenderFactory<? super T> newRenFac() {
        return manager -> new MySpriteRenderer<>(manager, Minecraft.getInstance()
            .getItemRenderer());
    }
}
