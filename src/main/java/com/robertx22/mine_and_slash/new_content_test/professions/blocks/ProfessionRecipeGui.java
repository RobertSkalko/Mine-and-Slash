package com.robertx22.mine_and_slash.new_content_test.professions.blocks;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.RequestTilePacket;
import com.robertx22.mine_and_slash.network.ScrollPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

public class ProfessionRecipeGui extends ContainerScreen<ProfessionRecipeContainer> {

    public ProfessionTile tile;
    Minecraft mc;

    ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/recipes_list.png");

    static int x = 199;
    static int y = 222;

    public ProfessionRecipeGui(ProfessionRecipeContainer cont, PlayerInventory inv,
                               ITextComponent text) {
        super(cont, inv, text);

        this.xSize = x;
        this.ySize = y;

        this.mc = Minecraft.getInstance();

        if (cont.pos != null) {
            TileEntity en = Minecraft.getInstance().world.getTileEntity(cont.pos);
            if (en instanceof ProfessionTile) {
                this.tile = (ProfessionTile) en;

                MMORPG.sendToServer(new ScrollPacket(this.currentRow, tile.getPos()));

            }
        }

    }

    @Override
    public boolean mouseClicked(double x, double y, int ticks) {

        Slot slot = this.getSelectedSlot(x, y);

        if (slot != null) {

            ItemStack stack = slot.getStack();

            System.out.println("click works");

            //  this.playerInventory.player.openContainer(.....);

            ///clickedSlot.getStack()
            // etc this.playerInventory.player.openContainer
        }
        return super.mouseClicked(x, y, ticks);

    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {

        if (tile != null) {
            if (mc.player.ticksExisted % 20 == 0) {
                MMORPG.sendToServer(new RequestTilePacket(tile.getPos()));
            }
        }

        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

    }

    int currentRow = 0;

    @Override
    public boolean mouseScrolled(double num1, double num2, double num3) {

        this.currentRow -= num3;
        this.currentRow = MathHelper.clamp(currentRow, 0, tile.profession.recipes.size() / 9);

        MMORPG.sendToServer(new ScrollPacket(this.currentRow, tile.getPos()));
        MMORPG.sendToServer(new RequestTilePacket(tile.getPos()));

        return false;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
        Minecraft.getInstance().getTextureManager().bindTexture(texture);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, 0, 0, xSize, ySize);
        drawExtraGuiStuff(partialTicks, x, y);

    }

    public void drawExtraGuiStuff(float partialTicks, int x, int y) {

    }

}
