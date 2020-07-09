package com.robertx22.mine_and_slash.packets.proxies;

import com.robertx22.mine_and_slash.gui.screens.main_hub.MainHubScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class OpenGuiWrapper {

    public static void openMainHub() {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            net.minecraft.client.Minecraft.getInstance()
                .displayGuiScreen(new MainHubScreen());
        });
    }

}
