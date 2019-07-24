package com.robertx22.mine_and_slash.uncommon.gui.player_overlays;

import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class MiddleOverlay extends BasePlayerOverlay {

    @Override
    public void Draw(AbstractGui gui, Minecraft mc, LivingEntity entity,
                     RenderGameOverlayEvent event, Unit unit, UnitData data) {
        // ENERGY

        int height = mc.mainWindow.getScaledHeight();
        int width = mc.mainWindow.getScaledWidth();

        int x = width / 2 + 5;
        int y = height - 53;

        this.DrawBar(mc, gui, unit, energytexturepath, data.getCurrentEnergy(), unit.energyData().Value, false, data, x, y);
        // ENERGY

        // MANA
        x = width / 2 + 5;
        y = height - 65;
        this.DrawBar(mc, gui, unit, manatexturepath, data.getCurrentMana(), unit.manaData().Value, false, data, x, y);
        // MANA

        // HEALTH
        x = width / 2 - this.TEXTURE_WIDTH;
        y = height - 65;

        this.DrawBar(mc, gui, unit, healthtexturepath, unit.health()
                .CurrentValue(entity, unit), unit.healthData().Value, false, data, x, y);
        // HEALTH

        // EXP

        x = width / 2 - this.TEXTURE_WIDTH;
        y = height - 53;
        this.DrawBar(mc, gui, unit, experiencetexturepath, data.getExp(), data.GetExpRequiredForLevelUp(), true, data, x, y);
        // EXP
    }
}