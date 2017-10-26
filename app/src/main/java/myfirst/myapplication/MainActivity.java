package myfirst.myapplication;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * 轮播
 */
public class MainActivity extends AppCompatActivity {

    private ArrayList<String> imglist;
    private ArrayList<String> textlist;
    private ArrayList<Ad> list = new ArrayList<Ad>();


    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg){
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            handler.sendEmptyMessageDelayed(0, 2000);
        }
    };
    private ViewPager viewPager;
    private LinearLayout dot_layout;
    private TextView tv_intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager)findViewById(R.id.viewpage);
        tv_intro = (TextView) findViewById(R.id.tv_intro);
        dot_layout = (LinearLayout)findViewById(R.id.dot_layout);
        imglist = new ArrayList<>();
        textlist = new ArrayList<>();
        imglist.add("http://img01.tooopen.com/Downs/images/2010/4/8/sy_20100408112256193519.jpg");
        imglist.add("http://www.taopic.com/uploads/allimg/111011/2893-11101109325830.jpg");
        imglist.add("http://scimg.jb51.net/allimg/121209/2-1212091UH0339.jpg");
        imglist.add("http://pic.58pic.com/58pic/16/62/63/97m58PICyWM_1024.jpg");
        imglist.add("http://p3.so.qhimgs1.com/t01f572e39dd47b8d43.jpg");
        textlist.add("我是文字1后教师大好河山进度");
        textlist.add("我是文字2后教师大好河山进度");
        textlist.add("我是文字3后教师大好河山进度");
        textlist.add("我是文字4后教师大好河山进度");
        textlist.add("我是文字5后教师大好河山进度");
        initData();
        initListener();
    }
    //初始化数据
    private void initData(){
        for (int i = 0; i <imglist.size() ; i++) {
            list.add(new Ad(imglist.get(i),textlist.get(i))) ;
        }

        initDots();
        viewPager.setAdapter(new MyPagerAdapter());
        int centerValue = Integer.MAX_VALUE/2;
        int value = centerValue % list.size();
        //设置viewPager的第一页为最大整数的中间数，实现伪无限循环
        viewPager.setCurrentItem(centerValue-value);
        updateIntroAndDot();
        handler.sendEmptyMessageDelayed(0,1000);
    }

    //初始化文字下方的圆点
    private void initDots() {
        for (int i=0; i< list.size(); i++)
        {
            View view = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10,10);
            if(i!=0) {
                params.leftMargin = 5;
            }
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.selector_dot);
            dot_layout.addView(view);
        }
    }

    //初始化监听器，当页面改变时，更新其相应数据
    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                updateIntroAndDot();
            }
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    //更新数据与圆点
    private void updateIntroAndDot(){
        int currentPage = viewPager.getCurrentItem() % list.size();
        tv_intro.setText(list.get(currentPage).getIntro());
        for (int i = 0; i < dot_layout.getChildCount(); i++)
            dot_layout.getChildAt(i).setEnabled(i==currentPage);
    }

    //ViewPager的主体部分
    class MyPagerAdapter extends PagerAdapter {
        /**
         * 返回多少page
         */
        public int getCount() {
            return Integer.MAX_VALUE;
        }
        /**view滑动到一半时是否创建新的view
         * true:表示不去创建，使用缓存；false:去重新创建
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        /**
         * 类似于BaseAdapter的getView方法
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(MainActivity.this, R.layout.activity_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            Ad ad = list.get(position%list.size());
            //imageView.setImageResource(ad.getIconResId());
            //imageView.setImageResource(Integer.parseInt(ad.getImg().get(position)));
            Glide.with(MainActivity.this).load(ad.getImg()).into(imageView);
            container.addView(view);
            return view;
        }
        /**
         * @param position:当前需要销毁第几个page
         * @param object:当前需要销毁的page
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
