package com.bzq.looperpager;

import android.content.Context;

/**
 * @author Gerkey
 * Created on 2021/8/20
 */
public class SizeUtils {
    public static int dip2px(Context context , float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return(int) (dpValue * scale + 0.5f);
    }
}

