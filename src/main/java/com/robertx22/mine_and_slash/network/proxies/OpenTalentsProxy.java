package com.robertx22.mine_and_slash.network.proxies;

import com.robertx22.mine_and_slash.new_content_test.talent_tree.gui.PerkTreeScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class OpenTalentsProxy {

    public static void open() {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            net.minecraft.client.Minecraft.getInstance()
                    .displayGuiScreen(new PerkTreeScreen());
        });
    }
}
