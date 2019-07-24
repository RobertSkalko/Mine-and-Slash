package com.robertx22.mine_and_slash.uncommon;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.util.ResourceLocation;

public class Res {
    public static final ResourceLocation loc(String id) {
        return new ResourceLocation(Ref.MODID, id);
    }
}
