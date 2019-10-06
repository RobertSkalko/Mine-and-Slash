package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.new_content_test.talent_tree.gui.PerkTreeScreen;
import net.minecraft.client.Minecraft;

public class ScreenContext {

    public float zoom, scrollX, scrollY;

    public int offsetX;
    public int offsetY;

    public ScreenContext(float zoom, float scrolLX, float scrollY) {
        this.zoom = zoom;
        this.scrollX = scrolLX;
        this.scrollY = scrollY;
        setupOffsets();
    }

    public ScreenContext(PerkTreeScreen screen) {
        this.zoom = screen.zoom;
        this.scrollX = screen.scrollX;
        this.scrollY = screen.scrollY;
        setupOffsets();
    }

    private void setupOffsets() {
        offsetX = (int) (Minecraft.getInstance().mainWindow.getScaledWidth() * getZoomMulti() / 2 - PerkTreeScreen.sizeX * getZoomMulti() / 2);
        offsetY = (int) (Minecraft.getInstance().mainWindow.getScaledHeight() * getZoomMulti() / 2 - PerkTreeScreen.sizeY * getZoomMulti() / 2);
    }

    public float getZoomMulti() {
        return 1 / zoom;
    }

}
