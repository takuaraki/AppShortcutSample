package com.example.arakitaku.appshortcutsample;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    /**
     * Create Intent for launch this activity.
     *
     * @param context Context
     * @return Intent for launch this activity
     */
    public static Intent createIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(EmailActivity.createIntent(MainActivity.this));
            }
        });

        if (Build.VERSION.SDK_INT >= 25) {
            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
            ShortcutInfo ara_tack = new ShortcutInfo.Builder(this, "ara_tack")
                    .setShortLabel("ara_tack")
                    .setLongLabel("mail to ara_tack")
                    .setIcon(Icon.createWithResource(this, R.mipmap.ic_create_email_a))
                    .setIntent(EmailActivity.createIntent(this, "ara_tack").setAction(Intent.ACTION_VIEW))
                    .build();

            ShortcutInfo hashido = new ShortcutInfo.Builder(this, "hashido")
                    .setShortLabel("hashido")
                    .setLongLabel("mail to hashido")
                    .setIcon(Icon.createWithResource(this, R.mipmap.ic_create_email_h))
                    .setIntent(EmailActivity.createIntent(this, "hashido").setAction(Intent.ACTION_VIEW))
                    .build();

            ShortcutInfo n_morioka = new ShortcutInfo.Builder(this, "n_morioka")
                    .setShortLabel("n_morioka")
                    .setLongLabel("mail to n_morioka")
                    .setIcon(Icon.createWithResource(this, R.mipmap.ic_create_email_n))
                    // Multiple Intent can be set
                    .setIntents(new Intent[]{
                            MainActivity.createIntent(this)
                                    .setAction(Intent.ACTION_VIEW),
                            EmailActivity.createIntent(this, "n_morioka")
                                    .setAction(Intent.ACTION_VIEW)
                    })
                    .build();

            shortcutManager.setDynamicShortcuts(Arrays.asList(ara_tack, hashido, n_morioka));

            // shortcutManager.getMaxShortcutCountPerActivity() equals 5
            Log.d("onCreate", "max: " + shortcutManager.getMaxShortcutCountPerActivity());
            boolean exceedMaxShortCut = false;
            if (exceedMaxShortCut) {
                ShortcutInfo ara_tack2 = new ShortcutInfo.Builder(this, "ara_tack2")
                        .setShortLabel("ara_tack2")
                        .setLongLabel("mail to ara_tack2")
                        .setIcon(Icon.createWithResource(this, R.mipmap.ic_create_email_a))
                        .setIntent(EmailActivity.createIntent(this, "ara_tack2").setAction(Intent.ACTION_VIEW))
                        .build();

                ShortcutInfo ara_tack3 = new ShortcutInfo.Builder(this, "ara_tack3")
                        .setShortLabel("ara_tack3")
                        .setLongLabel("mail to ara_tack3")
                        .setIcon(Icon.createWithResource(this, R.mipmap.ic_create_email_a))
                        .setIntent(EmailActivity.createIntent(this, "ara_tack3").setAction(Intent.ACTION_VIEW))
                        .build();

                // IllegalArgumentException is thrown because getMaxShortcutCountPerActivity() is exceeded.
                // 6 shortcuts: 1 static shortcut and 5 dynamic shortcuts.
                shortcutManager.setDynamicShortcuts(Arrays.asList(ara_tack, hashido, n_morioka, ara_tack2, ara_tack3));
            }
        }

    }
}
