package com.afrp.game.launcher.data;

import android.content.Context;
import android.graphics.Bitmap;

public class Config {

    // Serveur AFRP par defaut
    public static final String DEFAULT_SERVER_HOST = "51.38.205.167";
    public static final int DEFAULT_SERVER_PORT = 24328;
    public static final String DEFAULT_SERVER_NAME = "NOVA AFRP";

    public static Context mainContext;

    public static String responseServer;
    public static int responseInt;

    public static String mNewsDescription[], mNewsTitle[], mNewsImage[];
    public static Bitmap mBitmap[];

    public static int mServersDoubling[], mServersPing[], mServersIsNew[];
    public static String mServersOnline[];
    public static String mServersName[], mServersHost[];
    public static int mServersPort[];
}
