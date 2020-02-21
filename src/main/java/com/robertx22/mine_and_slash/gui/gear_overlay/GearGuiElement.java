package com.robertx22.mine_and_slash.gui.gear_overlay;

import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RepairUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class GearGuiElement {

    Minecraft mc = Minecraft.getInstance();

    public enum RenderReason {
        NONE,
        BROKEN,
        UNMET_REQ
    }

    static ItemStack barrier = new ItemStack(Items.BARRIER);

    ItemStack stack;
    GearItemData gear;
    EntityCap.UnitData data;

    RenderReason reason = RenderReason.NONE;

    public GearGuiElement(ItemStack stack, EntityCap.UnitData data) {
        this.stack = stack;
        gear = Gear.Load(stack);
        this.data = data;

        setReason();
    }

    private void setReason() {
        if (gear != null && gear.meetsRequirements(data) == false) {
            this.reason = RenderReason.UNMET_REQ;
        } else {
            if (RepairUtils.isItemBroken(stack)) {
                this.reason = RenderReason.BROKEN;
            }
        }

    }

    public boolean shouldRender() {
        return gear != null && reason != RenderReason.NONE;
    }

    public void render(int x, int y) {

        mc.getItemRenderer().renderItemAndEffectIntoGUI(stack, x, y);

        if (reason == RenderReason.UNMET_REQ) {
            mc.getItemRenderer().renderItemAndEffectIntoGUI(barrier, x, y);
        }
    }

}
