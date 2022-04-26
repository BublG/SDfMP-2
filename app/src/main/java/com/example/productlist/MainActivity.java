package com.example.productlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.example.productlist.databinding.ActivityMainBinding;
import com.example.productlist.db.AppDatabase;
import com.example.productlist.db.FishDao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public static FishDao fishDao;
    public static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "fish-database").allowMainThreadQueries().build();
        fishDao = db.fishDao();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                goToAbout();
                return true;
            case R.id.results:
                goToResults();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToAbout() {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        MainActivity.this.startActivity(intent);
    }

    private void goToResults() {
        Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
        MainActivity.this.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.clearAllTables();
    }
}