package com.loopar.zp.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.eftimoff.viewpagertransformers.RotateUpTransformer;
import com.loopar.zp.Classes.AdapterViewPager;
import com.loopar.zp.Fragment.FragmentAccount;
import com.loopar.zp.Fragment.FragmentShowWeb;
import com.loopar.zp.Fragment.FragmentHome;
import com.loopar.zp.Fragment.FragmentMenuMain;
import com.loopar.zp.R;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.viewPagerMain)
    ViewPager viewPager;
    @BindView(R.id.bottomNavMain)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.drawerLayoutMain)
    FlowingDrawer mDrawer;
    @BindView(R.id.toolbar_main)
    Toolbar toolbar;


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
        list.add(new FragmentHome());
        list.add(new FragmentShowWeb());
        list.add(new FragmentAccount());
        return list;
    }

    private void setupToolbar() {

        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.icon_menu_drawer);
        setSupportActionBar(toolbar);


        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        mDrawer = findViewById(R.id.drawerLayoutMain);

        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {

                } else if (newState == ElasticDrawer.STATE_OPEN) {
                    ft.replace(R.id.id_container_menu, new FragmentMenuMain());
                }


            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.openMenu(true);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.btnStoreToolbar:
                Toast.makeText(this, "Store", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
