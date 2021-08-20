package com.bzq.looperpager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bzq.looperpager.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author GerkeyB
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private List<Integer> mColorsList = new ArrayList<>();
    private LooperPageAdapter mLooperPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        prepareData();
        initView();
        initListener();
    }

    private void initListener() {
        mBinding.loopPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mLooperPageAdapter.getCount() == 0) {
                    return;
                }
                int targetPosition = position % mLooperPageAdapter.getDataSize();
                upDataLooperIndicator(targetPosition);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void upDataLooperIndicator(int targetPosition) {
        for (int i = 0; i < mBinding.looperPointContainer.getChildCount(); ++i) {
            View point = mBinding.looperPointContainer.getChildAt(i);
            if(i == targetPosition) {
                point.setBackgroundResource(R.drawable.shape_indicator_point_selected);
            } else {
                point.setBackgroundResource(R.drawable.shape_indicator_point_normal);
            }
        }
    }

    private void prepareData() {
        Random random = new Random();
        for (int i = 1; i <= 5; ++i) {
            mColorsList.add(Color.argb(random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255)));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mBinding.loopPager.startLoop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBinding.loopPager.stopLoop();
    }

    private void initView() {
        mLooperPageAdapter = new LooperPageAdapter();
        mBinding.loopPager.setAdapter(mLooperPageAdapter);
        mLooperPageAdapter.setData(mColorsList);
        int dx = (Integer.MAX_VALUE / 2) % mColorsList.size();
        int targetCenterPosition = (Integer.MAX_VALUE / 2) - dx;
        // 将数据返回的地方放在中间
        mBinding.loopPager.setCurrentItem(targetCenterPosition);

        // 动态添加点
        mBinding.looperPointContainer.removeAllViews();
        Context context = getApplicationContext();
        for (int i = 0; i < mColorsList.size(); i++) {
            View point = new View(context);
            int size = SizeUtils.dip2px(context, 8);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
            layoutParams.rightMargin = SizeUtils.dip2px(context, 5);
            layoutParams.leftMargin = SizeUtils.dip2px(context, 5);
            point.setLayoutParams(layoutParams);
            if (i == 0) {
                point.setBackgroundResource(R.drawable.shape_indicator_point_selected);
            } else {
                point.setBackgroundResource(R.drawable.shape_indicator_point_normal);
            }
            mBinding.looperPointContainer.addView(point);
        }

    }
}