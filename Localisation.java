package devines.com.devines20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Localisation extends AppCompatActivity {
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localisation);

        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayShowHomeEnabled(true);


        //navListener initialization //down menu
        BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        Fragment selectedFragment = null;
                        //open the fragments ONLY when any are clicked
                        switch (item.getItemId())
                        {
                            case R.id.nav_board :
                                startActivity(new Intent(Localisation.this, HomePage1.class));
                                overridePendingTransition(0,0);
                                return true;

                            case R.id.nav_map :
                                startActivity(new Intent(Localisation.this, MapActivity.class));
                                overridePendingTransition(0,0);
                                return true;

                            case R.id.nav_results:
                                Toast.makeText(Localisation.this, "You clicked on receiver", Toast.LENGTH_SHORT).show();
                                selectedFragment = new ResultFragment();
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