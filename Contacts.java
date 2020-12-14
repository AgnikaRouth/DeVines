package devines.com.devines20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Contacts extends AppCompatActivity {
    ImageView image;
    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        image = (ImageView) findViewById(R.id.ivContacts);

        //getSupportActionBar().setTitle("CONTACTS");
       // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        //navListener initialization
        BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        Fragment selectedFragment = null;
                        //open the fragments ONLY when any are clicked
                        switch (item.getItemId())
                        {
                            case R.id.nav_map :
                                startActivity(new Intent(getApplicationContext(), MapActivity.class));
                                overridePendingTransition(0,0);
                                return true;

                            case R.id.nav_contacts :
                                startActivity(new Intent(getApplicationContext(), Contacts.class));
                                overridePendingTransition(0,0);
                                return true;

                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_bottom,
                                selectedFragment).commit(); //to display Fragments
                        return  true; //select the clicked item. If false, show fragment but item wouldn't be selected
                    }
                };


        //bottom navigation view
        bottomNavigationView = findViewById(R.id.bottom_nav_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }


}