package com.fh.bdc.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.fh.baselib.utils.ActivityManagers
import com.fh.bdc.R
import com.weicai.upgradelib.AppDownloadManager
import kotlinx.android.synthetic.main.dialog_upgrade_app.*

class UpgradeAppDialog constructor(context: Context) : AlertDialog(context,
    R.style.loading_dialog
) {
    var mView: View? = null
    var descrip = ""
    var url = ""
    var appDownloadManager : AppDownloadManager? = null
    var determineListener : View.OnClickListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    constructor (context: Context,descrip: String,url: String,determineListener : View.OnClickListener) : this(context) {
        this.descrip = descrip
        this.url = url
        this.determineListener = determineListener
    }

    public open fun initViews() {
        val inflater = LayoutInflater.from(context)
        mView = inflater.inflate(R.layout.dialog_upgrade_app, null)
        setContentView(mView)
        tv_content.text = descrip

        setCanceledOnTouchOutside(false)

        tv_cancel.setOnClickListener {
            this.dismiss()
            ActivityManagers.instance.AppExit()
        }
        tv_determine.setOnClickListener(determineListener!!)

        setDialogLayout(context,window)
    }


    fun setDialogLayout(context: Context, dialogWindow: Window) {
        //        Window dialogWindow = getWindow();
        val lp = dialogWindow.attributes
        val d = context.resources.displayMetrics // 获取屏幕宽、高用
        lp.width = (d.widthPixels * 0.8).toInt() // 高度设置为屏幕的0.6
        dialogWindow.attributes = lp
    }



}