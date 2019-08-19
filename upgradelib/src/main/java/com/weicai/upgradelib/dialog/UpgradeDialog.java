package com.weicai.upgradelib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.weicai.upgradelib.R;


/**
 * Author: Austin
 * Time: 2018/8/9
 * Description:
 */
public class UpgradeDialog extends Dialog {
    private Context context;
    private TextView tvTitle;
    private TextView tvContext;
    private UpgradeDialogListener listener;
    private Button btnConfirm;
    private Button btnCancel;
    public interface UpgradeDialogListener {
        public void onClick(View view);

    }

    public UpgradeDialog(@NonNull Context context) {
        this(context, R.style.dialog);
        Log.e("aa","1111");
    }

    public UpgradeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        Log.e("aa","2222");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(false);//是否可以撤销
        View view = View.inflate(context,R.layout.dialog_upgrade,null);
        setContentView(view);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvContext = (TextView) view.findViewById(R.id.tv_content);
        btnConfirm = (Button) view.findViewById(R.id.btn_confirm);
        btnCancel = (Button) view.findViewById(R.id.btn_confirm);
        Log.e("aa","333");
//        initViews(view);
    }


    public void setTvTitle(String str) {
        tvTitle.setText(str);
    }

    private void initViews(View view) {
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvContext = (TextView) view.findViewById(R.id.tv_content);
        btnConfirm = (Button) view.findViewById(R.id.btn_confirm);
        btnCancel = (Button) view.findViewById(R.id.btn_confirm);




    }

    public void setRighgtButton(View.OnClickListener clickListener) {
        btnCancel.setOnClickListener(clickListener);

    }

    public void setRighgtButton(String rightStr,View.OnClickListener clickListener) {
        setRightBtnText(rightStr);
        setRighgtButton(clickListener);
    }

    public void setLeftButton(View.OnClickListener clickListener) {
        btnConfirm.setOnClickListener(clickListener);
    }

    public void setLeftButton(String leftStr, View.OnClickListener clickListener) {
        setLeftBtnText(leftStr);
        setLeftButton(clickListener);
    }

    public void setLeftBtnText(String strLeft) {
        btnConfirm.setText(strLeft);
    }

    public void setRightBtnText(String strRight) {
        btnCancel.setText(strRight);
    }


}
