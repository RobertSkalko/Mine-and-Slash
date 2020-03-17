package com.robertx22.mine_and_slash.gui.overlays.bar_overlays.types;

import com.robertx22.mine_and_slash.gui.overlays.bar_overlays.bases.BaseBarsOverlay;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class BottomMiddleOverlay extends BaseBarsOverlay {

    @Override
    public void Draw(AbstractGui gui, Minecraft mc, LivingEntity entity, RenderGameOverlayEvent event, UnitData data) {
        Unit unit = data.getUnit();

        int offY = 12;
        int offY2 = 45;

        int height = mc.mainWindow.getScaledHeight();
        int width = mc.mainWindow.getScaledWidth();

        // ENERGY
        int x = width / 2 - this.BAR_WIDTH * 2;
        int y = height - offY;

        this.DrawBar(BarType.ENE, data, x, y);

        // ENERGY

        // MANA
        x = width / 2 + this.BAR_WIDTH;
        y = height - offY;
        this.DrawBar(BarType.MANA, data, x, y);
        // MANA

        // HEALTHs
        x = width / 2 - this.BAR_WIDTH;
        y = height - offY2;

        this.DrawBar(BarType.HP, data, x, y);
        // HEALTH

        // EXP
        x = width / 2 + 5;
        y = height - offY2;

        this.DrawBar(BarType.EXP, data, x, y);
        // EXP

    }

}
