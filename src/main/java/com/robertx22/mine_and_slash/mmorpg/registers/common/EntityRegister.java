package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.database.spells.entities.cloud.ArrowStormEntity;
import com.robertx22.mine_and_slash.database.spells.entities.cloud.BlizzardEntity;
import com.robertx22.mine_and_slash.database.spells.entities.cloud.ThunderstormEntity;
import com.robertx22.mine_and_slash.database.spells.entities.cloud.VolcanoEntity;
import com.robertx22.mine_and_slash.database.spells.entities.proj.*;
import com.robertx22.mine_and_slash.database.spells.entities.trident.ThunderspearEntity;
import com.robertx22.mine_and_slash.database.spells.entities.weapon_proj.EntityStaffProjectile;
import com.robertx22.mine_and_slash.database.spells.entities.weapon_proj.EntityWandProjectile;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.function.BiFunction;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegister {

    public static List<EntityType<? extends Entity>> ENTITY_TYPES = new LinkedList();
    public static List<EntityType<? extends Entity>> ENTITY_THAT_USE_ITEM_RENDERS = new LinkedList();

    @SubscribeEvent
    public static void registerEntityTypes(final RegistryEvent.Register<EntityType<?>> event) {

        ENTITY_TYPES.forEach(entityType -> event.getRegistry()
            .register(entityType));

    }

    public static final EntityType<? extends Entity> THUNDERSTORM;
    public static final EntityType<? extends TridentEntity> THUNDER_SPEAR;
    public static final EntityType<? extends Entity> LIGHTNING_TOTEM;

    public static final EntityType<? extends Entity> FIREBOLT;
    public static final EntityType<? extends Entity> VOLCANO;

    public static final EntityType<? extends Entity> FROSTBOLT;
    public static final EntityType<? extends Entity> WHIRPOOL;
    public static final EntityType<? extends Entity> BLIZZARD;
    public static final EntityType<? extends Entity> GEYSER;

    public static final EntityType<RangerArrowEntity> RANGER_ARROW;
    public static final EntityType<? extends Entity> ARROW_STORM;

    public static final EntityType<MagicMissileEntity> MAGIC_MISSILE;

    public static final EntityType<? extends Entity> STAFFPROJECTILE;
    public static final EntityType<? extends Entity> WANDPROJECTILE;

    public static final EntityType<? extends Entity> DIVINE_TRIBULATION;

    public static final EntityType<? extends Entity> SEED;

    static {

        MAGIC_MISSILE = newType(MagicMissileEntity::new, MagicMissileEntity::new, "magic_missile", false);

        BLIZZARD = newType(BlizzardEntity::new, BlizzardEntity::new, "blizzard");
        FROSTBOLT = newType(FrostballEntity::new, FrostballEntity::new, "frostball");
        WHIRPOOL = newType(WhirlpoolEntity::new, WhirlpoolEntity::new, "whirlpool");
        GEYSER = newType(GeyserEntity::new, GeyserEntity::new, "geyser");

        THUNDERSTORM = newType(ThunderstormEntity::new, ThunderstormEntity::new, "thunderstorm");
        THUNDER_SPEAR = newType(ThunderspearEntity::new, ThunderspearEntity::new, "thunder_spear", false);
        LIGHTNING_TOTEM = newType(LightningTotemEntity::new, LightningTotemEntity::new, "lightning_totem");

        FIREBOLT = newType(FireballEntity::new, FireballEntity::new, "fireball");
        VOLCANO = newType(VolcanoEntity::new, VolcanoEntity::new, "volcano");

        STAFFPROJECTILE = newType(EntityStaffProjectile::new, EntityStaffProjectile::new, "staff_projectile");
        WANDPROJECTILE = newType(EntityWandProjectile::new, EntityWandProjectile::new, "wand_projectile");

        RANGER_ARROW = newType(RangerArrowEntity::new, RangerArrowEntity::new, "ranger_arrow");
        ARROW_STORM = newType(ArrowStormEntity::new, ArrowStormEntity::new, "arrow_storm");

        DIVINE_TRIBULATION = newType(DivineTribulationEntity::new, DivineTribulationEntity::new, "divine_tribulation");

        SEED = newType(SeedEntity::new, SeedEntity::new, "seed_entity");
    }

    private static <T extends Entity> EntityType<T> newType(EntityType.IFactory<T> factory,
                                                            BiFunction<FMLPlayMessages.SpawnEntity, World, T> bif,
                                                            String id) {

        return newType(factory, bif, id, true);

    }

    private static <T extends Entity> EntityType<T> newType(EntityType.IFactory<T> factory,
                                                            BiFunction<FMLPlayMessages.SpawnEntity, World, T> bif,
                                                            String id, boolean itemRender) {

        EntityType<T> type = EntityType.Builder.<T>create(factory, EntityClassification.MISC).setCustomClientFactory(
            bif)
            .size(0.5F, 0.5F)
            .build(Ref.MODID + ":" + id.toLowerCase(Locale.ROOT));

        type.setRegistryName(new ResourceLocation(Ref.MODID, id.toLowerCase(Locale.ROOT)));

        ENTITY_TYPES.add(type);

        if (itemRender) {
            ENTITY_THAT_USE_ITEM_RENDERS.add(type);
        }

        return type;
    }

}


