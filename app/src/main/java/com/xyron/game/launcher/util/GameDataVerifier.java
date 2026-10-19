package com.xyron.game.launcher.util;

import android.content.Context;

import java.io.File;

public final class GameDataVerifier {
    private static final String[] REQUIRED_DIRECTORIES = new String[] {
            "anim",
            "audio",
            "data",
            "models",
            "texdb"
    };

    private static final String[] REQUIRED_FILES = new String[] {
            "SAMP/main.scm",
            "models/MINFO.BIN",
            "texdb/SAMPCOL.img",
            "texdb/gta3.img",
            "texdb/gta_int.img",
            "texdb/player.img",
            "texdb/samp.img",
            "texdb/gta3/gta3.txt",
            "texdb/gta_int/gta_int.txt",
            "texdb/menu/menu.txt",
            "texdb/mobile/mobile.txt",
            "texdb/player/player.txt",
            "texdb/samp/samp.txt",
            "texdb/txd/txd.txt"
    };

    private GameDataVerifier() {
    }

    public static boolean hasRequiredGameData(Context context) {
        File gtaDir = StorageHelper.getGtaDirectory(context);
        if (checkDirectoryHasData(gtaDir)) {
            return true;
        }

        if (context != null && context.getExternalFilesDir(null) != null) {
            return checkDirectoryHasData(context.getExternalFilesDir(null));
        }

        return false;
    }

    public static boolean checkDirectoryHasData(File baseDirectory) {
        if (baseDirectory == null || !baseDirectory.exists() || !baseDirectory.isDirectory()) {
            return false;
        }

        File checkDir = baseDirectory;
        if (!new File(checkDir, "data").exists() && new File(checkDir, "files/data").exists()) {
            checkDir = new File(checkDir, "files");
        }

        for (String relativePath : REQUIRED_DIRECTORIES) {
            File directory = new File(checkDir, relativePath);
            if (!directory.exists() || !directory.isDirectory()) {
                return false;
            }
        }

        if (!hasNonEmptyFile(checkDir, "texdb/gta3.img") && !hasNonEmptyFile(checkDir, "data/gta.dat")) {
            return false;
        }

        return hasNonEmptyFile(checkDir, "SAMP/main.scm")
                || hasNonEmptyFile(checkDir, "SAMP/settings.json")
                || hasNonEmptyFile(checkDir, "SAMP/settings.ini");
    }

    private static boolean hasTextureSet(File baseDirectory, String basePath) {
        return hasTextureSet(baseDirectory, basePath, "dxt")
                || hasTextureSet(baseDirectory, basePath, "etc")
                || hasTextureSet(baseDirectory, basePath, "pvr");
    }

    private static boolean hasTextureSet(File baseDirectory, String basePath, String format) {
        return hasNonEmptyFile(baseDirectory, basePath + "." + format + ".dat")
                && hasNonEmptyFile(baseDirectory, basePath + "." + format + ".tmb")
                && hasNonEmptyFile(baseDirectory, basePath + "." + format + ".toc");
    }

    private static boolean hasNonEmptyFile(File baseDirectory, String relativePath) {
        File file = new File(baseDirectory, relativePath);
        return file.exists() && file.isFile() && file.length() > 0L;
    }
}
