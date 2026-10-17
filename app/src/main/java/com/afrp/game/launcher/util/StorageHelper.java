package com.afrp.game.launcher.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class StorageHelper {
    private static final String TAG = "StorageHelper";
    public static final String GTA_FOLDER_NAME = "GTA";

    private StorageHelper() {
    }

    /**
     * Retourne le dossier du jeu GTA (/storage/emulated/0/GTA ou /sdcard/GTA).
     * Correspond a la structure standard de Kuzia14 permettant aux joueurs de deposer
     * manuellement le modpack NOVA AFRP directement dans ce repertoire.
     */
    public static File getGtaDirectory(Context context) {
        File gtaDir = new File(Environment.getExternalStorageDirectory(), GTA_FOLDER_NAME);
        if (!gtaDir.exists()) {
            try {
                gtaDir.mkdirs();
            } catch (Exception ignored) {
            }
        }
        return gtaDir;
    }

    public static String getGtaDirectoryPath(Context context) {
        return getGtaDirectory(context).getAbsolutePath();
    }

    public static File getSettingsFile(Context context) {
        return new File(getGtaDirectory(context), "SAMP/settings.ini");
    }

    public static File getSettingsJsonFile(Context context) {
        return new File(getGtaDirectory(context), "SAMP/settings.json");
    }

    /**
     * Synchronise le fichier SAMP/settings.json pour assurer la compatibilite complete
     * avec les moteurs natifs lisant settings.json ou settings.ini.
     */
    public static void syncSettingsJson(Context context, String host, int port, String nickname) {
        try {
            File jsonFile = getSettingsJsonFile(context);
            File parent = jsonFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            JSONObject root;
            if (jsonFile.exists()) {
                StringBuilder builder = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line).append("\n");
                    }
                }
                String content = builder.toString().trim();
                root = content.startsWith("{") ? new JSONObject(content) : new JSONObject();
            } else {
                root = new JSONObject();
            }

            JSONObject client = root.optJSONObject("client");
            if (client == null) {
                client = new JSONObject();
                root.put("client", client);
            }

            JSONObject server = client.optJSONObject("server");
            if (server == null) {
                server = new JSONObject();
                client.put("server", server);
            }

            if (!TextUtils.isEmpty(host)) {
                server.put("host", host);
            }
            if (port > 0) {
                server.put("port", port);
            }

            if (!TextUtils.isEmpty(nickname)) {
                JSONObject settings = client.optJSONObject("settings");
                if (settings == null) {
                    settings = new JSONObject();
                    client.put("settings", settings);
                }
                settings.put("nick_name", nickname);
            }

            try (FileWriter writer = new FileWriter(jsonFile)) {
                writer.write(root.toString(4));
                writer.flush();
            }
        } catch (Exception e) {
            Log.w(TAG, "Erreur synchronisation settings.json: " + e.getMessage());
        }
    }
}
