package com.robertx22.mine_and_slash.onevent.ontick;

import com.robertx22.mine_and_slash.uncommon.capability.BossCap;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.script.ScriptEngineManager;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class OnClientTickRenderBoss {

    private static long lastRender = 0L;

    @SubscribeEvent
    public static void onRenderWorldLast(RenderWorldLastEvent event) {

        if (Minecraft.getInstance().world == null) {
            return;
        }
        ScriptEngineManager mgr = new ScriptEngineManager();

        // String foo = "100 * (1 + 100 / 100)";

        String foo = "100 * Math.pow(100,0.5 + 100 / 150)";

        //  System.out.println(engine.eval(foo) + " vs " + StatScaling.NORMAL.scale(100, 100));

        if (Minecraft.getInstance().player.ticksExisted % 3 == 0) {

            Minecraft.getInstance().world.getAllEntities().forEach(x -> {
                x.getCapability(BossCap.Data).ifPresent(c -> {
                    c.spawnParticle((LivingEntity) x);
                });
            });
        }
    }

}
