package com.sdau.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;


import android.util.Log;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.sdau.news.beans.Result;
import com.sdau.news.fragments.AttentionFragment;
import com.sdau.news.fragments.CollectFragment;
import com.sdau.news.fragments.FansFragment;
import com.sdau.news.fragments.FeedbackFragment;
import com.sdau.news.fragments.HistoryFragment;
import com.sdau.news.fragments.NewsContainerFragment;
import com.sdau.news.fragments.NoticeFragment;
import com.sdau.news.utils.SQLiteNewsImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsActivity extends AppCompatActivity implements View.OnClickListener{
    //private static final String TAG ;




    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private NewsContainerFragment newsContainerFragment;
    private Fragment collectFragment;
    private Fragment noticeFragment;
    private Fragment attentionFragment;
    private Fragment fansFragment;
    private Fragment feedbackFragment;
    private DrawerLayout mDrawerLayout;



    private NavigationView.OnNavigationItemSelectedListener left_navigationItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


            Fragment fragment =null;
            switch (menuItem.getItemId()){
                case R.id.nav_attention:
                    fragment=new AttentionFragment();
                    break;
                case R.id.nav_fans:
                    fragment=new FansFragment();
                    break;
                case R.id.nav_feedback:
                    if(SQLiteNewsImpl.newInstance(NewsActivity.this).queryFeedbackAll()==null) {
                        fragment = new FeedbackFragment(true);
                    }else {
                        fragment = new FeedbackFragment();
                    }
                    break;
                case R.id.nav_history:
                    if(SQLiteNewsImpl.newInstance(NewsActivity.this).queryHistoryAll()==null) {
                        fragment = new HistoryFragment(true);
                    }else {
                        fragment = new HistoryFragment();
                    }
                    break;
            }
            if (fragment != null) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.news_contrainer, fragment);
                //fragmentTransaction.add(fragment,"");

                fragmentTransaction.commit();

                mDrawerLayout.closeDrawers();
                return true;
            } else return false;
        }
    };

    /**
     *  底部导航栏fragment切换
     *
     */


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.menu_news:
                    fragment = newsContainerFragment;
                    break;
                case R.id.menu_collect:
                    if(SQLiteNewsImpl.newInstance(NewsActivity.this).queryCollectAll()==null) {
                        fragment = new CollectFragment(true);
                    }else {
                        fragment = new CollectFragment();
                    }
                    break;
                case R.id.menu_notice:
                    fragment = new NoticeFragment();;
                    break;
            }
            if (fragment != null) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.news_contrainer, fragment);
                Log.d("TAG", "onNavigationItemSelected: 底部导航栏切换");
                fragmentTransaction.commit();
                return true;
            } else return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mDrawerLayout=findViewById(R.id.drawer_layout);



       Toolbar toolbar=findViewById(R.id.appbar);



        initFragment();

        //包含initFragemnt()
        //顶部任务栏
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.menu_top);

        }

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        NavigationView navigationView =findViewById(R.id.nav_left);
        navigationView.setNavigationItemSelectedListener(left_navigationItemSelectedListener);


    }


    /**
     *
     * @param item 顶部任务栏返回按钮
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }



    public void initFragment(){
        //获取fragmentManager
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        //实例化新闻容器
        //从myapplication中传入的值
        MyApplication myApplication=(MyApplication) getApplication();
      //  for(int i=0;i<myApplication.type.size();i++){
         //   Log.d("TAG", "myapplication持有的CN数组类型: "+myApplication.typeCN.get(i));
       //     Log.d("TAG", "myapplication持有的数组类型: "+myApplication.type.get(i));
    //    }

        newsContainerFragment=NewsContainerFragment.newInstance(myApplication.type,myApplication.typeCN,myApplication.getResultArrayList());
        Log.d("TAG", "initFragment: myapplication的arraylist值： "+myApplication.getResultArrayList().get(0).getNews().get(0).getTitle());

        //设置默认的fragment
        fragmentTransaction.replace(R.id.news_contrainer,newsContainerFragment);
        fragmentTransaction.commit();
    }



    public void onClick(View view){
        Intent intent=null;

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
