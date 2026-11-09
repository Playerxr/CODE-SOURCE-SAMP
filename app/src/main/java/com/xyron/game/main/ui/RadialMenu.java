package com.xyron.game.main.ui;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.xyron.game.R;
import com.xyron.game.launcher.util.Util;

import com.xyron.game.main.SAMP;

import java.nio.charset.StandardCharsets;

public class RadialMenu {
    private static final int RADIAL_ICON_COLOR = 0xFFFFFFFF;
    private static final int RADIAL_TEXT_COLOR = 0xFFE0E0E0;
    private static final int RADIAL_BACKDROP_COLOR = 0xCC181818;
    private static final int RADIAL_TEXT_SHADOW = 0x80000000;
    private ConstraintLayout radialLayout;
    private Activity activity;
    public static boolean menuVisible;
    private RadialVehicles mRadialVehicles;

    native void sendCommand(byte[] str);

    public RadialMenu(Activity activity) {
        this.activity = activity;

        ConstraintLayout layout = (ConstraintLayout) activity.getLayoutInflater().inflate(R.layout.radial, null);
        activity.addContentView(layout, new ConstraintLayout.LayoutParams(-1, -1));

        radialLayout = activity.findViewById(R.id.radial);
        radialLayout.setVisibility(View.GONE);
        applyTheme();

        setListeners();
        menuVisible = false;

        Util.HideLayout(radialLayout, false);
    }

    private void applyTheme() {
        Typeface labelTypeface = ResourcesCompat.getFont(activity, R.font.montserrat_regular);
        int[] iconIds = {
                R.id.radial_image_00,
                R.id.radial_image_01,
                R.id.radial_image_02,
                R.id.radial_image_03,
                R.id.radial_image_04,
                R.id.radial_image_05,
                R.id.radial_image_06,
                R.id.radial_image_07,
                R.id.radial_image_08,
                R.id.radial_image_09
        };
        int[] labelIds = {
                R.id.radial_text_00,
                R.id.radial_text_01,
                R.id.radial_text_02,
                R.id.radial_text_03,
                R.id.radial_text_04,
                R.id.radial_text_05,
                R.id.radial_text_06,
                R.id.radial_text_07,
                R.id.radial_text_08,
                R.id.radial_text_09
        };

        radialLayout.setBackgroundColor(RADIAL_BACKDROP_COLOR);

        ImageView closeIcon = activity.findViewById(R.id.imageView31);
        if (closeIcon != null) {
            closeIcon.setImageTintList(ColorStateList.valueOf(RADIAL_TEXT_COLOR));
        }

        for (int iconId : iconIds) {
            ImageView icon = activity.findViewById(iconId);
            if (icon != null) {
                icon.setImageTintList(ColorStateList.valueOf(RADIAL_ICON_COLOR));
            }
        }

        for (int labelId : labelIds) {
            TextView label = activity.findViewById(labelId);
            if (label != null) {
                label.setTextColor(RADIAL_TEXT_COLOR);
                label.setIncludeFontPadding(false);
                label.setSingleLine(true);
                label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 9.25f);
                label.setShadowLayer(10f, 0f, 0f, RADIAL_TEXT_SHADOW);
                if (labelTypeface != null) {
                    label.setTypeface(labelTypeface);
                }
            }
        }
    }

    private void setListeners()
    {
        View closeBtn = activity.findViewById(R.id.radial_close);
        if (closeBtn != null) {
            closeBtn.setOnClickListener(view -> hide());
        }

        setRadialButtonClick(R.id.radial_button_00, 0);
        setRadialButtonClick(R.id.radial_button_01, 1);
        setRadialButtonClick(R.id.radial_button_02, 2);
        setRadialButtonClick(R.id.radial_button_03, 3);
        setRadialButtonClick(R.id.radial_button_04, 4);
        setRadialButtonClick(R.id.radial_button_05, 5);
        setRadialButtonClick(R.id.radial_button_06, 6);
        setRadialButtonClick(R.id.radial_button_07, 7);
        setRadialButtonClick(R.id.radial_button_08, 8);
        setRadialButtonClick(R.id.radial_button_09, 9);
    }

    private void setRadialButtonClick(int buttonId, int actionId)
    {
        View button = activity.findViewById(buttonId);
        if (button == null) {
            return;
        }

        button.setOnClickListener(view -> {
            if (actionId == 0) // Téléphone
            {
                hide();
                if (activity instanceof SAMP) {
                    ((SAMP) activity).showPhoneOverlay();
                    return;
                }
                sendCommand("/c".getBytes(StandardCharsets.UTF_8));
            }
            else if (actionId == 1) // GPS
            {
                hide();
                sendCommand("/gps".getBytes(StandardCharsets.UTF_8));
            }
            else if (actionId == 2) // Sac à dos / Inventaire
            {
                hide();
                if (activity instanceof SAMP) {
                    ((SAMP) activity).showInventoryOverlay();
                    return;
                }
                sendCommand("/m".getBytes(StandardCharsets.UTF_8));
            }
            else if (actionId == 3) // Position / Localisation
            {
                hide();
                sendCommand("/eloc".getBytes(StandardCharsets.UTF_8));
            }
            else if (actionId == 4) // Propriétés
            {
                hide();
                sendCommand("/casa".getBytes(StandardCharsets.UTF_8));
            }
            else if (actionId == 5) // Aide
            {
                hide();
                sendCommand("/ajuda".getBytes(StandardCharsets.UTF_8));
            }
            else if (actionId == 6) // Boutique VIP
            {
                hide();
                sendCommand("/loja".getBytes(StandardCharsets.UTF_8));
            }
            else if (actionId == 7) // Assistance / ATM
            {
                hide();
                sendCommand("/atm".getBytes(StandardCharsets.UTF_8));
            }
            else if (actionId == 8) // Animations / Émotes
            {
                hide();
                sendCommand("/anim".getBytes(StandardCharsets.UTF_8));
            }
            else if (actionId == 9) // Identité
            {
                hide();
                sendCommand("/doc".getBytes(StandardCharsets.UTF_8));
            }
        });
    }

    public void show() {
        if (!menuVisible && !RadialVehicles.menuVisibleV) {
            Util.ShowLayout(radialLayout, true);
            menuVisible = true;
        }
    }

    public void hide() {
        if (menuVisible) {
            Util.HideLayout(radialLayout, true);
            menuVisible = false;
        }
    }
}
