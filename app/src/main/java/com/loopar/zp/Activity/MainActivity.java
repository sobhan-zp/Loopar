package com.loopar.zp.Activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.eftimoff.viewpagertransformers.RotateUpTransformer;
import com.loopar.zp.Classes.AdapterViewPager;
import com.loopar.zp.Fragment.FragmentAccount;
import com.loopar.zp.Fragment.FragmentShowWeb;
import com.loopar.zp.Fragment.FragmentHome;
import com.loopar.zp.R;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.viewPagerMain)
    ViewPager viewPager;
    @BindView(R.id.bottomNavMain)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.drawerLayoutMain)
    DrawerLayout mDrawer;
    @BindView(R.id.toolbar_main)
    Toolbar toolbar;
    @BindView(R.id.navMain)
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();
        setupBody();


    }

    private void setupBody() {
        AdapterViewPager adapterViewPager = new AdapterViewPager(getSupportFragmentManager(), fragmentList());
        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(0);
        viewPager.setPageTransformer(true, new RotateUpTransformer());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.btnHome:
                        viewPager.setCurrentItem(0);
                        break;

                    case R.id.btnCoin:
                        viewPager.setCurrentItem(1);
                        break;

                    case R.id.btnaccount:
                        viewPager.setCurrentItem(2);
                        break;
                }

                return false;
            }
        });

    }

    public List<Fragment> fragmentList() {
        List<Fragment> list = new ArrayList<>();

        FragmentShowWeb fragmentShowWeb = new FragmentShowWeb();
        fragmentShowWeb.newInstance(viewPager);

        list.add(new FragmentHome());
        list.add(fragmentShowWeb);
        list.add(new FragmentAccount());
        return list;
    }

    private void setupToolbar() {

        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.icon_menu_drawer);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this , mDrawer , toolbar, 0 ,0);
        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
