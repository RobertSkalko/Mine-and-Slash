package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.onevent.TestLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.Entity;

import java.util.Map;

public class RegisterModelLayers {

    public static void reg() {
        Map<Class<? extends Entity>, EntityRenderer<? extends Entity>> renders = Minecraft
                .getInstance()
                .getRenderManager().
                renderers;

        for (EntityRenderer<? extends Entity> render : renders.values()) {
            if (render instanceof LivingRenderer) {
                LivingRenderer living = (LivingRenderer) render;

                living.addLayer(new TestLayer(living));
            }
        }
    }
}
