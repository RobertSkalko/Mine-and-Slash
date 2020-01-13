package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.database.spells.entities.aoe.EntityAcidExplosion;
import com.robertx22.mine_and_slash.database.spells.entities.aoe.EntityFlameExplosion;
import com.robertx22.mine_and_slash.database.spells.entities.aoe.EntityFrostExplosion;
import com.robertx22.mine_and_slash.database.spells.entities.aoe.EntityLightningExplosion;
import com.robertx22.mine_and_slash.database.spells.entities.blizzard.BlizzardCloudEntity;
import com.robertx22.mine_and_slash.database.spells.entities.bomb.EntityAcidBomb;
import com.robertx22.mine_and_slash.database.spells.entities.bomb.EntityFireBomb;
import com.robertx22.mine_and_slash.database.spells.entities.bomb.EntityIceBomb;
import com.robertx22.mine_and_slash.database.spells.entities.bomb.EntityThunderBomb;
import com.robertx22.mine_and_slash.database.spells.entities.proj.EntityAcidBolt;
import com.robertx22.mine_and_slash.database.spells.entities.proj.EntityFireBolt;
import com.robertx22.mine_and_slash.database.spells.entities.proj.EntityFrostBolt;
import com.robertx22.mine_and_slash.database.spells.entities.proj.EntityThunderBolt;
import com.robertx22.mine_and_slash.database.spells.entities.weapon_proj.EntityStaffProjectile;
import com.robertx22.mine_and_slash.database.spells.entities.weapon_proj.EntityWandProjectile;
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
    public static List<EntityType<? extends Entity>> ENTITY_THAT_USE_ITEM_RENDERS = new LinkedList();

    @SubscribeEvent
    public static void registerEntityTypes(
            final RegistryEvent.Register<EntityType<?>> event) {

        ENTITY_TYPES.forEach(entityType -> event.getRegistry().register(entityType));

    }

    public static final EntityType<? extends Entity> FROST_BLIZZARD;

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
    public static final EntityType<? extends Entity> WANDPROJECTILE;

    static {
        FROST_BLIZZARD = newType(BlizzardCloudEntity::new, BlizzardCloudEntity::new, "entity_frost_blizzard");

        FIREBOLT = newType(EntityFireBolt::new, EntityFireBolt::new, "entity_fire_bolt");
        FROSTBOLT = newType(EntityFrostBolt::new, EntityFrostBolt::new, "entity_frost_bolt");
        ACIDBOLT = newType(EntityAcidBolt::new, EntityAcidBolt::new, "entity_acid_bolt");
        THUNDERBOLT = newType(EntityThunderBolt::new, EntityThunderBolt::new, "entity_thunder_bolt");

        FIREEXPLOSION = newType(EntityFlameExplosion::new, EntityFlameExplosion::new, "entity_flame_explosion");
        FROSTEXPLOSION = newType(EntityFrostExplosion::new, EntityFrostExplosion::new, "entity_frost_explosion");
        ACIDEXPLOSION = newType(EntityAcidExplosion::new, EntityAcidExplosion::new, "entity_acid_explosion");
        THUNDEREXPLOSION = newType(EntityLightningExplosion::new, EntityLightningExplosion::new, "entity_lightning_explosion");

        FIREBOMB = newType(EntityFireBomb::new, EntityFireBomb::new, "entity_fire_bomb");
        FROSTBOMB = newType(EntityIceBomb::new, EntityIceBomb::new, "entity_ice_bomb");
        ACIDBOMB = newType(EntityAcidBomb::new, EntityAcidBomb::new, "entity_acid_bomb");
        THUNDERBOMB = newType(EntityThunderBomb::new, EntityThunderBomb::new, "entity_thunder_bomb");

        STAFFPROJECTILE = newType(EntityStaffProjectile::new, EntityStaffProjectile::new, "staff_projectile");
        WANDPROJECTILE = newType(EntityWandProjectile::new, EntityWandProjectile::new, "wand_projectile");
    }

    private static <T extends Entity> EntityType<T> newType(
            EntityType.IFactory<T> factory,
            BiFunction<FMLPlayMessages.SpawnEntity, World, T> bif, String id) {

        return newType(factory, bif, id, true);

    }

    private static <T extends Entity> EntityType<T> newType(
            EntityType.IFactory<T> factory,
            BiFunction<FMLPlayMessages.SpawnEntity, World, T> bif, String id,
            boolean itemRender) {

        EntityType<T> type = EntityType.Builder.<T>create(factory, EntityClassification.MISC)
                .setCustomClientFactory(bif)
                .size(0.5F, 0.5F)
                .build(Ref.MODID + ":" + id.toLowerCase());

        type.setRegistryName(new ResourceLocation(Ref.MODID, id.toLowerCase()));

        ENTITY_TYPES.add(type);

        if (itemRender) {
            ENTITY_THAT_USE_ITEM_RENDERS.add(type);
        }

        return type;
    }

}


