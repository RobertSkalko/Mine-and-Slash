package com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.blocks.slots.handlerslots.RecipeSlot;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.OpenProfessionCraftingPacket;
import com.robertx22.mine_and_slash.network.RequestTilePacket;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseMaterial;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.ItemStackHandler;

public class ProfessionCraftingGui extends ContainerScreen<ProfessionCraftingContainer> {

    public ProfessionTile tile;
    Minecraft mc;

    ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/profession_station.png");
    ResourceLocation buttonImg = new ResourceLocation(Ref.MODID, "textures/gui/recipe_button.png");

    static int x = 256;
    static int y = 207;

    public ProfessionCraftingGui(ProfessionCraftingContainer cont, PlayerInventory inv,
                                 ITextComponent text) {
        super(cont, inv, text);

        this.xSize = x;
        this.ySize = y;

        this.mc = Minecraft.getInstance();

        if (cont.pos != null) {
            TileEntity en = Minecraft.getInstance().world.getTileEntity(cont.pos);
            if (en instanceof ProfessionTile) {
                this.tile = (ProfessionTile) en;
            }
        }

    }

    @Override
    public void init() {
        super.init();

        this.addButton((new MyButton(this.width / 2 - 20 / 2, this.height / 2 - 18 / 2, 20, 18, buttonImg, (button) -> {

            MMORPG.sendToServer(new OpenProfessionCraftingPacket(this.tile.getPos()));
        })));
    }

    static class MyButton extends ImageButton {
        public MyButton(int xPos, int yPos, int xSize, int ySize, ResourceLocation img,
                        Button.IPressable pressable) {
            super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1 /*1 pixel space between */, img, pressable);
        }
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
        renderPreviewItemsAndTooltips(mouseX, mouseY);
        this.renderHoveredToolTip(mouseX, mouseY);

    }

    public void renderPreviewItemsAndTooltips(int mouseX, int mouseY) {
        if (tile != null) {
            if (tile.currentRecipe != null) {

                int x = this.width / 2 - ProfessionCraftingGui.x / 2 + 25;
                int y = this.height / 2 - ProfessionCraftingGui.y / 2 + 20;

                for (BaseMaterial mat : tile.currentRecipe.getMaterials()) {

                    ItemStackHandler handler = new ItemStackHandler(1);
                    handler.setStackInSlot(0, mat.getPreview());

                    RecipeSlot slot = new RecipeSlot(handler, 0, x, y);

                    this.drawSlot(slot);

                    if (this.isInRect(slot.xPos, slot.yPos, 16, 16, mouseX, mouseY)) {
                        this.renderTooltip(slot.getStack(), mouseX, mouseY);
                    }

                    y += 18;
                }

                x += 185;
                y = this.height / 2 - ProfessionCraftingGui.y / 2 + 20;

                ItemStack output = tile.currentRecipe.getOutput(tile).getPreview();
                ItemStackHandler handler = new ItemStackHandler(1);
                handler.setStackInSlot(0, output);

                RecipeSlot slot = new RecipeSlot(handler, 0, x, y);

                this.drawSlot(slot);

                if (this.isInRect(slot.xPos, slot.yPos, 16, 16, mouseX, mouseY)) {
                    this.renderTooltip(slot.getStack(), mouseX, mouseY);
                }

                y += 18;

            }

        }
    }

    // Returns true if the given x,y coordinates are within the given rectangle
    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX,
                                   int mouseY) {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
        Minecraft.getInstance().getTextureManager().bindTexture(texture);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, 0, 0, xSize, ySize);

    }

}
