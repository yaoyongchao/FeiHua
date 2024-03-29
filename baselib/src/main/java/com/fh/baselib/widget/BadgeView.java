/*
 *  BadgeView为角标数字提醒控件
 *  How to use？
 *  BadgeView badge = new BadgeView(getActivity());
 *  badge.setTargetView(myView);
 *  badge.setBadgeCount(42);
 *  1. setTargetView(View) --> 设置哪个控件显示数字提醒，参数就是一个view对象
 *  2. setBadgeCount(int) --> 设置提醒的数字
 *  3. setBadgeGravity(Gravity) --> 设置badgeview的显示位置
 *  4. setBackgroundColor() --> 设置badgeview的背景色，当然还可以设置背景图片
 *  5. setBackgroundResource() --> 设置背景图片
 *  6. setTypeface() --> 设置显示的字体
 *  7. setShadowLayer() --> 设置字体的阴影
 *
 */

package com.fh.baselib.widget;
 
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressLint("AppCompatCustomView")
public class BadgeView extends TextView {
 
    private boolean mHideOnNull = true;
 
    public BadgeView(Context context) {
        this(context, null);
    } 
 
    public BadgeView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    } 
 
    public BadgeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
 
        init(); 
    } 
 
    private void init() { 
        if (!(getLayoutParams() instanceof LayoutParams)) {
            LayoutParams layoutParams =
                    new LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            Gravity.RIGHT | Gravity.TOP);
            setLayoutParams(layoutParams);
        }

        // set default font
        setTextColor(Color.WHITE);
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        setPadding(dip2Px(5), dip2Px(1), dip2Px(5), dip2Px(1));

        // set default background
        setBackground(9, Color.parseColor("#FF3437"));

        setGravity(Gravity.CENTER);

        // default values
        setHideOnNull(true);
        setBadgeCount(0);
    }

    @SuppressLint("NewApi")
    public void setBackground(int dipRadius, int badgeColor) {
        int radius = dip2Px(dipRadius);
        float[] radiusArray = new float[] { radius, radius, radius, radius, radius, radius, radius, radius };

        RoundRectShape roundRect = new RoundRectShape(radiusArray, null, null);
        ShapeDrawable bgDrawable = new ShapeDrawable(roundRect);
        bgDrawable.getPaint().setColor(badgeColor);
        setBackground(bgDrawable);
    }

    /**
     * @return Returns true if view is hidden on badge value 0 or null;
     */
    public boolean isHideOnNull() {
        return mHideOnNull;
    }

    /**
     * @param hideOnNull the hideOnNull to set
     */
    public void setHideOnNull(boolean hideOnNull) {
        mHideOnNull = hideOnNull;
        setText(getText());
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.TextView#setText(java.lang.CharSequence, android.widget.TextView.BufferType)
     */
    @Override
    public void setText(CharSequence text, BufferType type) {
        if (isHideOnNull() && (text == null || text.toString().equalsIgnoreCase("0"))) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);
        }
        super.setText(text, type);
    }

    /*
     * When more than 99 show 99+
     */
    public void setBadgeText(CharSequence text) {
        if (!TextUtils.isEmpty(text) && isNumeric(text)){
            int i = Integer.parseInt(text.toString());
            if(i > 99)
                text = "99+";
        }

        setText(text);
    }



    public void setBadgeCount(int count) {
        setText(String.valueOf(count));
    }

    public Integer getBadgeCount() {
        if (getText() == null) {
            return null;
        }

        String text = getText().toString();
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void setBadgeGravity(int gravity) {
        LayoutParams params = (LayoutParams) getLayoutParams();
        params.gravity = gravity;
        setLayoutParams(params);
    }

    public int getBadgeGravity() {
        LayoutParams params = (LayoutParams) getLayoutParams();
        return params.gravity;
    }

    public void setBadgeMargin(int dipMargin) {
        setBadgeMargin(dipMargin, dipMargin, dipMargin, dipMargin);
    }

    public void setBadgeMargin(int leftDipMargin, int topDipMargin, int rightDipMargin, int bottomDipMargin) {
        LayoutParams params = (LayoutParams) getLayoutParams();
        params.leftMargin = dip2Px(leftDipMargin);
        params.topMargin = dip2Px(topDipMargin);
        params.rightMargin = dip2Px(rightDipMargin);
        params.bottomMargin = dip2Px(bottomDipMargin);
        setLayoutParams(params);
    }

    public int[] getBadgeMargin() {
        LayoutParams params = (LayoutParams) getLayoutParams();
        return new int[] { params.leftMargin, params.topMargin, params.rightMargin, params.bottomMargin };
    } 
 
    public void incrementBadgeCount(int increment) {
        Integer count = getBadgeCount();
        if (count == null) {
            setBadgeCount(increment);
        } else { 
            setBadgeCount(increment + count);
        } 
    } 
 
    public void decrementBadgeCount(int decrement) {
        incrementBadgeCount(-decrement);
    } 
 
    /* 
     * Attach the BadgeView to the TabWidget 
     *  
     * @param target the TabWidget to attach the BadgeView 
     *  
     * @param tabIndex index of the tab 
     */ 
    public void setTargetView(TabWidget target, int tabIndex) {
        View tabView = target.getChildTabViewAt(tabIndex);
        setTargetView(tabView);
    } 
 
    /* 
     * Attach the BadgeView to the target view 
     *  
     * @param target the view to attach the BadgeView 
     */ 
    public void setTargetView(View target) {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        } 
 
        if (target == null) {
            return; 
        } 
 
        if (target.getParent() instanceof FrameLayout) {
            ((FrameLayout) target.getParent()).addView(this);
 
        } else if (target.getParent() instanceof ViewGroup) {
            // use a new Framelayout container for adding badge 
            ViewGroup parentContainer = (ViewGroup) target.getParent();
            int groupIndex = parentContainer.indexOfChild(target);
            parentContainer.removeView(target);
 
            FrameLayout badgeContainer = new FrameLayout(getContext());
            ViewGroup.LayoutParams parentLayoutParams = target.getLayoutParams();
 
            badgeContainer.setLayoutParams(parentLayoutParams);
            target.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
 
            parentContainer.addView(badgeContainer, groupIndex, parentLayoutParams);
            badgeContainer.addView(target);
 
            badgeContainer.addView(this);
        } else if (target.getParent() == null) {
            Log.e(getClass().getSimpleName(), "ParentView is needed");
        } 
 
    } 
 
    /* 
     * converts dip to px 
     */ 
    private int dip2Px(float dip) {
        return (int) (dip * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

    /*
     * Determine whether a string is a number.
     */
    public boolean isNumeric(CharSequence str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
} 