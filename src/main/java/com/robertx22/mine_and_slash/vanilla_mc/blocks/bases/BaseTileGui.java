package com.robertx22.mine_and_slash.vanilla_mc.blocks.bases;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import com.robertx22.mine_and_slash.vanilla_mc.packets.RequestTilePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public abstract class BaseTileGui<Tile extends TileEntity> extends Screen {

    public Tile tile;
    public Minecraft mc;

    /**
     * Starting X position for the Gui. Inconsistent use for Gui backgrounds.
     */
    protected int guiLeft;
    /**
     * Starting Y position for the Gui. Inconsistent use for Gui backgrounds.
     */
    protected int guiTop;

    protected int xSize = 176;
    protected int ySize = 166;

    @Override
    protected void init() {
        super.init();

        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

    }

    public BaseTileGui(Class<Tile> token, BlockPos pos, int x, int y) {
        super(new SText(""));

        xSize = x;
        ySize = y;

        this.mc = Minecraft.getInstance();

        if (pos != null) {
            TileEntity en = Minecraft.getInstance().world.getTileEntity(pos);
            if (en != null) {
                if (token.isAssignableFrom(en.getClass())) {
                    this.tile = (Tile) en;
                }
            }
        }
    }

    int ticks;

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {

        ticks++;

        if (tile != null) {
            if (ticks % 10 == 0 || ticks < 2) {
                MMORPG.sendToServer(new RequestTilePacket(tile.getPos()));
            }
        }

        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);

    }

}
