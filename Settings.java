package devines.com.devines20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    Toolbar toolbar;
    TextView notification, localisation, cancelaccount, bluetooth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        // Set a Toolbar to replace the ActionBar.
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Réglages");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        notification =(TextView) findViewById(R.id.tvNotification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, Notification.class));
            }
        });

        localisation = (TextView) findViewById(R.id.tvLocation);
        localisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, Localisation.class));
            }
        });

        bluetooth = (TextView) findViewById(R.id.tvBluetooth);
        bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, Bluetooth.class));
            }
        });

        cancelaccount = (TextView) findViewById(R.id.tvCancel);
        cancelaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, CancelAccount.class));
            }
        });
    }
}