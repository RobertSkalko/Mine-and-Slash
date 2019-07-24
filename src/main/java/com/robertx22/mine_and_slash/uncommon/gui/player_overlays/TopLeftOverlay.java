package com.robertx22.mine_and_slash.uncommon.gui.player_overlays;

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
    public void Draw(AbstractGui gui, Minecraft mc, LivingEntity entity,
                     RenderGameOverlayEvent event, Unit unit, UnitData data) {
        yPos = 2;

        DrawBar(mc, gui, unit, healthtexturepath, unit.health()
                .CurrentValue(mc.player, unit), unit.healthData().Value, false, data, xPos, yPos);
        yPos += 12;

        DrawBar(mc, gui, unit, manatexturepath, data.getCurrentMana(), unit.manaData().Value, false, data, xPos, yPos);
        yPos += 12;
        DrawBar(mc, gui, unit, energytexturepath, data.getCurrentEnergy(), unit.energyData().Value, false, data, xPos, yPos);
        yPos += 12;
        DrawBar(mc, gui, unit, experiencetexturepath, data.getExp(), data.GetExpRequiredForLevelUp(), true, data, xPos, yPos);

    }

}
