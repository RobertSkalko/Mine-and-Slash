package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.spells.entities.bases.EntityStaffProjectile;
import com.robertx22.mine_and_slash.spells.entities.bases.IMyRenderAsItem;
import com.robertx22.mine_and_slash.spells.entities.bases.MySpriteRenderer;
import com.robertx22.mine_and_slash.spells.entities.bases.aoe.EntityAcidExplosion;
import com.robertx22.mine_and_slash.spells.entities.bases.aoe.EntityFlameExplosion;
import com.robertx22.mine_and_slash.spells.entities.bases.aoe.EntityFrostExplosion;
import com.robertx22.mine_and_slash.spells.entities.bases.aoe.EntityLightningExplosion;
import com.robertx22.mine_and_slash.spells.entities.bases.bomb.EntityAcidBomb;
import com.robertx22.mine_and_slash.spells.entities.bases.bomb.EntityFireBomb;
import com.robertx22.mine_and_slash.spells.entities.bases.bomb.EntityIceBomb;
import com.robertx22.mine_and_slash.spells.entities.bases.bomb.EntityThunderBomb;
import com.robertx22.mine_and_slash.spells.entities.bases.proj.EntityAcidBolt;
import com.robertx22.mine_and_slash.spells.entities.bases.proj.EntityFireBolt;
import com.robertx22.mine_and_slash.spells.entities.bases.proj.EntityFrostBolt;
import com.robertx22.mine_and_slash.spells.entities.bases.proj.EntityThunderBolt;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderRegister {

    //@SubscribeEvent
    public static void regRenders(/*ModelRegistryEvent evt*/) {

        RenderingRegistry.registerEntityRenderingHandler(EntityFireBolt.class, newRenFac());
        RenderingRegistry.registerEntityRenderingHandler(EntityFireBomb.class, newRenFac());
        RenderingRegistry.registerEntityRenderingHandler(EntityFlameExplosion.class, newRenFac());

        RenderingRegistry.registerEntityRenderingHandler(EntityFrostBolt.class, newRenFac());
        RenderingRegistry.registerEntityRenderingHandler(EntityIceBomb.class, newRenFac());
        RenderingRegistry.registerEntityRenderingHandler(EntityFrostExplosion.class, newRenFac());

        RenderingRegistry.registerEntityRenderingHandler(EntityThunderBolt.class, newRenFac());
        RenderingRegistry.registerEntityRenderingHandler(EntityLightningExplosion.class, newRenFac());
        RenderingRegistry.registerEntityRenderingHandler(EntityThunderBomb.class, newRenFac());

        RenderingRegistry.registerEntityRenderingHandler(EntityAcidBolt.class, newRenFac());
        RenderingRegistry.registerEntityRenderingHandler(EntityAcidExplosion.class, newRenFac());
        RenderingRegistry.registerEntityRenderingHandler(EntityAcidBomb.class, newRenFac());

        RenderingRegistry.registerEntityRenderingHandler(EntityStaffProjectile.class, newRenFac());

    }

    private static <T extends Entity & IMyRenderAsItem> IRenderFactory<T> newRenFac() {
        return manager -> new MySpriteRenderer<>(manager, Minecraft.getInstance()
                .getItemRenderer());
    }
}
