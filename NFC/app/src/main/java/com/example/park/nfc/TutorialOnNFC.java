package com.example.park.nfc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Park on 2015-12-08.
 */
public class TutorialOnNFC extends Activity {

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        setContentView(R.layout.activity_main);

        Button btag = (Button)findViewById(R.id.buttontag);
        btag.setOnClickListener(new View.OnClickListener(){
            // @Override
            public void onClick(View arg0) {
                finish();
                startActivity(new Intent(TutorialOnNFC.this, TagDispatch.class));
            }
        });

        Button bbeam = (Button)findViewById(R.id.buttonbeam);
        bbeam.setOnClickListener(new View.OnClickListener(){
            // @Override
            public void onClick(View arg0) {
                finish();
                startActivity(new Intent(TutorialOnNFC.this, BeamData.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
