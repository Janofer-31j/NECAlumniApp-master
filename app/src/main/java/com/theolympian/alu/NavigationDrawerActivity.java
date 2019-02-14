package com.theolympian.alu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.theolympian.alu.alumnimembers.AlumniMembersActivity;
import com.theolympian.alu.profiles.ProfileActivity;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    protected DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.open, R.string.close);
        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();
        if(getSupportActionBar() != null)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id == R.id.menu_home) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }
        if(id == R.id.menu_news){
            startActivity(new Intent(getApplicationContext(), NewsActivity.class));
        }
        if(id == R.id.menu_job){
            startActivity(new Intent(getApplicationContext(), JobActivity.class));
        }
        if(id == R.id.menu_events){
            startActivity(new Intent(getApplicationContext(), EventsActivity.class));
        }
        if(id == R.id.menu_AlumniMembers){
            startActivity(new Intent(getApplicationContext(), AlumniMembersActivity.class));
        }
        if(id == R.id.menu_gallery){
            startActivity(new Intent(getApplicationContext(),GalleryActivity.class));
        }
        if(id == R.id.menu_profile){
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
        if(id == R.id.menu_logout){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        return false;
    }
}