package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.util.ResourceLocation;

public enum PerkType {

    SMALL(24, 24, 0),
    BIG(22, 26, 59),
    MAJOR(26, 26, 28);

    PerkType(int x, int y, int offsetX) {
        this.sizeX = x;
        this.sizeY = y;
        this.offsetX = offsetX;

    }

    public int sizeX;
    public int sizeY;
    public int offsetX;

    public int getOffsetY(PerkConnection.Allocation status) {
        if (status == PerkConnection.Allocation.CAN_ALLOCATE) {
            return 0;
        }
        if (status == PerkConnection.Allocation.ALLOCATED) {
            return 32;
        } else {
            return 63;
        }
    }

    public int getOffsetX() {
        return this.offsetX;
    }

    public ResourceLocation TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/talents/perks.png");

}