package com.robertx22.mine_and_slash.uncommon.capability.player;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.saveclasses.spells.AllocatedAbilitiesData;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCastingData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonPlayerCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
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

        public abstract AllocatedAbilitiesData getAbilitiesData();

        public abstract BaseSpell getCurrentRightClickSpell();

        public abstract BaseSpell getSpellByKeybind(int key, SpellCastingData.Hotbar bar);

        public abstract SpellCastingData getCastingData();

        public abstract List<BaseSpell> getAvailableSpells();

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
        public AllocatedAbilitiesData getAbilitiesData() {
            return abilitiesData;
        }

        @Override
        public BaseSpell getCurrentRightClickSpell() {
            return null; // TODO
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
        public List<BaseSpell> getAvailableSpells() {
            return this.abilitiesData.getAllocatedSpells();
        }

    }

    public static class Storage extends BaseStorage<ISpellsCap> {

    }

}
