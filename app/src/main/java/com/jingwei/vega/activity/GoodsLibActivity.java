package com.jingwei.vega.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.widget.EditText;

import com.flyco.tablayout.SlidingTabLayout;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.adapter.ViewPagerAdapter;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.fragment.GoodsLibAllFragment;
import com.jingwei.vega.fragment.GoodsLibLatestFragment;
import com.jingwei.vega.fragment.GoodsLibSentimentFragment;
import com.jingwei.vega.moudle.LibSearchMsgEvent;
import com.jingwei.vega.utils.TextUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class GoodsLibActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    SlidingTabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.et_content)
    EditText mEtContent;

    private GoodsLibAllFragment mAllFragment;
    private GoodsLibLatestFragment mLatestFragment;
    private GoodsLibSentimentFragment mSentimentFragment;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private final String[] mTitles = {
            "全部", "最新", "人气"};
    private ViewPagerAdapter mAdapter;

    private String msg = "";
    private String brandId = "";

    @Override
    public int getContentView() {
        return R.layout.activity_goods_lib;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setLeftImage(R.drawable.icon_back)
                .setTitleText("商品库");
    }

    @Override
    public void initView() {
        msg = getIntent().getStringExtra("tag");
        brandId = getIntent().getStringExtra("brandId");
        mEtContent.setText(msg);

        if (!TextUtil.isEmpty(msg)) {
            hintTitleBar();
        }

        Bundle bundle = new Bundle();
        bundle.putString("tag", msg);
        bundle.putString("brandId", brandId);

        mAllFragment = new GoodsLibAllFragment();
        mAllFragment.setArguments(bundle);
        mLatestFragment = new GoodsLibLatestFragment();
        mLatestFragment.setArguments(bundle);
        mSentimentFragment = new GoodsLibSentimentFragment();
        mSentimentFragment.setArguments(bundle);

        mFragments.add(mAllFragment);
        mFragments.add(mLatestFragment);
        mFragments.add(mSentimentFragment);

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewpager.setAdapter(mAdapter);
        mViewpager.setOffscreenPageLimit(2);
        mTablayout.setViewPager(mViewpager, mTitles, GoodsLibActivity.this, mFragments);
    }

    @Override
    public void initData() {
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @OnClick(R.id.et_content)
    public void onViewClicked() {
        Intent intent = new Intent(GoodsLibActivity.this, SearchActivity.class);
        intent.putExtra("name", mEtContent.getText().toString().trim());
        startActivityForResult(intent, Constants.GOODSLIBACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.GOODSLIBACTIVITY) {
            if (data != null) {
                msg = data.getStringExtra("content");
                mEtContent.setText(msg);
                EventBus.getDefault().post(new LibSearchMsgEvent(msg));
            }
        }
    }
}
