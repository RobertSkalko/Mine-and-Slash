package com.robertx22.mine_and_slash.uncommon.gui.player_overlays;

import com.robertx22.mine_and_slash.config.ClientContainer;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.PlayerGUIs;
import com.robertx22.mine_and_slash.uncommon.gui.NewOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BarsGUI extends AbstractGui {
    private Minecraft mc;

    BottomMiddleOverlay bottomMiddle = new BottomMiddleOverlay();
    BottomMiddleCornersOverlay bottomMiddleCorners = new BottomMiddleCornersOverlay();
    TopLeftOverlay topleft = new TopLeftOverlay();
    MiddleOverlay middle = new MiddleOverlay();
    NewOverlay neww = new NewOverlay();

    public BarsGUI(Minecraft mc) {
        super();
        this.mc = mc;

    }

    Unit unit;
    UnitData data;
    int ticks = 0;

    private void handleHeartGui(RenderGameOverlayEvent event) {

        if (event.getType()
                .equals(ElementType.HEALTH) && ClientContainer.INSTANCE.SHOW_VANILLA_HEARTS
                .get() == false) {
            event.setCanceled(true);
        }

    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderPlayerOverlay(RenderGameOverlayEvent event) {

        try {

            handleHeartGui(event);

            if (event.isCancelable() || event.getType() != ElementType.EXPERIENCE) {
                return;
            }

            ticks++;

            if (ticks > 10) {
                UnitData newData = Load.Unit(mc.player);

                ticks = 0;

                Unit newUnit = null;

                if (newData != null) {
                    data = newData;
                    if (newData.getUnit() != null) {
                        newUnit = newData.getUnit();
                    }
                    if (newUnit != null) {
                        unit = newUnit;
                    }
                }
            }

            if (unit == null || data == null || mc == null || mc.player == null) {
                return;
            }
            if (unit.energyData() == null || unit.manaData() == null || unit.healthData() == null) {
                return;
            }

            if (ClientContainer.INSTANCE.PLAYER_GUI_TYPE.get()
                    .equals(PlayerGUIs.Top_Left)) {
                topleft.Draw(this, mc, mc.player, event, unit, data);
            } else if (ClientContainer.INSTANCE.PLAYER_GUI_TYPE.get()
                    .equals(PlayerGUIs.Bottom_Middle)) {
                bottomMiddle.Draw(this, mc, mc.player, event, unit, data);
            } else if (ClientContainer.INSTANCE.PLAYER_GUI_TYPE.get()
                    .equals(PlayerGUIs.Bottom_Middle_Corners)) {
                bottomMiddleCorners.Draw(this, mc, mc.player, event, unit, data);
            } else if (ClientContainer.INSTANCE.PLAYER_GUI_TYPE.get()
                    .equals(PlayerGUIs.Middle)) {
                middle.Draw(this, mc, mc.player, event, unit, data);
            }

            if (MMORPG.RUN_DEV_TOOLS) {
                neww.Draw(this, mc, mc.player, event, unit, data);
            }

        } catch (Exception e) {
            // e.printStackTrace();
        }

    }

}