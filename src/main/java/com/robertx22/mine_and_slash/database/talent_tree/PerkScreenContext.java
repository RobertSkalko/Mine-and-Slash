package com.robertx22.mine_and_slash.database.talent_tree;

import com.robertx22.mine_and_slash.gui.bases.BasePerkTreeScreen;
import com.robertx22.mine_and_slash.gui.talent_tree_gui.TalentPerkTreeScreen;
import net.minecraft.client.Minecraft;

public class PerkScreenContext {

    public float zoom, scrollX, scrollY;

    public int offsetX;
    public int offsetY;

    public PerkScreenContext(float zoom, float scrolLX, float scrollY) {
        this.zoom = zoom;
        this.scrollX = scrolLX;
        this.scrollY = scrollY;
        setupOffsets();
    }

    public PerkScreenContext(BasePerkTreeScreen screen) {
        this.zoom = screen.zoom;
        this.scrollX = screen.scrollX;
        this.scrollY = screen.scrollY;
        setupOffsets();
    }

    private void setupOffsets() {
        offsetX = (int) (Minecraft.getInstance().mainWindow.getScaledWidth() * getZoomMulti() / 2 - TalentPerkTreeScreen
                .sizeX() * getZoomMulti() / 2);
        offsetY =
                (int) (Minecraft.getInstance().mainWindow.getScaledHeight() * getZoomMulti() / 2 - TalentPerkTreeScreen
                .sizeY() * getZoomMulti() / 2);
    }

    public float getZoomMulti() {
        return 1 / zoom;
    }

}
