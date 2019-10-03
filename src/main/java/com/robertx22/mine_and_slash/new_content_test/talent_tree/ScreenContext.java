package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.new_content_test.talent_tree.gui.PerkTreeScreen;

public class ScreenContext {

    public float zoom, scrollX, scrollY;

    public ScreenContext(float zoom, float scrolLX, float scrollY) {
        this.zoom = zoom;
        this.scrollX = scrolLX;
        this.scrollY = scrollY;
    }

    public ScreenContext(PerkTreeScreen screen) {
        this.zoom = screen.zoom;
        this.scrollX = screen.scrollX;
        this.scrollY = screen.scrollY;
    }

    public float getZoomMulti() {
        return 1 / zoom;
    }

}
