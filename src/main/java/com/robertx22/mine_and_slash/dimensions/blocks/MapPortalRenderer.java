package com.robertx22.mine_and_slash.dimensions.blocks;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@OnlyIn(Dist.CLIENT)
public class MapPortalRenderer<T extends TileMapPortal> extends TileEntityRenderer<T> {
    public static final ResourceLocation END_SKY_TEXTURE = new ResourceLocation("textures/environment/end_sky.png");
    public static final ResourceLocation END_PORTAL_TEXTURE = new ResourceLocation("textures/entity/end_portal.png");
    private static final Random RANDOM = new Random(31100L);
    private static final List<RenderType> field_228881_e_ = (List) IntStream.range(0, 16)
            .mapToObj((p_228882_0_) -> {
                return RenderType.func_228630_a_(p_228882_0_ + 1);
            })
            .collect(ImmutableList.toImmutableList());

    public MapPortalRenderer(TileEntityRendererDispatcher p_i226019_1_) {
        super(p_i226019_1_);
    }

    public void func_225616_a_(T p_225616_1_, float p_225616_2_, MatrixStack p_225616_3_,
                               IRenderTypeBuffer p_225616_4_, int p_225616_5_,
                               int p_225616_6_) {
        RANDOM.setSeed(31100L);
        double lvt_7_1_ = p_225616_1_.getPos()
                .distanceSq(this.field_228858_b_.renderInfo.getProjectedView(), true);
        int lvt_9_1_ = this.getPasses(lvt_7_1_);
        float lvt_10_1_ = this.getOffset();
        Matrix4f lvt_11_1_ = p_225616_3_.func_227866_c_().func_227870_a_();
        this.func_228883_a_(p_225616_1_, lvt_10_1_, 0.15F, lvt_11_1_, p_225616_4_.getBuffer((RenderType) field_228881_e_
                .get(0)));

        for (int lvt_12_1_ = 1; lvt_12_1_ < lvt_9_1_; ++lvt_12_1_) {
            this.func_228883_a_(p_225616_1_, lvt_10_1_, 2.0F / (float) (18 - lvt_12_1_), lvt_11_1_, p_225616_4_
                    .getBuffer((RenderType) field_228881_e_.get(lvt_12_1_)));
        }

    }

    private void func_228883_a_(T p_228883_1_, float p_228883_2_, float p_228883_3_,
                                Matrix4f p_228883_4_, IVertexBuilder p_228883_5_) {
        float lvt_6_1_ = (RANDOM.nextFloat() * 0.5F + 0.1F) * p_228883_3_;
        float lvt_7_1_ = (RANDOM.nextFloat() * 0.5F + 0.4F) * p_228883_3_;
        float lvt_8_1_ = (RANDOM.nextFloat() * 0.5F + 0.5F) * p_228883_3_;
        this.func_228884_a_(p_228883_1_, p_228883_4_, p_228883_5_, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, lvt_6_1_, lvt_7_1_, lvt_8_1_, Direction.SOUTH);
        this.func_228884_a_(p_228883_1_, p_228883_4_, p_228883_5_, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, lvt_6_1_, lvt_7_1_, lvt_8_1_, Direction.NORTH);
        this.func_228884_a_(p_228883_1_, p_228883_4_, p_228883_5_, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, lvt_6_1_, lvt_7_1_, lvt_8_1_, Direction.EAST);
        this.func_228884_a_(p_228883_1_, p_228883_4_, p_228883_5_, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, lvt_6_1_, lvt_7_1_, lvt_8_1_, Direction.WEST);
        this.func_228884_a_(p_228883_1_, p_228883_4_, p_228883_5_, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, lvt_6_1_, lvt_7_1_, lvt_8_1_, Direction.DOWN);
        this.func_228884_a_(p_228883_1_, p_228883_4_, p_228883_5_, 0.0F, 1.0F, p_228883_2_, p_228883_2_, 1.0F, 1.0F, 0.0F, 0.0F, lvt_6_1_, lvt_7_1_, lvt_8_1_, Direction.UP);
    }

    private void func_228884_a_(T p_228884_1_, Matrix4f p_228884_2_,
                                IVertexBuilder p_228884_3_, float p_228884_4_,
                                float p_228884_5_, float p_228884_6_, float p_228884_7_,
                                float p_228884_8_, float p_228884_9_, float p_228884_10_,
                                float p_228884_11_, float p_228884_12_,
                                float p_228884_13_, float p_228884_14_,
                                Direction p_228884_15_) {
        if (p_228884_1_.shouldRenderFace(p_228884_15_)) {
            p_228884_3_.func_227888_a_(p_228884_2_, p_228884_4_, p_228884_6_, p_228884_8_)
                    .func_227885_a_(p_228884_12_, p_228884_13_, p_228884_14_, 1.0F)
                    .endVertex();
            p_228884_3_.func_227888_a_(p_228884_2_, p_228884_5_, p_228884_6_, p_228884_9_)
                    .func_227885_a_(p_228884_12_, p_228884_13_, p_228884_14_, 1.0F)
                    .endVertex();
            p_228884_3_.func_227888_a_(p_228884_2_, p_228884_5_, p_228884_7_, p_228884_10_)
                    .func_227885_a_(p_228884_12_, p_228884_13_, p_228884_14_, 1.0F)
                    .endVertex();
            p_228884_3_.func_227888_a_(p_228884_2_, p_228884_4_, p_228884_7_, p_228884_11_)
                    .func_227885_a_(p_228884_12_, p_228884_13_, p_228884_14_, 1.0F)
                    .endVertex();
        }

    }

    protected int getPasses(double p_191286_1_) {
        if (p_191286_1_ > 36864.0D) {
            return 1;
        } else if (p_191286_1_ > 25600.0D) {
            return 3;
        } else if (p_191286_1_ > 16384.0D) {
            return 5;
        } else if (p_191286_1_ > 9216.0D) {
            return 7;
        } else if (p_191286_1_ > 4096.0D) {
            return 9;
        } else if (p_191286_1_ > 1024.0D) {
            return 11;
        } else if (p_191286_1_ > 576.0D) {
            return 13;
        } else {
            return p_191286_1_ > 256.0D ? 14 : 15;
        }
    }

    protected float getOffset() {
        return 0.75F;
    }
}
