package com.info.infomila.david.billarapp.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.info.infomila.david.billarapp.R;
import com.info.infomila.david.billarapp.fragments.EstadistiquesFragment;
import com.info.infomila.david.billarapp.fragments.TornejosObertsFragment;
import com.info.infomila.david.billarapp.fragments.TornejosOnParticipoFragment;

import java.util.ArrayList;
import java.util.List;

import info.infomila.billar.models.Soci;

public class MainActivity extends AppCompatActivity {

    public static final String SOCI = "Soci";
    public static final String SESSION_ID = "session_id";

    public Soci soci;
    private String sessionId;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras() != null) {
            soci = (Soci) getIntent().getExtras().get(SOCI);
            sessionId = (String) getIntent().getExtras().get(SESSION_ID);
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra(MainActivity.SOCI, soci);
            intent.putExtra(MainActivity.SESSION_ID, sessionId);
            this.startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        soci = (Soci) data.getExtras().get(SOCI);
        sessionId = (String) data.getExtras().get(SESSION_ID);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(EstadistiquesFragment.newInstance(sessionId, soci), "ESTADISTIQUES");
        adapter.addFragment(TornejosObertsFragment.newInstance(sessionId, soci), "TORNEJOS");
        adapter.addFragment(TornejosOnParticipoFragment.newInstance(sessionId, soci), "TORNEJOS ON PARTICIPO");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
