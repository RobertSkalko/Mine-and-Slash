package com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.RequestTilePacket;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfessionRecipeGui extends ContainerScreen<ProfessionRecipeContainer> implements IGuiEventListener {

    Minecraft mc;
    public ProfessionTile tile;
    List<ChooseRecipeButton> recipeButtons = new ArrayList<>();
    private TextFieldWidget searchBar;

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
        String s = this.searchBar != null ? this.searchBar.getText() : "";
        this.searchBar = new TextFieldWidget(this.mc.fontRenderer, this.guiLeft + 5, this.guiTop + 5, 80, 9 + 5, I18n
                .format("itemGroup.search"));
        this.searchBar.setMaxStringLength(50);
        this.searchBar.setEnableBackgroundDrawing(false);
        this.searchBar.setVisible(true);
        this.searchBar.setTextColor(16777215);
        this.searchBar.setText(s);

    }

    @Override
    public boolean mouseClicked(double x, double y, int ticks) {

        this.recipeButtons.forEach(button -> button.onClick(x, y));
        this.searchBar.mouseClicked(x, y, ticks);
        return super.mouseClicked(x, y, ticks);

    }

    public boolean keyPressed(int x, int y, int i) {

        boolean bool = super.keyPressed(x, y, i);

        if (searchBar.isFocused()) {
            searchBar.keyPressed(x, y, i);
        }

        return bool;
    }

    @Override
    public boolean charTyped(char c, int i) {
        if (this.searchBar.charTyped(c, i)) {

            this.updateRecipeButtons();

            return true;
        } else {
            /*
            if (this.searchBar.getText().length() > 1) {
                this.searchBar.setText(this.searchBar.getText()
                        .substring(0, this.searchBar.getText().length() - 2));
            } else {
                searchBar.setText("");
            }
            this.updateRecipeButtons();


             */
            return false; //IGuiEventListener.super.charTyped(c, i);
        }

    }

    public void updateRecipeButtons() {

        recipeButtons.clear();

        List<BaseRecipe> recipes = this.getCurrentRecipes();

        int x = this.guiLeft + 5;
        int y = this.guiTop + 20;
        int xOffset = 0;

        int n = 0;
        for (int i = this.currentRow * 3; i < recipes.size(); i++) {
            BaseRecipe recipe = recipes.get(i);

            ItemStack output = recipe.getOutput(tile).getPreview();

            if (n == 3) {
                y += ChooseRecipeButton.ySize + 3;
                n = 0;
            }

            xOffset = n * (ChooseRecipeButton.xSize + 2);

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
            }
        }

        updateRecipeButtons();

        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);

        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1F);
        GlStateManager.enableBlend();
        GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

        this.searchBar.render(mouseX, mouseY, partialTicks);

        this.recipeButtons.forEach(x -> {
            x.render(mouseX, mouseY, partialTicks);
            this.drawSlot(x.slot);

        });

        this.renderHoveredToolTip(mouseX, mouseY);

        this.recipeButtons.forEach(x -> {
            if (x.isInside(mouseX, mouseY)) {
                this.renderTooltip(x.slot.getStack(), mouseX, mouseY);
            }
        });

    }

    int currentRow = 0;

    @Override
    public boolean mouseScrolled(double num1, double num2, double num3) {

        this.currentRow -= num3;
        this.currentRow = MathHelper.clamp(currentRow, 0, this.getCurrentRecipes()
                .size() / 9);

        return false;
    }

    public List<BaseRecipe> getCurrentRecipes() { // TODO

        List<BaseRecipe> recipes = tile.profession.recipes();

        if (this.searchBar.getText().isEmpty() == false) {

            recipes = recipes.stream()
                    .filter(x -> x.getOutput(tile)
                            .getPreview()
                            .getDisplayName()
                            .getFormattedText()
                            .toLowerCase()
                            .contains(this.searchBar.getText().toLowerCase()))
                    .collect(Collectors.toList());

        }

        return recipes;

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
