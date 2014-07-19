package org.ababup1192.quiz.util;

import android.support.v7.widget.GridLayout;
import android.view.View;
import android.view.ViewGroup;

/**
 * レイアウトのサポートクラス
 */
public class LayoutHelper {

    public static <T extends View> void setGridLayoutParams(T view, int width, int height, int left, int top, int right, int bottom) {
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        layoutParams.setMargins(left, top, right, bottom);
        view.setLayoutParams(layoutParams);
    }

    public static <T extends View> void setGridLayoutParams(T view, int widthAndHeight, int left, int top, int right, int bottom) {
        setGridLayoutParams(view, widthAndHeight, widthAndHeight, left, top, right, bottom);
    }

    public static <T extends View> void setGridLayoutParams(T view, int width, int height) {
        setGridLayoutParams(view, width, height, 0, 0, 0, 0);
    }

    public static <T extends View> void setGridLayoutParams(T view, int widthAndHeight) {
        setGridLayoutParams(view, widthAndHeight, widthAndHeight);
    }

    public static <T extends View> void setGridLayoutParamsWrap(T view) {
        setGridLayoutParams(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

}
