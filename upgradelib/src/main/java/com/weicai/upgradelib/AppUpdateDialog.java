package com.weicai.upgradelib;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Author: Austin
 * Time: 2018/8/9
 * Description:
 */
public class AppUpdateDialog extends Dialog {
    public AppUpdateDialog(@NonNull Context context) {
        super(context);
    }

    public AppUpdateDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected AppUpdateDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
