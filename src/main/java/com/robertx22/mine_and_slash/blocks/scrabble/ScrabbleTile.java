package com.robertx22.mine_and_slash.blocks.scrabble;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.registers.common.TileEntityRegister;
import com.robertx22.mine_and_slash.uncommon.testing.Watch;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import org.apache.commons.lang3.tuple.ImmutablePair;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public class ScrabbleTile extends TileEntity implements ITickableTileEntity {

    public ScrabbleTile() {
        super(TileEntityRegister.SCRABBLE.get());
    }

    String letters = "";

    static char[] alphabet = {
        'a',
        'b',
        'c',
        'd',
        'e',
        'f',
        'g',
        'h',
        'i',
        'j',
        'k',
        'l',
        'm',
        'n',
        'o',
        'p',
        'q',
        'r',
        's',
        't',
        'u',
        'v',
        'w',
        'x',
        'y',
        'z'
    };
    static char[] wovels = {
        'a',
        'e',
        'i',
        'o',
        'u',
        };

    @Override
    public void tick() {
        if (!world.isRemote) {
            if (letters.isEmpty()) {
                this.letters = genRandomLetters();
            }
        }
    }

    public static String genRandomLetters() {

        String letters = "";

        Watch watch = new Watch();

        while (!areLettersSuitable(letters)) {

            int amount = RandomUtils.RandomRange(4, 12);

            letters = "";

            for (int i = 0; i < amount; i++) {
                if (RandomUtils.roll(30)) {
                    letters += alphabet[RandomUtils.RandomRange(0, alphabet.length - 1)];

                } else {
                    letters += wovels[RandomUtils.RandomRange(0, wovels.length - 1)];
                }
            }
        }

        watch.print("random word ");

        return letters;

    }

    public static boolean areLettersSuitable(String letters) {

        if (letters.length() < 3) {
            return false;
        }

        int words = 0;

        Set<ImmutablePair<String, Integer>> pairs = getPairs(letters);

        for (String x : MMORPG.WORDS) {
            if (usesAllowedLetters(x, letters)) {
                words++;
            }
        }

        return words > 5 && words < 70;
    }

    public static Set<ImmutablePair<String, Integer>> getPairs(String letters) {

        Set<ImmutablePair<String, Integer>> pairs = new HashSet<>();

        for (int i = 0; i < letters.length(); i++) {
            String letter = String.valueOf(letters
                .charAt(i));
            int count = countLetter(letters, letter);
            pairs.add(ImmutablePair.of(letter, count));

        }

        return pairs;
    }

    public static boolean usesAllowedLetters(String word, String letters) {

        Set<ImmutablePair<String, Integer>> pairs = getPairs(letters);

        for (int i = 0; i < word.length(); i++) {
            String letter = String.valueOf(word.charAt(i));

            if (!letters
                .contains(letter)) {
                return false;
            }
        }

        for (ImmutablePair<String, Integer> pair : pairs) {
            int count = countLetter(word, pair.left);

            if (count > pair.right) {
                return false;
            }
        }

        return true;

    }

    public static int countLetter(String string, String letter) {
        int count = 0;

        for (int c = 0; c < string.length(); c++) {
            String l = String.valueOf(string.charAt(c));
            if (l.equals(letter)) {
                count++;
            }
        }

        return count;
    }

    public static boolean isInDict(String word) {
        return MMORPG.WORDS.contains(word);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);

        nbt.putString("letters", this.letters);
        return nbt;
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);

        this.letters = nbt.getString("letters");
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT updateTagDescribingTileEntityState = getUpdateTag();
        final int METADATA = 0;
        return new SUpdateTileEntityPacket(this.pos, METADATA, updateTagDescribingTileEntityState);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT updateTagDescribingTileEntityState = pkt.getNbtCompound();
        handleUpdateTag(updateTagDescribingTileEntityState);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        write(nbtTagCompound);
        return nbtTagCompound;
    }

}
