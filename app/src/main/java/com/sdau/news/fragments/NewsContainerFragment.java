package com.sdau.news.fragments;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sdau.news.MyApplication;
import com.sdau.news.R;
import com.sdau.news.adapters.PagerAdapter;
import com.sdau.news.beans.Result;
import com.sdau.news.utils.HttpUtil;
import com.sdau.news.utils.JsonUtil;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.android.material.tabs.TabLayout.MODE_FIXED;
import static com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE;

public class NewsContainerFragment extends Fragment implements Serializable {

    private static final String TYPE = "type";
    private static final String TYPE_CN = "typeCN";

    //新闻类别顺序表
    private  ArrayList<String> type;
    private  ArrayList<String> typeCN;
    //当前Fragment单例
    private static NewsContainerFragment containerFragment;

    //需要填充的newsFragment
    private  static NewsFragment[] fragments;


   private ViewPager viewPager;



    private View view;
    private FragmentManager fragmentManager;
    private PagerAdapter pagerAdapter;
    private ArrayList<Result> resultArrayList;
    public NewsContainerFragment() {
    }

    public static NewsContainerFragment newInstance(ArrayList<String> type,ArrayList<String> typeCN,ArrayList<Result> resultArrayList) {
        containerFragment = containerFragment == null ? new NewsContainerFragment() : containerFragment;
        Bundle args = new Bundle();
        args.putStringArrayList(TYPE, type);
        args.putStringArrayList(TYPE_CN, typeCN);
        args.putSerializable("ArrayListResult",resultArrayList);
        Log.d("TAG", "newInstance: 实例化容器时的arraylist："+resultArrayList.get(0).getNews().get(0).getTitle());
        containerFragment.setArguments(args);
        fragments = new NewsFragment[type.size()];


        return containerFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建数据库操作类
      //  favouriteOp = FavouriteSQLiteImpl.newInstance(getContext());
       // favouriteOp.createTable();


        if (getArguments() != null) {
            type = getArguments().getStringArrayList(TYPE);
            typeCN = getArguments().getStringArrayList(TYPE_CN);
            resultArrayList=(ArrayList<Result>) getArguments().getSerializable("ArrayListResult");
            Log.d("TAG", "newInstance: 接收到的arraylist："+resultArrayList.get(0).getNews().get(0).getTitle());

        }
        //初始化要加载的fragment
        if (fragments[0] == null) {
         //   Log.d(TAG, "waste time");
            for (int i = 0; i < fragments.length; i++) {
                fragments[i] = new NewsFragment(type.get(i),resultArrayList.get(i));
            }

        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_news_contrainer, container, false);

        TabLayout mTabLayout=view.findViewById(R.id.news_tab);

        viewPager=view.findViewById(R.id.news_viewpager);
        ButterKnife.bind(this, view);

        fragmentManager = getActivity().getSupportFragmentManager();

        //设置viewpager的适配器
        setAdapter();

        //设置tabLayout的填充方式
        if (type.size() <= 5)
            mTabLayout.setTabMode(MODE_FIXED);
        else
            mTabLayout.setTabMode(MODE_SCROLLABLE);



        //tabLayout与pagerView建立监听
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        return view;
    }


    private void setAdapter() {
        if (pagerAdapter == null ) {
            Log.d("TAG", "new Pager Adapter");
            pagerAdapter = new PagerAdapter(fragmentManager,fragments, type, typeCN);
            viewPager.setAdapter(pagerAdapter);

        } else {
            //Log.d(TAG, "reuse adapter");
        }
    }

}
