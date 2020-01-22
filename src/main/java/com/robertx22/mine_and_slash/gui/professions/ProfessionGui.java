package com.robertx22.mine_and_slash.gui.professions;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.blocks.slots.handlerslots.RecipeSlot;
import com.robertx22.mine_and_slash.gui.professions.widgets.ChooseRecipeButton;
import com.robertx22.mine_and_slash.gui.professions.widgets.OnlyLvlMetCheckBox;
import com.robertx22.mine_and_slash.gui.professions.widgets.ProfessionLvlBar;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.RequestTilePacket;
import com.robertx22.mine_and_slash.professions.blocks.bases.ProfessionContainer;
import com.robertx22.mine_and_slash.professions.blocks.bases.ProfessionTile;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.uncommon.capability.ProfessionsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProfessionGui extends ContainerScreen<ProfessionContainer> implements IGuiEventListener {

    Minecraft mc;
    public ProfessionTile tile;
    List<ChooseRecipeButton> displayedRecipeButtons = new ArrayList<>();
    List<BaseRecipe> filteredRecipes = new ArrayList<>();
    ProfessionsCap.IProfessionsData proffs;

    private TextFieldWidget searchBar;
    private OnlyLvlMetCheckBox onlyLvlMetCheckbox;
    private ProfessionLvlBar lvlbar;

    int currentRow = 0;

    static int maxRowMembers = 1;
    static int maxRows = 10;

    ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/profession/villager.png");

    static int x = 318;
    static int y = 232;

    public ProfessionGui(ProfessionContainer cont, PlayerInventory inv, ITextComponent text) {
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

        if (x == 256 == false) { // if isnt escape key

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
        if (this.searchBar != null && this.searchBar.isFocused()) {
            if (this.searchBar.charTyped(c, i)) {
                this.currentRow = 0;
                this.updateRecipeButtons();

                return true;
            }
        }
        return false;

    }

    public void updateRecipeButtons() {

        displayedRecipeButtons.clear();

        List<BaseRecipe> recipes = this.getCurrentRecipes();

        this.filteredRecipes.clear();
        this.filteredRecipes.addAll(recipes);

        int x = this.guiLeft + 4;
        int y = this.guiTop + 19;
        int xOffset = 0;

        int count = 0;
        int n = 0;
        for (int i = getCurrentRow() * maxRowMembers; i < recipes.size(); i++) {

            count++;

            if (count > maxRows * maxRowMembers) {
                return;
            }

            BaseRecipe recipe = recipes.get(i);

            ItemStack output = recipe.getOutput(tile).getPreview();

            if (n == maxRowMembers) {
                y += ChooseRecipeButton.ySize;
                n = 0;
            }

            xOffset = n * (ChooseRecipeButton.xSize + 1);

            ChooseRecipeButton button = new ChooseRecipeButton(
                    proffs.getLevel(recipe.profession()), recipe, output, x + xOffset, y, tile.getPos());

            this.displayedRecipeButtons.add(button);

            n++;
        }

    }

    public int getCurrentRow() {

        if (this.filteredRecipes.size() < maxRows) {
            return 0;
        }

        return MathHelper.clamp(this.currentRow, 0, this.filteredRecipes.size() / maxRowMembers);

    }

    Slot currentOutput;

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {

        if (this.lvlbar == null) {
            this.lvlbar = new ProfessionLvlBar(
                    tile.profession, proffs, guiLeft + 229 - ProfessionLvlBar.xSize / 2,
                    guiTop + 70 - ProfessionLvlBar.ySize
            );
        }

        if (this.searchBar == null) {
            String s = this.searchBar != null ? this.searchBar.getText() : "";
            this.searchBar = new TextFieldWidget(
                    this.mc.fontRenderer, this.guiLeft + 20, this.guiTop + 5, 80, 9 + 5,
                    I18n.format("itemGroup.search")
            );
            this.searchBar.setMaxStringLength(50);
            this.searchBar.setEnableBackgroundDrawing(false);
            this.searchBar.setVisible(true);
            this.searchBar.setTextColor(16777215);
            this.searchBar.setText(s);
        }

        if (onlyLvlMetCheckbox == null) {

            this.onlyLvlMetCheckbox = new OnlyLvlMetCheckBox(this.guiLeft + xSize - 27, this.guiTop + 12, 150);
        }

        if (this.onlyLvlMetCheckbox.changed) {
            this.onlyLvlMetCheckbox.changed = false;
            this.currentRow = 0;
        }

        if (tile != null) {
            if (mc.player.ticksExisted % 10 == 0) {
                MMORPG.sendToServer(new RequestTilePacket(tile.getPos()));
            }
        }
        if (mc.player.ticksExisted % 5 == 0) {
            updateRecipeButtons();
        }
        RenderHelper.disableStandardItemLighting();
        this.renderBackground();
        RenderHelper.disableStandardItemLighting();
        super.render(mouseX, mouseY, partialTicks);

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1F);
        RenderHelper.disableStandardItemLighting();
        renderItemIcons(mouseX, mouseY, partialTicks);

        RenderHelper.disableStandardItemLighting();
        this.onlyLvlMetCheckbox.render(mouseX, mouseY, partialTicks);

        RenderHelper.disableStandardItemLighting();
        this.searchBar.render(mouseX, mouseY, partialTicks);

        RenderHelper.disableStandardItemLighting();
        this.lvlbar.render(mouseX, mouseY, partialTicks);

        RenderHelper.disableStandardItemLighting();
        renderTooltips(mouseX, mouseY, partialTicks);

    }

    public void renderItemIcons(int mouseX, int mouseY, float partialTicks) {
        this.displayedRecipeButtons.forEach(x -> {
            RenderHelper.disableStandardItemLighting();
            x.render(mouseX, mouseY, partialTicks);
            RenderHelper.disableStandardItemLighting();
            this.drawSlot(x.slot);
            x.materialSlots.forEach(slot -> {
                RenderHelper.disableStandardItemLighting();
                this.drawSlot(slot);
            });
        });

        if (this.tile.currentRecipe != null) {
            int x = this.guiLeft + 217 + 5;
            int y = this.guiTop + 19 + 5;
            currentOutput = new Slot(new Inventory(tile.currentRecipe.getOutput(tile).getPreview()), 0, x, y);
            this.drawSlot(currentOutput);
        }
    }

    public void renderTooltips(int mouseX, int mouseY, float partialTicks) {
        this.renderHoveredToolTip(mouseX, mouseY);

        this.displayedRecipeButtons.forEach(button -> {

            if (button.isInsideSlot(button.slot, mouseX, mouseY)) {
                this.renderTooltip(button.slot.getStack(), mouseX, mouseY);
            }
            for (RecipeSlot slot : button.materialSlots) {

                if (button.isInsideSlot(slot, mouseX, mouseY)) {
                    this.renderTooltip(slot.getStack(), mouseX, mouseY);
                }

            }

        });

        if (currentOutput != null) {
            if (GuiUtils.isInRect(currentOutput.xPos, currentOutput.yPos, 16, 16, mouseX, mouseY)) {
                this.renderTooltip(currentOutput.getStack(), mouseX, mouseY);
            }
        }
    }

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

                String s = recipe.getOutput(tile).getPreview().getDisplayName().getFormattedText().toLowerCase();

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

        if (recipes.size() > 1) {
            recipes = recipes.stream()
                    .sorted((Comparator.comparingInt(BaseRecipe::getLevelReq)))
                    .collect(Collectors.toList());

        }

        return recipes;

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
        Minecraft.getInstance().getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, this.getBlitOffset(), 0.0F, 0.0F, this.xSize, this.ySize, 256, 512);

        // the cook time arrow
        float cooktime = tile.getCookTimeCompleted();
        blit(guiLeft + 222, guiTop + 95, 324, 324, 0.0F, 16, (int) (22 * cooktime), 256, 512);

    }

}
