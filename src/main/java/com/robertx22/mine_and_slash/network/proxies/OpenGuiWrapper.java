package com.robertx22.mine_and_slash.network.proxies;

import com.robertx22.mine_and_slash.gui.stat_allocation_screen.StatAllocationScreen;
import com.robertx22.mine_and_slash.gui.talent_tree_gui.TalentPerkTreeScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class OpenGuiWrapper {

    public static void openStatAllocation() {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            net.minecraft.client.Minecraft.getInstance()
                    .displayGuiScreen(new StatAllocationScreen());
        });
    }

    public static void openSpellPerks() {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            net.minecraft.client.Minecraft.getInstance().displayGuiScreen(null);
        });
    }

    public static void openTalents() {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            net.minecraft.client.Minecraft.getInstance()
                    .displayGuiScreen(new TalentPerkTreeScreen());
        });
    }
}
