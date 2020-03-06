package com.robertx22.mine_and_slash.gui.bar_overlays;

import com.robertx22.mine_and_slash.config.forge.ClientContainer;
import com.robertx22.mine_and_slash.gui.bar_overlays.types.*;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.PlayerGUIs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerBarsOverlayScreen extends AbstractGui {
    private Minecraft mc;

    BottomMiddleOverlay bottomMiddle = new BottomMiddleOverlay();
    BottomMiddleCornersOverlay bottomMiddleCorners = new BottomMiddleCornersOverlay();
    TopLeftOverlay topleft = new TopLeftOverlay();
    MiddleOverlay middle = new MiddleOverlay();
    AzureTopLeftOverlay azuretopleft = new AzureTopLeftOverlay();

    public PlayerBarsOverlayScreen(Minecraft mc) {
        super();
        this.mc = mc;

    }

    UnitData data;
    int ticks = 0;

    private void handleHeartGui(RenderGameOverlayEvent event) {

        if (event.getType()
            .equals(ElementType.HEALTH) && ClientContainer.INSTANCE.SHOW_VANILLA_HEARTS.get() == false) {
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

            if (ticks > 3) {
                UnitData newData = Load.Unit(mc.player);

                ticks = 0;

                if (newData != null) {
                    data = newData;
                }
            }

            if (data == null || data.getUnit() == null || mc == null || mc.player == null) {
                return;
            }
            if (data.getUnit()
                .energyData() == null || data.getUnit()
                .manaData() == null || data.getUnit()
                .healthData() == null) {
                return;
            }

            PlayerGUIs guiType = ClientContainer.INSTANCE.PLAYER_GUI_TYPE.get();

            if (guiType.equals(PlayerGUIs.Top_Left)) {
                topleft.Draw(this, mc, mc.player, event, data);
            } else if (guiType.equals(PlayerGUIs.Bottom_Middle)) {
                bottomMiddle.Draw(this, mc, mc.player, event, data);
            } else if (guiType.equals(PlayerGUIs.Bottom_Middle_Corners)) {
                bottomMiddleCorners.Draw(this, mc, mc.player, event, data);
            } else if (guiType.equals(PlayerGUIs.Middle)) {
                middle.Draw(this, mc, mc.player, event, data);
            } else if (guiType.equals(PlayerGUIs.Azure_Top_Left)) {
                azuretopleft.Draw(this, mc, mc.player, event, data);
            } else if (guiType.equals(PlayerGUIs.NONE)) {
                // nothing
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}