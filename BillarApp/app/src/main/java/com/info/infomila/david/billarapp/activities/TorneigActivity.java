package com.info.infomila.david.billarapp.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.info.infomila.david.billarapp.R;
import com.info.infomila.david.billarapp.fragments.EstadistiquesFragment;
import com.info.infomila.david.billarapp.fragments.TorneigClassificacioFragment;
import com.info.infomila.david.billarapp.fragments.TornejosObertsFragment;
import com.info.infomila.david.billarapp.fragments.TornejosOnParticipoFragment;

import java.util.ArrayList;
import java.util.List;

import info.infomila.billar.models.Soci;
import info.infomila.billar.models.Torneig;

public class TorneigActivity extends AppCompatActivity {

    public static final String SESSION_ID = "session_id";
    public static final String SOCI = "soci";
    public static final String TORNEIG = "torneig";

    private String sessionId;
    private Soci soci;
    private Torneig torneig;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneig);

        soci = (Soci) getIntent().getExtras().get(SOCI);
        sessionId = (String) getIntent().getExtras().get(SESSION_ID);
        torneig = (Torneig) getIntent().getExtras().get(TORNEIG);


        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);
        toolbar.setTitle(torneig.getNom());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(TorneigClassificacioFragment.newInstance(soci, torneig, sessionId), "CLASSIFICACIO");
        adapter.addFragment(EstadistiquesFragment.newInstance(sessionId, soci), "PARTIDES");
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.SOCI, soci);
        intent.putExtra(MainActivity.SESSION_ID, sessionId);
        setResult(2, intent);
        finish();
    }
}
