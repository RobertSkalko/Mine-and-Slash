package com.robertx22.mine_and_slash.uncommon.gui.player_overlays;

import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class BottomMiddleCornersOverlay extends BasePlayerOverlay {

    private static final int X_OFFSET_OFFHAND = -20;
    private static final int X_OFFSET_NO_OFFHAND = 5;

    @Override
    public void Draw(AbstractGui gui, Minecraft mc, LivingEntity entity,
                     RenderGameOverlayEvent event, Unit unit, UnitData data) {
        // ENERGY

        int offsetx = entity.getHeldItemOffhand()
                .isEmpty() ? X_OFFSET_NO_OFFHAND : X_OFFSET_OFFHAND;

        int height = mc.mainWindow.getScaledHeight();
        int width = mc.mainWindow.getScaledWidth();

        int x = width / 2 + this.TEXTURE_WIDTH - offsetx;
        int y = height - 16 - this.TEXTURE_HEIGHT;

        this.DrawBar(mc, gui, unit, energytexturepath, data.getCurrentEnergy(), unit.energyData().Value, false, data, x, y);

        // MANA
        x = width / 2 + this.TEXTURE_WIDTH - offsetx;
        y = height - 15;

        this.DrawBar(mc, gui, unit, manatexturepath, data.getCurrentMana(), unit.manaData().Value, false, data, x, y);

        // MANA

        // HEALTH
        x = width / 2 - this.TEXTURE_WIDTH * 2 + offsetx;
        y = height - 16 - this.TEXTURE_HEIGHT;

        this.DrawBar(mc, gui, unit, healthtexturepath, unit.health()
                .CurrentValue(entity, unit), unit.healthData().Value, false, data, x, y);

        // HEALTH

        // EXP
        x = width / 2 - this.TEXTURE_WIDTH * 2 + offsetx;
        y = height - 15;

        this.DrawBar(mc, gui, unit, experiencetexturepath, data.getExp(), data.GetExpRequiredForLevelUp(), true, data, x, y);

        // EXP
    }

}
