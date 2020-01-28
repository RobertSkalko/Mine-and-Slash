package com.robertx22.mine_and_slash.gui.bar_overlays.types;

import com.robertx22.mine_and_slash.gui.bar_overlays.bases.BaseBarsOverlay;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class MiddleOverlay extends BaseBarsOverlay {

    @Override
    public void Draw(AbstractGui gui, Minecraft mc, LivingEntity entity, RenderGameOverlayEvent event, UnitData data) {
        // ENERGY

        Unit unit = data.getUnit();

        int height = mc.mainWindow.getScaledHeight();
        int width = mc.mainWindow.getScaledWidth();

        int x = width / 2 + 5;
        int y = height - 53;

        this.DrawBar(BarType.ENE, data, x, y);
        // ENERGY

        // MANA
        x = width / 2 + 5;
        y = height - 65;
        this.DrawBar(BarType.MANA, data, x, y);
        // MANA

        // HEALTH
        x = width / 2 - this.BAR_WIDTH;
        y = height - 65;

        this.DrawBar(BarType.HP, data, x, y);
        // HEALTH

        // EXP

        x = width / 2 - this.BAR_WIDTH;
        y = height - 53;
        this.DrawBar(BarType.EXP, data, x, y);
        // EXP
    }
}