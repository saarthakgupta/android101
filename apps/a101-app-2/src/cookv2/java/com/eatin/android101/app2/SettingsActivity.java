package com.eatin.android101.app2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by nitin.verma on 14/06/15.
 */
public class SettingsActivity extends Activity {
    private static final Logger LOG = LoggerFactory.getLogger(SettingsActivity.class);
    private TextView textView;
    private LinearLayout root;
    private final Object lock = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        textView = (TextView) findViewById(R.id.text);
        root = (LinearLayout) findViewById(R.id.rootContainer);
        new AsyncTask<Void,Void,Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                updateTextView("You can change settings!");
                return null;
            }
        }.execute();
    }

    public void updateTextView(final String text) {
        if (textView != null) {
            if ( Looper.getMainLooper().getThread() == Thread.currentThread() ) {
                synchronized (lock) {
                    textView.setText(text);
                    root.invalidate();
                }
            }
            else {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (lock) {
                            textView.setText(text);
                            root.invalidate();
                        }
                    }
                });
            }
        }
    }
}
