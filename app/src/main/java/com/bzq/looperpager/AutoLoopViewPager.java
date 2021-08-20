package com.bzq.looperpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * @author Gerkey
 * Created on 2021/8/20
 */
public class AutoLoopViewPager extends ViewPager {

    public static final int DEFAULT_DURATION = 3000;
    private int mDuration = DEFAULT_DURATION;

    public AutoLoopViewPager(@NonNull Context context) {
        this(context, null);
    }

    public AutoLoopViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 读取数据
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoLoopViewPager);
        mDuration = typedArray.getInteger(R.styleable.AutoLoopViewPager_MyDuration, DEFAULT_DURATION);
        // 释放资源
        typedArray.recycle();
    }

    private boolean isLooping = false;

    private Runnable mTask = new Runnable() {
        @Override
        public void run() {
            // 先拿到 LoopPager 当前的位置
            int currentItem = getCurrentItem();
            currentItem++;
            // 让他自己++，即滑到下一页
            setCurrentItem(currentItem);
            if (isLooping) {
                postDelayed(this, mDuration);
            }
        }
    };

    public void startLoop() {
        isLooping = true;
        post(mTask);
    }

    public void stopLoop() {
        isLooping = false;
        removeCallbacks(mTask);
    }
}

