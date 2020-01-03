package com.robertx22.mine_and_slash.blocks.map_device;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.UseMapTicketPacket;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class MapGetGroupMapButton extends ImageButton {

    public static int xSize = 155;
    public static int ySize = 20;
    static ResourceLocation img = new ResourceLocation(Ref.MODID, "textures/gui/map_device_buttons.png");
    BlockPos pos;

    public MapGetGroupMapButton(BlockPos pos, int xPos, int yPos) {
        super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, img, (button) -> {
        });
        this.pos = pos;

    }

    @Override
    public void onPress() {

        MMORPG.sendToServer(new UseMapTicketPacket(pos));

        this.onPress.onPress(this);
    }

}
