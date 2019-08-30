package com.fh.bdc.demo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fh.bdc.R;

import java.util.List;

/**
 * 适配器，模拟列表
 */
public class EmptyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "EmptyAdapter";

    private List<String> mList;

    /**
     * viewType--分别为item以及空view
     */
    public static final int VIEW_TYPE_ITEM = 1;
    public static final int VIEW_TYPE_EMPTY = 0;

    public EmptyAdapter(List<String> datas) {
        mList = datas;
        log("EmptyAdapter");
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //在这里根据不同的viewType进行引入不同的布局
        log("onCreateViewHolder");
        if (viewType == VIEW_TYPE_EMPTY) {
            View emptyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rv_empty, parent, false);

            return new RecyclerView.ViewHolder(emptyView) {

            };

        }
        //其他的引入正常的
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_demo, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        log("onBindViewHolder");
        if (holder instanceof MyViewHolder) {
            MyViewHolder hd = (MyViewHolder) holder;
            String ss = mList.get(position);
            hd.tv.setText(ss);
        }
    }

    @Override
    public int getItemCount() {
        //同时这里也需要添加判断，如果mData.size()为0的话，只引入一个布局，就是emptyView
        // 那么，这个recyclerView的itemCount为1
        log("getItemCount");
        if (mList.size() == 0) {
            return 1;
        }
        //如果不为0，按正常的流程跑
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        //在这里进行判断，如果我们的集合的长度为0时，我们就使用emptyView的布局
        log("getItemViewType");
        if (mList.size() == 0) {
            return VIEW_TYPE_EMPTY;
        }
        //如果有数据，则使用ITEM的布局
        return VIEW_TYPE_ITEM;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            log("MyViewHolder");
        }
    }


    private void log(String str) {
        Log.e("aa","str  ---" + str);
    }

}
