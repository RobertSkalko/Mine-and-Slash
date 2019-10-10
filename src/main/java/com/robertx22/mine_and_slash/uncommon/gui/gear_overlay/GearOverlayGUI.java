package com.robertx22.mine_and_slash.uncommon.gui.gear_overlay;

import com.robertx22.mine_and_slash.onevent.my_events.CollectGearEvent;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GearOverlayGUI extends AbstractGui {
    private Minecraft mc;

    public GearOverlayGUI(Minecraft mc) {
        super();
        this.mc = mc;
    }

    Unit unit;
    EntityCap.UnitData data;
    int ticks = 0;

    List<GearGuiElement> elements = new ArrayList<>();
    List<GearGuiElement> elementsToRender = new ArrayList<>();

    private void setupElements() {

        elements.clear();

        elements.add(new GearGuiElement(mc.player.getHeldItemMainhand(), data));
        elements.add(new GearGuiElement(mc.player.getHeldItemOffhand(), data));

        CollectGearEvent.getEquipsExcludingWeapon(mc.player)
                .forEach(x -> elements.add(new GearGuiElement(x, data)));

        elementsToRender = elements.stream()
                .filter(x -> x.shouldRender())
                .collect(Collectors.toList());

    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void renderGearOverlay(RenderGameOverlayEvent event) {

        try {

            if (event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
                return;
            }

            ticks++;

            if (ticks > 25) {
                EntityCap.UnitData newData = Load.Unit(mc.player);

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
                    setupElements();
                }
            }

            if (unit == null || data == null || mc == null || mc.player == null) {
                return;
            }

            if (mc.player.ticksExisted < 100) {
                return;
            }

            int x = mc.mainWindow.getScaledWidth() - 20;
            int y = mc.mainWindow.getScaledHeight() / 2 - elementsToRender.size() / 2 * 16;

            for (GearGuiElement ele : elementsToRender) {
                ele.render(x, y);
                y += 16;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}