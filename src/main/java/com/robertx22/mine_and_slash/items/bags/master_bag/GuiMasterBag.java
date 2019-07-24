package com.robertx22.mine_and_slash.items.bags.master_bag;

import com.robertx22.mine_and_slash.items.bags.BaseBagGui;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class GuiMasterBag extends BaseBagGui<ContainerMasterBag> {

    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/master_bag.png");

    static int x = 199;
    static int y = 222;

    public GuiMasterBag(ContainerMasterBag bag, PlayerInventory inv,
                        ITextComponent comp) {
        super(inv, bag, x, y);

        this.minecraft = Minecraft.getInstance();

        int i = 0;
        for (ContainerMasterBag.ItemType type : ContainerMasterBag.ItemType.values()) {

            MasterButton button = new MasterButton(getGUIStartX() - 20, getGUIStartY() + 18 * i, type);
            mybuttons.add(button);

            i++;
        }

    }

    private int getGUIStartX() {

        return (int) (minecraft.mainWindow.getScaledWidth() / 2 + this.x / 2);
    }

    private int getGUIStartY() {

        return (int) (minecraft.mainWindow.getScaledHeight() / 2 - this.y / 2 + 18);
    }

    List<MasterButton> mybuttons = new ArrayList<>();

    @Override
    public ResourceLocation texture() {
        return texture;
    }

    @Override
    public int rows() {
        return 6;
    }

    @Override
    public String name() {
        return "";
    }

    @Override
    public boolean mouseClicked(double X, double Y, int idk) {
        super.mouseClicked(X, Y, idk);

        // my stuff

        for (MasterButton widget : this.mybuttons) {

            if (widget.isMouseOver(X, Y)) {

                widget.onClick(X, Y);
                //System.out.println("button works!!  " + widget.type.toString());

            } else {
                //System.out.println("button doesnt work " + X + " " + Y);
            }

        }

        return true;
    }

}