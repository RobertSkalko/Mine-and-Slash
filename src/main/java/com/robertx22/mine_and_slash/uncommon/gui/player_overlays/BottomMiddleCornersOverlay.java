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
                     RenderGameOverlayEvent event, UnitData data) {
        // ENERGY
        Unit unit = data.getUnit();

        int offsetx = entity.getHeldItemOffhand()
                .isEmpty() ? X_OFFSET_NO_OFFHAND : X_OFFSET_OFFHAND;

        int height = mc.mainWindow.getScaledHeight();
        int width = mc.mainWindow.getScaledWidth();

        int x = width / 2 + this.TEXTURE_WIDTH - offsetx;
        int y = height - 16 - this.TEXTURE_HEIGHT;

        this.DrawBar(mc, gui, energytexturepath, data.getCurrentEnergy(), unit.energyData().Value, Type.ENE, data, x, y);

        // MANA
        x = width / 2 + this.TEXTURE_WIDTH - offsetx;
        y = height - 15;

        this.DrawBar(mc, gui, manatexturepath, data.getCurrentMana(), unit.manaData().Value, Type.MANA, data, x, y);

        // MANA

        // HEALTH
        x = width / 2 - this.TEXTURE_WIDTH * 2 + offsetx;
        y = height - 16 - this.TEXTURE_HEIGHT;

        this.DrawBar(mc, gui, healthtexturepath, unit.health()
                .CurrentValue(entity, unit), unit.healthData().Value, Type.HP, data, x, y);

        // HEALTH

        // EXP
        x = width / 2 - this.TEXTURE_WIDTH * 2 + offsetx;
        y = height - 15;

        this.DrawBar(mc, gui, experiencetexturepath, data.getExp(), data.GetExpRequiredForLevelUp(), Type.EXP, data, x, y);

        // EXP
    }

}
