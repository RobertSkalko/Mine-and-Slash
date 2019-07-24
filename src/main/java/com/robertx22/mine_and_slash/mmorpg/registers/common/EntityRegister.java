package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.spells.entities.bases.EntityStaffProjectile;
import com.robertx22.mine_and_slash.spells.entities.bases.aoe.EntityAcidExplosion;
import com.robertx22.mine_and_slash.spells.entities.bases.aoe.EntityFlameExplosion;
import com.robertx22.mine_and_slash.spells.entities.bases.aoe.EntityFrostExplosion;
import com.robertx22.mine_and_slash.spells.entities.bases.aoe.EntityLightningExplosion;
import com.robertx22.mine_and_slash.spells.entities.bases.bomb.EntityAcidBomb;
import com.robertx22.mine_and_slash.spells.entities.bases.bomb.EntityFireBomb;
import com.robertx22.mine_and_slash.spells.entities.bases.bomb.EntityIceBomb;
import com.robertx22.mine_and_slash.spells.entities.bases.bomb.EntityThunderBomb;
import com.robertx22.mine_and_slash.spells.entities.bases.proj.EntityAcidBolt;
import com.robertx22.mine_and_slash.spells.entities.bases.proj.EntityFireBolt;
import com.robertx22.mine_and_slash.spells.entities.bases.proj.EntityFrostBolt;
import com.robertx22.mine_and_slash.spells.entities.bases.proj.EntityThunderBolt;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegister {

    public static List<EntityType<? extends Entity>> ENTITY_TYPES = new LinkedList();

    @SubscribeEvent
    public static void registerEntityTypes(
            final RegistryEvent.Register<EntityType<?>> event) {

        ENTITY_TYPES.forEach(entityType -> event.getRegistry().register(entityType));

    }

    public static final EntityType<? extends Entity> FIREBOLT;
    public static final EntityType<? extends Entity> FROSTBOLT;
    public static final EntityType<? extends Entity> ACIDBOLT;
    public static final EntityType<? extends Entity> THUNDERBOLT;

    public static final EntityType<? extends Entity> FIREBOMB;
    public static final EntityType<? extends Entity> FROSTBOMB;
    public static final EntityType<? extends Entity> ACIDBOMB;
    public static final EntityType<? extends Entity> THUNDERBOMB;

    public static final EntityType<? extends Entity> FIREEXPLOSION;
    public static final EntityType<? extends Entity> FROSTEXPLOSION;
    public static final EntityType<? extends Entity> ACIDEXPLOSION;
    public static final EntityType<? extends Entity> THUNDEREXPLOSION;

    public static final EntityType<? extends Entity> STAFFPROJECTILE;

    static {

        FIREBOLT = newType(EntityFireBolt.class, EntityFireBolt::new, EntityFireBolt::new, "entity_fire_bolt");
        FROSTBOLT = newType(EntityFrostBolt.class, EntityFrostBolt::new, EntityFrostBolt::new, "entity_frost_bolt");
        ACIDBOLT = newType(EntityAcidBolt.class, EntityAcidBolt::new, EntityAcidBolt::new, "entity_acid_bolt");
        THUNDERBOLT = newType(EntityThunderBolt.class, EntityThunderBolt::new, EntityThunderBolt::new, "entity_thunder_bolt");

        FIREEXPLOSION = newType(EntityFlameExplosion.class, EntityFlameExplosion::new, EntityFlameExplosion::new, "entity_flame_explosion");
        FROSTEXPLOSION = newType(EntityFrostExplosion.class, EntityFrostExplosion::new, EntityFrostExplosion::new, "entity_frost_explosion");
        ACIDEXPLOSION = newType(EntityAcidExplosion.class, EntityAcidExplosion::new, EntityAcidExplosion::new, "entity_acid_explosion");
        THUNDEREXPLOSION = newType(EntityLightningExplosion.class, EntityLightningExplosion::new, EntityLightningExplosion::new, "entity_lightning_explosion");

        FIREBOMB = newType(EntityFireBomb.class, EntityFireBomb::new, EntityFireBomb::new, "entity_fire_bomb");
        FROSTBOMB = newType(EntityIceBomb.class, EntityIceBomb::new, EntityIceBomb::new, "entity_ice_bomb");
        ACIDBOMB = newType(EntityAcidBomb.class, EntityAcidBomb::new, EntityAcidBomb::new, "entity_acid_bomb");
        THUNDERBOMB = newType(EntityThunderBomb.class, EntityThunderBomb::new, EntityThunderBomb::new, "entity_thunder_bomb");

        STAFFPROJECTILE = newType(EntityStaffProjectile.class, EntityStaffProjectile::new, EntityStaffProjectile::new, "staff_projectile");

    }

    private static <T extends Entity> EntityType<T> newType(
            Class<? extends T> entityClass, EntityType.IFactory<T> factory,
            BiFunction<FMLPlayMessages.SpawnEntity, World, T> bif, String id) {

        EntityType<T> type = EntityType.Builder.<T>create(factory, EntityClassification.MISC)
                .setCustomClientFactory(bif)
                .size(0.25F, 0.25F)
                .build(Ref.MODID + ":" + id.toLowerCase());

        type.setRegistryName(new ResourceLocation(Ref.MODID, id.toLowerCase()));

        ENTITY_TYPES.add(type);

        return type;
    }

}


