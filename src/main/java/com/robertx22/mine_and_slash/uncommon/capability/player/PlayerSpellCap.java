package com.robertx22.mine_and_slash.uncommon.capability.player;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.saveclasses.spells.AllocatedAbilitiesData;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCastingData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonPlayerCap;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
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

    public abstract static class ISpellsCap implements ICommonPlayerCap {
        public abstract int getAllowedPoints(EntityCap.UnitData data);

        public abstract void addPoint(IAbility ability);

        public abstract void applyStats(EntityCap.UnitData data, PlayerEntity player);

        public abstract AllocatedAbilitiesData getAbilitiesData();

        public abstract BaseSpell getSpellByKeybind(int key, SpellCastingData.Hotbar bar);

        public abstract SpellCastingData getCastingData();

        public abstract boolean canCastRightClickSpell(BaseSpell spell, PlayerEntity player);

        public abstract List<BaseSpell> getAvailableSpells();

        public abstract int getLevelOf(IAbility ability);

        public abstract boolean hasSynergy(Synergy synergy);

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

        AllocatedAbilitiesData abilitiesData = new AllocatedAbilitiesData();
        SpellCastingData spellCastingData = new SpellCastingData();

        public void tryRemovePoint(IAbility ability, ServerPlayerEntity player) {
            this.abilitiesData.removePoint(ability);
            this.getCastingData()
                .clear();
            this.syncToClient(player);

        }

        @Override
        public CompoundNBT saveToNBT() {
            CompoundNBT nbt = new CompoundNBT();

            try {
                LoadSave.Save(abilitiesData, nbt, SPELL_PERK_DATA);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                LoadSave.Save(spellCastingData, nbt, PLAYER_SPELL_DATA);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return nbt;
        }

        @Override
        public PlayerCaps getCapType() {
            return PlayerCaps.SPELLS;
        }

        @Override
        public void loadFromNBT(CompoundNBT nbt) {
            this.abilitiesData = LoadSave.Load(AllocatedAbilitiesData.class, new AllocatedAbilitiesData(), nbt, SPELL_PERK_DATA);

            if (abilitiesData == null) {
                abilitiesData = new AllocatedAbilitiesData();
            }

            this.spellCastingData = LoadSave.Load(
                SpellCastingData.class, new SpellCastingData(), nbt, PLAYER_SPELL_DATA);

            if (spellCastingData == null) {
                spellCastingData = new SpellCastingData();
            }
        }

        @Override
        public int getAllowedPoints(EntityCap.UnitData data) {

            int perlvl = (int) ((float) ModConfig.INSTANCE.Server.SPELL_POINTS_AT_MAX_LEVEL.get() / (float) ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get() * data.getLevel());

            int starting = ModConfig.INSTANCE.Server.STARTING_SPELL_POINTS.get();

            return starting + perlvl;

        }

        @Override
        public void addPoint(IAbility ability) {
            this.getAbilitiesData()
                .addPoint(ability);
        }

        @Override
        public void applyStats(EntityCap.UnitData data, PlayerEntity player) {
            this.abilitiesData.applyStats(data);
        }

        @Override
        public AllocatedAbilitiesData getAbilitiesData() {
            return abilitiesData;
        }

        @Override
        public BaseSpell getSpellByKeybind(int key, SpellCastingData.Hotbar hotbar) {
            return this.spellCastingData.getSpellByKeybind(key, hotbar);
        }

        @Override
        public SpellCastingData getCastingData() {
            return this.spellCastingData;
        }

        @Override
        public boolean canCastRightClickSpell(BaseSpell spell, PlayerEntity player) {

            if (getAbilitiesData().getLevelOf(spell) < 1) {
                return false;
            }

            return this.getCastingData()
                .canCast(spell, player);

        }

        @Override
        public List<BaseSpell> getAvailableSpells() {
            return this.abilitiesData.getAllocatedSpells();
        }

        @Override
        public int getLevelOf(IAbility ability) {
            return this.getAbilitiesData()
                .getLevelOf(ability);
        }

        @Override
        public boolean hasSynergy(Synergy synergy) {
            return getLevelOf(synergy) > 0;
        }

        @Override
        public void reset() {

            this.getAbilitiesData()
                .reset();

            this.getCastingData()
                .clear();

        }

    }

    public static class Storage extends BaseStorage<ISpellsCap> {

    }

}
