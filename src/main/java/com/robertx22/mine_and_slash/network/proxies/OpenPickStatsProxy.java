package com.robertx22.mine_and_slash.network.proxies;

import com.robertx22.mine_and_slash.gui.stat_point_screen.StatPointScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class OpenPickStatsProxy {

    public static void open() {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            net.minecraft.client.Minecraft.getInstance()
                    .displayGuiScreen(new StatPointScreen());
        });
    }
}
