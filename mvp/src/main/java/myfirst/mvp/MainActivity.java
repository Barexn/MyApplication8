package myfirst.mvp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_tab)
    TabLayout mainTab;
    @BindView(R.id.main_vp)
    ViewPager mainVp;
    private List<Fragment> fragments;
    private Fragment1 fragment1;
    private Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        final String[] list=new String[]{"最新日报","专栏","热门","主题日报"};
        mainTab.setupWithViewPager(mainVp);
        mainTab.setTabMode(TabLayout.MODE_SCROLLABLE);



        mainTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                mainVp.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FragmentManager fm=getSupportFragmentManager();
        mainVp.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return list[position];
            }
        });

    }


    private void initViews() {

        fragments = new ArrayList<>();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
    }
}
