package liceobuonarroti.appsopravivenza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainToolsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tools);
    }


    public void goToCompass(View v){
        Intent i = new Intent(MainToolsActivity.this, CompassActivity.class);
        startActivity(i);
    }
}
