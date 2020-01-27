package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_tree.SpellPerk;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryContainer;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.sync_cap.CapTypes;
import com.robertx22.mine_and_slash.saveclasses.spells.PlayerSpellsData;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellPerksData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCapability;
import com.robertx22.mine_and_slash.uncommon.capability.bases.IPerkCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class PlayerSpellCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "spells");

    private static final String SPELL_PERK_DATA = "spell_perk_data";
    private static final String PLAYER_SPELL_DATA = "player_spells_data";

    @CapabilityInject(ISpellsCap.class)
    public static final Capability<ISpellsCap> Data = null;

    public abstract static class ISpellsCap extends IPerkCap<SpellPerk, SpellPerksData> implements ICommonCapability {
        public abstract BaseSpell getSpellByKeybind(int key, PlayerSpellsData.Hotbar bar);

        public abstract PlayerSpellsData getSpellData();

        public abstract List<BaseSpell> getAvailableSpells();

        public abstract boolean hasSynergy(Synergy synergy);

        @Override
        public abstract void reset();

    }

    @Mod.EventBusSubscriber
    public static class EventHandler {
        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof PlayerEntity) {
                event.addCapability(RESOURCE, new Provider());
            }
        }

    }

    public static class Provider extends BaseProvider<ISpellsCap> {

        @Override
        public ISpellsCap defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<ISpellsCap> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl extends ISpellsCap {

        SpellPerksData perksData = new SpellPerksData();
        PlayerSpellsData playerSpellsData = new PlayerSpellsData();

        @Override
        public boolean tryRemovePoint(SpellPerk perk, ServerPlayerEntity player) {

            boolean bool = super.tryRemovePoint(perk, player);

            if (bool) {
                this.getSpellData().clear();
                this.syncToClient(player);
            }

            return bool;
        }

        @Override
        public CompoundNBT getNBT() {
            CompoundNBT nbt = new CompoundNBT();
            LoadSave.Save(perksData, nbt, SPELL_PERK_DATA);
            LoadSave.Save(playerSpellsData, nbt, PLAYER_SPELL_DATA);

            return nbt;
        }

        @Override
        public CapTypes getCapType() {
            return CapTypes.SPELLS;
        }

        @Override
        public void setNBT(CompoundNBT nbt) {
            this.perksData = LoadSave.Load(SpellPerksData.class, new SpellPerksData(), nbt, SPELL_PERK_DATA);

            if (perksData == null) {
                perksData = new SpellPerksData();
            }

            this.playerSpellsData = LoadSave.Load(
                    PlayerSpellsData.class, new PlayerSpellsData(), nbt, PLAYER_SPELL_DATA);

            if (playerSpellsData == null) {
                playerSpellsData = new PlayerSpellsData();
            }
        }

        @Override
        public int getAllowedPoints(EntityCap.UnitData data) {

            int perlvl = (int) (data.getLevel() * ModConfig.INSTANCE.Server.SPELL_POINTS_PER_LEVEL.get());

            int starting = ModConfig.INSTANCE.Server.STARTING_SPELL_POINTS.get();

            return starting + perlvl;

        }

        @Override
        public void allocate(SpellPerk talent) {
            getPerksData().allocate(talent.GUID());
        }

        @Override
        public void applyStats(EntityCap.UnitData data, PlayerEntity player) {
            this.perksData.applyStats(data);
        }

        @Override
        public SpellPerksData getPerksData() {
            return perksData;
        }

        @Override
        public SlashRegistryContainer getContainer() {
            return SlashRegistry.SpellPerks();
        }

        @Override
        public BaseSpell getSpellByKeybind(int key, PlayerSpellsData.Hotbar hotbar) {
            return this.playerSpellsData.getSpellByKeybind(key, hotbar);
        }

        @Override
        public PlayerSpellsData getSpellData() {
            return this.playerSpellsData;
        }

        @Override
        public List<BaseSpell> getAvailableSpells() {
            return this.perksData.getAvailableSpells();
        }

        @Override
        public boolean hasSynergy(Synergy synergy) {
            return getPerksData().hasSynergy(synergy);
        }

        @Override
        public void reset() {

            this.getPerksData().reset();

            this.getSpellData().clear();

        }

    }

    public static class Storage extends BaseStorage<ISpellsCap> {

    }

}
