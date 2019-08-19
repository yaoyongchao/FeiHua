package com.fh.bdc.ui.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import android.widget.SeekBar
import com.fh.bdc.R
import com.weicai.upgradelib.AppDownloadManager
import kotlinx.android.synthetic.main.dialog_upgrade_progress.*

class UpgradeProgressDialog constructor(context: Context) : AlertDialog(context, R.style.loading_dialog) {
    var mView: View? = null
//    var descrip = ""
    var url = ""
    var version = ""
    var quota = 0
    var appDownloadManager : AppDownloadManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }



    constructor (context: Context, url: String, version : String) : this(context) {
        this.url = url
        this.version = version
    }

    public open fun initViews() {
        val inflater = LayoutInflater.from(context)
        mView = inflater.inflate(R.layout.dialog_upgrade_progress, null)
        setContentView(mView)
        setCanceledOnTouchOutside(false)

        seekbar.setOnTouchListener { v, event ->
            true
        }

//        seekbar.setOnSeekBarChangeListener()
        seekbar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("NewApi")
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                tv!!.text = progress.toString() + "%"
                quota = progress
                val spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                tv!!.measure(spec, spec)
                val quotaWidth = tv!!.measuredWidth

                val spec2 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                tv!!.measure(spec2, spec2)
                val sbWidth = seekBar.measuredWidth
                val params = tv!!.layoutParams as LinearLayout.LayoutParams
                params.leftMargin = (progress.toDouble() / seekBar.max * sbWidth - quotaWidth.toDouble() * progress / seekBar.max).toInt()
                tv!!.layoutParams = params
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        setDialogLayout(context,window)
    }


    fun setTvText(progress: Int) {
        tv.text = progress.toString() + "%"
        seekbar.setProgress(progress)
        if (seekbar?.progress ==  100)
            dismiss()
    }
    fun setTvText(rate: String) {
        var pro = rate.toFloat()
        var p = 0;
        if (pro < 1.00) {
            p= 1
            tv.text = "1%"
        } else {
            p = pro.toInt()
            tv.text =p.toString()+"%"
        }
        seekbar.setProgress(p)
        if (seekbar?.progress ==  100)
            dismiss()
    }

    fun setDialogLayout(context: Context, dialogWindow: Window) {
        //        Window dialogWindow = getWindow();
        val lp = dialogWindow.attributes
        val d = context.resources.displayMetrics // 获取屏幕宽、高用
        lp.width = (d.widthPixels * 0.8).toInt() // 高度设置为屏幕的0.6
        dialogWindow.attributes = lp
    }

}