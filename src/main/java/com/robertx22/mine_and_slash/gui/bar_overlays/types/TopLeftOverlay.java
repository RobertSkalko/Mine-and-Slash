package com.robertx22.mine_and_slash.gui.bar_overlays.types;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class TopLeftOverlay extends BasePlayerOverlay {
    int xPos = 2;
    int yPos = 2;

    @Override
    public void Draw(AbstractGui gui, Minecraft mc, LivingEntity entity, RenderGameOverlayEvent event, UnitData data) {

        Unit unit = data.getUnit();

        yPos = 2;

        float scale = 1F;

        RenderSystem.scalef(scale, scale, scale);

        DrawBar(
                mc, gui, healthtexturepath, unit.health().CurrentValue(mc.player, unit), unit.healthData().val, Type.HP,
                data, xPos, yPos
        );
        yPos += 12;

        DrawBar(mc, gui, manatexturepath, data.getCurrentMana(), unit.manaData().val, Type.MANA, data, xPos, yPos);
        yPos += 12;
        DrawBar(mc, gui, energytexturepath, data.getCurrentEnergy(), unit.energyData().val, Type.ENE, data, xPos, yPos);
        yPos += 12;
        DrawBar(
                mc, gui, experiencetexturepath, data.getExp(), data.GetExpRequiredForLevelUp(), Type.EXP, data, xPos,
                yPos
        );

        scale = 1 / scale;
        RenderSystem.scalef(scale, scale, scale);

    }

}
