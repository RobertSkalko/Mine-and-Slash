package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.database.spells.entities.cloud.ArrowStormEntity;
import com.robertx22.mine_and_slash.database.spells.entities.cloud.BlizzardEntity;
import com.robertx22.mine_and_slash.database.spells.entities.cloud.ThunderstormEntity;
import com.robertx22.mine_and_slash.database.spells.entities.cloud.VolcanoEntity;
import com.robertx22.mine_and_slash.database.spells.entities.proj.*;
import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.FireballEntity;
import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.FrostballEntity;
import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.PoisonBallEntity;
import com.robertx22.mine_and_slash.database.spells.entities.summons.SpiderPetEntity;
import com.robertx22.mine_and_slash.database.spells.entities.summons.SpiritWolfPetEntity;
import com.robertx22.mine_and_slash.database.spells.entities.trident.ThunderspearEntity;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mobs.entity.LeapingZombie;
import com.robertx22.mine_and_slash.new_content.trader.TraderEntity;
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

    public static final EntityType<? extends Entity> POISON_BALL;

    public static final EntityType<? extends Entity> FROSTBOLT;
    public static final EntityType<? extends Entity> WHIRPOOL;
    public static final EntityType<? extends Entity> BLIZZARD;
    public static final EntityType<? extends Entity> TIDAL_WAVE;

    public static final EntityType<RangerArrowEntity> RANGER_ARROW;
    public static final EntityType<? extends Entity> ARROW_STORM;

    public static final EntityType<? extends Entity> DIVINE_TRIBULATION;

    public static final EntityType<? extends Entity> SEED;

    public static final EntityType<TraderEntity> TRADER;

    public static final EntityType<LeapingZombie> LEAPING_ZOMBIE;
    public static final EntityType<SpiderPetEntity> SPIDER_PET;
    public static final EntityType<SpiritWolfPetEntity> SPIRIT_WOLF_PET;

    static {

        BLIZZARD = projectile(BlizzardEntity::new, BlizzardEntity::new, "blizzard");
        FROSTBOLT = projectile(FrostballEntity::new, FrostballEntity::new, "frostball");
        WHIRPOOL = projectile(WhirlpoolEntity::new, WhirlpoolEntity::new, "whirlpool");
        TIDAL_WAVE = projectile(TidalWaveEntity::new, TidalWaveEntity::new, "tidal_wave");

        POISON_BALL = projectile(PoisonBallEntity::new, PoisonBallEntity::new, "poison_ball");

        THUNDERSTORM = projectile(ThunderstormEntity::new, ThunderstormEntity::new, "thunderstorm");
        THUNDER_SPEAR = projectile(ThunderspearEntity::new, ThunderspearEntity::new, "thunder_spear", false);
        LIGHTNING_TOTEM = projectile(LightningTotemEntity::new, LightningTotemEntity::new, "lightning_totem");

        FIREBOLT = projectile(FireballEntity::new, FireballEntity::new, "fireball");
        VOLCANO = projectile(VolcanoEntity::new, VolcanoEntity::new, "volcano");

        RANGER_ARROW = projectile(RangerArrowEntity::new, RangerArrowEntity::new, "ranger_arrow");
        ARROW_STORM = projectile(ArrowStormEntity::new, ArrowStormEntity::new, "arrow_storm");

        DIVINE_TRIBULATION = projectile(DivineTribulationEntity::new, DivineTribulationEntity::new, "divine_tribulation");

        SEED = projectile(SeedEntity::new, SeedEntity::new, "seed_entity");

        TRADER = EntityType.Builder.<TraderEntity>create(TraderEntity::new, EntityClassification.MISC).setCustomClientFactory(
            TraderEntity::new)
            .size(0.5F, 2F)
            .build(Ref.MODID + ":trader");
        TRADER.setRegistryName(new ResourceLocation(Ref.MODID, "trader"));
        ENTITY_TYPES.add(TRADER);

        LEAPING_ZOMBIE = EntityType.Builder.<LeapingZombie>create(LeapingZombie::new, EntityClassification.MONSTER).setCustomClientFactory(
            LeapingZombie::new)
            .size(0.6F, 1.95F)
            .build(Ref.MODID + ":leaping_zombie");
        LEAPING_ZOMBIE.setRegistryName(new ResourceLocation(Ref.MODID, "leaping_zombie"));
        ENTITY_TYPES.add(LEAPING_ZOMBIE);

        SPIDER_PET = EntityType.Builder.<SpiderPetEntity>create(SpiderPetEntity::new, EntityClassification.MONSTER).setCustomClientFactory(
            SpiderPetEntity::new)
            .size(1.4F, 0.9F)
            .build(Ref.MODID + ":spider_pet");
        SPIDER_PET.setRegistryName(new ResourceLocation(Ref.MODID, "spider_pet"));
        ENTITY_TYPES.add(SPIDER_PET);

        SPIRIT_WOLF_PET = EntityType.Builder.<SpiritWolfPetEntity>create(SpiritWolfPetEntity::new, EntityClassification.MONSTER).setCustomClientFactory(
            SpiritWolfPetEntity::new)
            .size(0.6F, 0.85F)
            .build(Ref.MODID + ":spirit_wolf_pet");
        SPIRIT_WOLF_PET.setRegistryName(new ResourceLocation(Ref.MODID, "spirit_wolf_pet"));
        ENTITY_TYPES.add(SPIRIT_WOLF_PET);

    }

    private static <T extends Entity> EntityType<T> projectile(EntityType.IFactory<T> factory,
                                                               BiFunction<FMLPlayMessages.SpawnEntity, World, T> bif,
                                                               String id) {

        return projectile(factory, bif, id, true);

    }

    private static <T extends Entity> EntityType<T> projectile(EntityType.IFactory<T> factory,
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


