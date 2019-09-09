package com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.RequestTilePacket;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.uncommon.capability.ProfessionsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
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
    List<ChooseRecipeButton> displayedRecipeButtons = new ArrayList<>();
    List<BaseRecipe> filteredRecipes = new ArrayList<>();
    ProfessionsCap.IProfessionsData proffs;

    private TextFieldWidget searchBar;
    private OnlyLvlMetCheckBox onlyLvlMetCheckbox;

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

        this.proffs = Load.professions(mc.player);

    }

    @Override
    public boolean mouseClicked(double x, double y, int ticks) {

        this.displayedRecipeButtons.forEach(button -> button.onClick(x, y));
        this.searchBar.mouseClicked(x, y, ticks);
        this.onlyLvlMetCheckbox.mouseClicked(x, y, ticks);
        return super.mouseClicked(x, y, ticks);

    }

    @Override
    public boolean keyPressed(int x, int y, int i) {

        if (x == 256 == false) { // if escape key
            if (searchBar.isFocused()) {
                return searchBar.keyPressed(x, y, i);
            }
        }

        return super.keyPressed(x, y, i);

    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public boolean charTyped(char c, int i) {
        if (this.searchBar.charTyped(c, i)) {
            this.currentRow = 0;
            this.updateRecipeButtons();

            return true;
        } else {
            return false;
        }

    }

    public void updateRecipeButtons() {

        displayedRecipeButtons.clear();

        List<BaseRecipe> recipes = this.getCurrentRecipes();

        this.filteredRecipes.clear();
        this.filteredRecipes.addAll(recipes);

        int x = this.guiLeft + 5;
        int y = this.guiTop + 30;
        int xOffset = 0;

        int count = 0;
        int n = 0;
        for (int i = getCurrentRow() * 3; i < recipes.size(); i++) {

            count++;

            if (count > 5 * 3) {
                return;
            }

            BaseRecipe recipe = recipes.get(i);

            ItemStack output = recipe.getOutput(tile).getPreview();

            if (n == 3) {
                y += ChooseRecipeButton.ySize + 3;
                n = 0;
            }

            xOffset = n * (ChooseRecipeButton.xSize + 2);

            ChooseRecipeButton button = new ChooseRecipeButton(proffs.getLevel(recipe.profession()), recipe, output, x + xOffset, y, tile
                    .getPos());

            this.displayedRecipeButtons.add(button);

            n++;
        }

    }

    public int getCurrentRow() {

        if (this.filteredRecipes.size() < 5 * 3) {
            return 0;
        }

        return MathHelper.clamp(this.currentRow, 0, this.filteredRecipes.size() / 3);

    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {

        if (this.searchBar == null) {
            String s = this.searchBar != null ? this.searchBar.getText() : "";
            this.searchBar = new TextFieldWidget(this.mc.fontRenderer, this.guiLeft + 5, this.guiTop + 5, 80, 9 + 5, I18n
                    .format("itemGroup.search"));
            this.searchBar.setMaxStringLength(50);
            this.searchBar.setEnableBackgroundDrawing(false);
            this.searchBar.setVisible(true);
            this.searchBar.setTextColor(16777215);
            this.searchBar.setText(s);
        }

        if (onlyLvlMetCheckbox == null) {

            this.onlyLvlMetCheckbox = new OnlyLvlMetCheckBox(this.guiLeft + xSize - 25, this.guiTop + 10, 150);
        }

        if (tile != null) {
            if (mc.player.ticksExisted % 20 == 0) {
                MMORPG.sendToServer(new RequestTilePacket(tile.getPos()));
            }
        }

        if (mc.player.ticksExisted % 5 == 0) {
            updateRecipeButtons();
        }

        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);

        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1F);
        GlStateManager.enableBlend();
        GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

        this.onlyLvlMetCheckbox.render(mouseX, mouseY, partialTicks);
        this.searchBar.render(mouseX, mouseY, partialTicks);

        this.displayedRecipeButtons.forEach(x -> {
            x.render(mouseX, mouseY, partialTicks);
            this.drawSlot(x.slot);

        });

        this.renderHoveredToolTip(mouseX, mouseY);

        this.displayedRecipeButtons.forEach(x -> {
            if (x.isInside(mouseX, mouseY)) {
                this.renderTooltip(x.slot.getStack(), mouseX, mouseY);
            }
        });

    }

    int currentRow = 0;

    @Override
    public boolean mouseScrolled(double num1, double num2, double num3) {

        this.currentRow -= num3;
        this.currentRow = this.getCurrentRow();

        this.updateRecipeButtons();

        return false;
    }

    public List<BaseRecipe> getCurrentRecipes() {

        List<BaseRecipe> recipes = tile.profession.recipes();

        if (this.searchBar.getText().isEmpty() == false) {

            List<BaseRecipe> list = new ArrayList<>();

            for (BaseRecipe recipe : recipes) {

                String s = recipe.getOutput(tile)
                        .getPreview()
                        .getDisplayName()
                        .getFormattedText()
                        .toLowerCase();

                if (s.contains(this.searchBar.getText().toLowerCase())) {
                    list.add(recipe);
                }
            }
            recipes = list;

        }

        ProfessionsCap.IProfessionsData profs = Load.professions(mc.player);

        if (this.onlyLvlMetCheckbox.checked == OnlyLvlMetCheckBox.PickedRecipes.LVL_MET) {
            recipes = recipes.stream()
                    .filter(x -> x.professionLevelReq <= profs.getLevel(x.profession()))
                    .collect(Collectors.toList());
        }
        if (this.onlyLvlMetCheckbox.checked == OnlyLvlMetCheckBox.PickedRecipes.LVL_NOT_MET) {
            recipes = recipes.stream()
                    .filter(x -> x.professionLevelReq > profs.getLevel(x.profession()))
                    .collect(Collectors.toList());
        }

        return recipes;

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
        Minecraft.getInstance().getTextureManager().bindTexture(texture);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, 0, 0, xSize, ySize);

    }

}
