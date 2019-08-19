package com.weicai.upgradelib.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.weicai.upgradelib.R;

/**
 * Author: Austin
 * Time: 2018/8/10
 * Description:
 */
public class UpgradeDialogFragment extends AppCompatDialogFragment {
    private Context context;
    private TextView tvTitle;
    private TextView tvContext;
    private Button btnConfirm;
    private Button btnCancel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_upgrade, container);
        initViews(view);
        
        return view;
    }

    private void initViews(View view) {
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvContext = (TextView) view.findViewById(R.id.tv_content);
        btnConfirm = (Button) view.findViewById(R.id.btn_confirm);
        btnCancel = (Button) view.findViewById(R.id.btn_confirm);
    }


    public void setTvTitle(String str) {
        tvTitle.setText(str);
    }
}
