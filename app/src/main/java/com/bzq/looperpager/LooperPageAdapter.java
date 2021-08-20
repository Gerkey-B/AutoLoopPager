package com.bzq.looperpager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class LooperPageAdapter extends PagerAdapter {

    private List<Integer> mColorsList = new ArrayList<>();

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        int relativePosition = position % mColorsList.size();
        imageView.setBackgroundColor(mColorsList.get(relativePosition));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return  view == object;
    }

    public void setData(List<Integer> colors) {
        mColorsList.clear();
        mColorsList.addAll(colors);
        notifyDataSetChanged();;
    }

    public int getDataSize() {
        return mColorsList.size();
    }
}
