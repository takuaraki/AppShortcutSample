package com.example.arakitaku.appshortcutsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Screen for sending email.
 */
public class EmailActivity extends AppCompatActivity {

    private static final String EXTRA_SEND_TO = "EXTRA_SEND_TO";

    /**
     * Create Intent for launch this activity.
     *
     * @param context Context
     * @return Intent for launch this activity
     */
    public static Intent createIntent(Context context) {
        return createIntent(context, "");
    }

    /**
     * Create Intent for launch this activity.
     *
     * @param context Context
     * @param sendTo  The person you send email
     * @return Intent for launch this activity
     */
    public static Intent createIntent(Context context, String sendTo) {
        return new Intent(context, EmailActivity.class)
                .putExtra(EXTRA_SEND_TO, sendTo);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        String sendTo = getIntent().getStringExtra(EXTRA_SEND_TO);
        EditText sendToEditText = (EditText) findViewById(R.id.sendToEditText);
        sendToEditText.setText(sendTo);
    }
}
