package com.robertx22.mine_and_slash.database.spells.entities.magic_missiles;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseProjectileRenderer;
import com.robertx22.mine_and_slash.database.spells.entities.proj.MagicMissileEntity;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class MagicMissileRenderer extends BaseProjectileRenderer<MagicMissileEntity> {
    public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(Ref.MODID,
                                                                                 "textures/entity/magic_missile.png"
    );

    public MagicMissileRenderer(EntityRendererManager manager) {
        super(manager, new MagicMissileModel());
    }

    @Override
    public ResourceLocation getEntityTexture(MagicMissileEntity entity) {
        return TEXTURE_LOCATION;
    }

}
