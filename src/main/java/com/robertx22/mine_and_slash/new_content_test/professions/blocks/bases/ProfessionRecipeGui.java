package com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.RequestTilePacket;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class ProfessionRecipeGui extends ContainerScreen<ProfessionRecipeContainer> {

    Minecraft mc;
    public ProfessionTile tile;
    List<ChooseRecipeButton> recipeButtons = new ArrayList<>();

    ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/recipes_list.png");

    static int x = 256;
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
            }
        }

    }

    @Override
    public boolean mouseClicked(double x, double y, int ticks) {

        this.recipeButtons.forEach(button -> button.onClick(x, y));

        return super.mouseClicked(x, y, ticks);

    }

    public void updateRecipeButtons() {

        recipeButtons.clear();

        int x = this.guiLeft;
        int y = this.guiTop;
        int xOffset = 0;

        int n = 0;
        for (BaseRecipe recipe : getCurrentRecipes()) {
            ItemStack output = recipe.getOutput(tile).getPreview();

            if (n == 2) {
                y -= ChooseRecipeButton.ySize + 3;
                n = 0;
            }

            xOffset = n * (ChooseRecipeButton.xSize + 3);

            ChooseRecipeButton button = new ChooseRecipeButton(recipe, output, x + xOffset, y, tile
                    .getPos());

            this.recipeButtons.add(button);

            n++;
        }

    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {

        if (tile != null) {
            if (mc.player.ticksExisted % 20 == 0) {
                MMORPG.sendToServer(new RequestTilePacket(tile.getPos()));
                updateRecipeButtons();
            }
        }

        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

        this.recipeButtons.forEach(x -> {
            x.render(mouseX, mouseX, partialTicks);
            this.renderTooltip(x.slot.getStack(), mouseX, mouseY);

        });

    }

    int currentRow = 0;

    @Override
    public boolean mouseScrolled(double num1, double num2, double num3) {

        this.currentRow -= num3;
        this.currentRow = MathHelper.clamp(currentRow, 0, this.getCurrentRecipes()
                .size() / 9);

        //MMORPG.sendToServer(new RequestTilePacket(tile.getPos()));

        return false;
    }

    public List<BaseRecipe> getCurrentRecipes() { // TODO

        return tile.profession.recipes();

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
