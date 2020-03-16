/**
 * Riccardo Cestaro 2020-2-15
 */

package com.example.virtualizationdetection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MainTest().getStarted();
    }

}
