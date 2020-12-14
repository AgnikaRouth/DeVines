package devines.com.devines20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class HomePage1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DashboardFragment.OnFragmentItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    Toolbar toolbar;

    ViewPager pager;
    TabLayout mTabLayout;
    TabItem firstItem, secondItem;
    PagerAdapter adapter;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private FirebaseAuth mAuth;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page1);

        // Set a Toolbar to replace the ActionBar.
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Find our drawer view
        drawerLayout = findViewById(R.id.drawer);
        // Setup drawer view
        navigationView = findViewById(R.id.nav_view);
        //setupDrawerContent(navigationView) for left menu;
        navigationView.setNavigationItemSelectedListener(this);

        //open navigation drawer when clicked on hamburger sign
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle); // for the navigation drawer animation
        toggle.setDrawerIndicatorEnabled(true); //enable hamburger sign
        toggle.syncState(); //to track if the drawer is open or close


        //mAuth authentication
        mAuth = FirebaseAuth.getInstance();

        //tab assignments
        pager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tablayout);
        firstItem = findViewById(R.id.firstitem);
        secondItem = findViewById(R.id.seconditem);

        adapter = new PagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                mTabLayout.getTabCount());
        pager.setAdapter(adapter);

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
                                startActivity(new Intent(getApplicationContext(), HomePage1.class));
                                overridePendingTransition(0,0);
                                return true;

                            case R.id.nav_map :
                                startActivity(new Intent(getApplicationContext(), MapActivity.class));
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

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    //left menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        if (item.getItemId() == R.id.nav_settings) {
            startActivity(new Intent(this, Settings.class));
        }
        if (item.getItemId() == R.id.nav_contacts) {
            startActivity(new Intent(this, Contacts.class));
        }
        if (item.getItemId() == R.id.nav_feedback) {
            startActivity(new Intent(this, Feedback.class));
        }
        if (item.getItemId() == R.id.nav_help) {
            startActivity(new Intent(this, Help.class));
        }
        if(item.getItemId() == R.id.nav_logout)
        {
            Logout();
        }
        return true; //false : no item was selected even though the action was triggered

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START); //to close drawer on the right side of the screen : .END

        } else {
            super.onBackPressed();
        }

    }



    @Override
    public void onButtonSelected() {
    }

    //Logout Activity
    private void Logout()
    {
        mAuth.signOut();
        finish();
        startActivity(new Intent(HomePage1.this, Reg_Login.class));
    }
}