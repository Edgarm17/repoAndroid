package com.simarro.practica9edgargarcia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.menu_op1:
                fragment = FragmentOpc1.newInstance();
                break;
            case R.id.menu_op2:
                fragment = FragmentOpc2.newInstance();
                break;
            case R.id.menu_op3:
                fragment = FragmentOpc3.newInstance();
                break;
            case R.id.menu_subopcion_1:
                fragment = FragmentOpc4.newInstance();
                break;
            case R.id.menu_subopcion_2:
                fragment = FragmentOpc5.newInstance();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();

        if (navigationView.getCheckedItem() != null){
            navigationView.getCheckedItem().setChecked(false);
        }

        menuItem.setChecked(true);

        getSupportActionBar().setTitle(menuItem.getTitle());

        drawerLayout.closeDrawers();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
