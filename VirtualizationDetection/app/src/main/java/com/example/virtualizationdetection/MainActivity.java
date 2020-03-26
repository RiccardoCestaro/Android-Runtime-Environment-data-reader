/**
 * Riccardo Cestaro 2020-2-15
 */

package com.example.virtualizationdetection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String value = new MainTest().getStarted();

        Log.d("mainActivity",value);

        final TextView textViewToChange = (TextView) findViewById(R.id.value);
        textViewToChange.setText("dex_code_item_offset = " + value);
    }

}
