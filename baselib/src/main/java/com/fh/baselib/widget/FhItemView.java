package com.fh.baselib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fh.baselib.R;

/**
 * Author: YongChao
 * Date: 19-9-6 上午9:41
 * Description:
 */
public class FhItemView extends LinearLayout {
    private android.view.View mView;
    private TypedArray mAttributes;
    private TextView tvTitle;
    private TextView tvRight;
    private ImageView imgArrow;
    private View viewBorder;
    public FhItemView(Context context) {
        this(context,null);
    }

    public FhItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FhItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initviews(context, attrs);
    }

    private void initviews(Context context, @Nullable AttributeSet attrs) {
        mView = LayoutInflater.from(context).inflate(R.layout.layout_item_view,this);
        mAttributes = context.obtainStyledAttributes(attrs,R.styleable.FhItemView);
        tvTitle = mView.findViewById(R.id.tv_title);
        tvRight = mView.findViewById(R.id.tv_right1);
        imgArrow = mView.findViewById(R.id.img_arrow1);
        viewBorder = mView.findViewById(R.id.view_border);
        tvTitle.setText(mAttributes.getString(R.styleable.FhItemView_text_title));
        tvRight.setText(mAttributes.getString(R.styleable.FhItemView_text_right));
        imgArrow.setVisibility(mAttributes.getBoolean(R.styleable.FhItemView_show_right_arrow,true)?View.VISIBLE:View.GONE);
        viewBorder.setVisibility(mAttributes.getBoolean(R.styleable.FhItemView_show_border,false)?View.VISIBLE:View.GONE);

        mAttributes.recycle();

    }

    public void setRightText(String s) {
        if(tvRight!=null)
            tvRight.setText(s);
    }
}
