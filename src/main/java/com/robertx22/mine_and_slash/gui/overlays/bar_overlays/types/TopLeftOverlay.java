package com.robertx22.mine_and_slash.gui.overlays.bar_overlays.types;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.gui.overlays.bar_overlays.bases.BaseBarsOverlay;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class TopLeftOverlay extends BaseBarsOverlay {
    int xPos = 2;
    int yPos = 2;

    @Override
    public void Draw(AbstractGui gui, Minecraft mc, LivingEntity entity, RenderGameOverlayEvent event, UnitData data) {

        yPos = 2;

        float scale = 1F;

        RenderSystem.scalef(scale, scale, scale);

        DrawBar(BarType.HP, data, xPos, yPos);
        yPos += 12;

        DrawBar(BarType.MANA, data, xPos, yPos);
        yPos += 12;
        DrawBar(BarType.ENE, data, xPos, yPos);
        yPos += 12;
        DrawBar(BarType.EXP, data, xPos, yPos);

        scale = 1 / scale;
        RenderSystem.scalef(scale, scale, scale);

    }

}
