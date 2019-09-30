package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.util.ResourceLocation;

public enum PerkType {

    SMALL(22, 22, new ResourceLocation(Ref.MODID, "textures/gui/talents/small_perk.png")),
    BIG(22, 26, new ResourceLocation(Ref.MODID, "textures/gui/talents/big_perk.png")),
    MAJOR(26, 26, new ResourceLocation(Ref.MODID, "textures/gui/talents/major_perk.png"));

    PerkType(int x, int y, ResourceLocation loc) {
        this.sizeX = x;
        this.sizeY = y;
        this.TEXTURE = loc;
    }

    public int sizeX;
    public int sizeY;

    public ResourceLocation TEXTURE;

}