package com.theolympian.alu.profiles;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.theolympian.alu.R;
import com.theolympian.alu.interfaces.OnFragmentInteractionListener;
import com.theolympian.alu.profiles.profileFragments.ProfileJobFragment;
import com.theolympian.alu.profiles.profileFragments.ProfileLocationFragment;
import com.theolympian.alu.profiles.profileFragments.ProfilePersonalFragment;

public class ProfileActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private ProfilesPagerAdapter profilesPagerAdapter;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_tab_personal,
            R.drawable.ic_tab_job,
            R.drawable.ic_tab_location
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        profilesPagerAdapter  = new ProfilesPagerAdapter(getSupportFragmentManager());
        profilesPagerAdapter.addFrag(new ProfilePersonalFragment(), "Personal");
        profilesPagerAdapter.addFrag(new ProfileJobFragment(), "Job");
        profilesPagerAdapter.addFrag(new ProfileLocationFragment(), "Location");
        viewPager.setAdapter(profilesPagerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
