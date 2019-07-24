package com.robertx22.mine_and_slash.onevent.world;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.world_providers.BaseWorldProvider;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Objects;

public class OnStartResetMaps {

    public static void OnStartResetMaps() {

        if (ModConfig.INSTANCE.Server.RESET_MAP_DIMENSIONS_ON_LOAD.get()) {

            File file = new File(FMLPaths.GAMEDIR.get().toAbsolutePath().toString());
            for (File dir : Objects.requireNonNull(FileUtils.listFilesAndDirs(file, new NoFilter(), new NoFilter()))) {

                try {

                    if (dirMatches(dir)) {
                        System.out.println("Deleting Resettable Map Folder from Mine and Slash");
                        deleteDirectoryRecursion(dir.toPath());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    static class NoFilter implements IOFileFilter {

        @Override
        public boolean accept(File file) {
            return true;

        }

        @Override
        public boolean accept(File dir, String name) {
            return true;

        }
    }

    private static boolean dirMatches(File file) {
        String str = file.toPath().toString();

        if (file.isDirectory()) {

            if (str.contains(BaseWorldProvider.RESETTABLE)) {

                if (str.contains(Ref.MODID)) {
                    return true;
                }
            }

        }
        return false;

    }

    static void deleteDirectoryRecursion(Path path) throws IOException {
        if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
                for (Path entry : entries) {
                    deleteDirectoryRecursion(entry);
                }
            }
        }
        Files.delete(path);

    }

}
