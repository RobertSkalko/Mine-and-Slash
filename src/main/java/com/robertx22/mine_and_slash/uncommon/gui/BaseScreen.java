package com.robertx22.mine_and_slash.uncommon.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

public class BaseScreen extends Screen {

    protected BaseScreen() {
        super(new StringTextComponent(""));
    }

    @Override
    public boolean mouseReleased(double x, double y, int ticks) {
        this.buttons.forEach(b -> b.onClick(x, y));
        return super.mouseReleased(x, y, ticks);
    }

}
