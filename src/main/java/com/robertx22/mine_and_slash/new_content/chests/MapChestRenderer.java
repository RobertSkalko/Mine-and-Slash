package com.robertx22.mine_and_slash.new_content.chests;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.robertx22.mine_and_slash.mmorpg.registers.client.ChestModels;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

public class MapChestRenderer<T extends TileEntity & IChestLid> extends TileEntityRenderer<T> {

    private final ModelRenderer chestLid;
    private final ModelRenderer chestBottom;
    private final ModelRenderer chestLock;

    public MapChestRenderer(TileEntityRendererDispatcher tileEntityRendererDispatcher) {
        super(tileEntityRendererDispatcher);

        this.chestBottom = new ModelRenderer(64, 64, 0, 19);
        this.chestBottom.addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F, 0.0F);
        this.chestLid = new ModelRenderer(64, 64, 0, 0);
        this.chestLid.addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F, 0.0F);
        this.chestLid.rotationPointY = 9.0F;
        this.chestLid.rotationPointZ = 1.0F;
        this.chestLock = new ModelRenderer(64, 64, 0, 0);
        this.chestLock.addBox(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F, 0.0F);
        this.chestLock.rotationPointY = 8.0F;
    }

    @Override
    public void render(T tileEntityIn, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int p_225616_5_, int p_225616_6_) {
        MapChestTile tileEntity = (MapChestTile) tileEntityIn;

        World world = tileEntity.getWorld();
        boolean flag = world != null;

        BlockState blockstate = flag ? tileEntity.getBlockState() : (BlockState) ModBlocks.MAP_CHEST.get()
            .getDefaultState()
            .with(MapChestBlock.FACING, Direction.SOUTH);
        Block block = blockstate.getBlock();

        MapChestTile.ChestTypes type = MapChestTile.ChestTypes.NORMAL;

        if (block instanceof MapChestBlock) {
            MapChestBlock chestBlock = (MapChestBlock) block;

            matrixStack.push();
            float f = blockstate.get(MapChestBlock.FACING)
                .getHorizontalAngle();
            matrixStack.translate(0.5D, 0.5D, 0.5D);
            matrixStack.rotate(Vector3f.YP.rotationDegrees(-f));
            matrixStack.translate(-0.5D, -0.5D, -0.5D);

            TileEntityMerger.ICallbackWrapper<? extends MapChestTile> iCallbackWrapper;
            if (flag) {
                iCallbackWrapper = chestBlock.getWrapper(blockstate, world, tileEntity.getPos(), true);
            } else {
                iCallbackWrapper = TileEntityMerger.ICallback::func_225537_b_;
            }

            float f1 = iCallbackWrapper.apply(MapChestBlock.getLid((IChestLid) tileEntity))
                .get(partialTicks);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = iCallbackWrapper.apply(new DualBrightnessCallback<>())
                .applyAsInt(p_225616_5_);

            Material material = ChestModels.chooseChestModel(tileEntity, type);
            IVertexBuilder ivertexbuilder = material.getBuffer(iRenderTypeBuffer, RenderType::getEntityCutout);

            this.handleModelRender(matrixStack, ivertexbuilder, this.chestLid, this.chestLock, this.chestBottom, f1, i, p_225616_6_);

            matrixStack.pop();
        }
    }

    private void handleModelRender(MatrixStack matrixStack, IVertexBuilder iVertexBuilder, ModelRenderer firstModel, ModelRenderer secondModel, ModelRenderer thirdModel, float f1, int p_228871_7_, int p_228871_8_) {
        firstModel.rotateAngleX = -(f1 * ((float) Math.PI / 2F));
        secondModel.rotateAngleX = firstModel.rotateAngleX;
        firstModel.render(matrixStack, iVertexBuilder, p_228871_7_, p_228871_8_);
        secondModel.render(matrixStack, iVertexBuilder, p_228871_7_, p_228871_8_);
        thirdModel.render(matrixStack, iVertexBuilder, p_228871_7_, p_228871_8_);
    }
}
