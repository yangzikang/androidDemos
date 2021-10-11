package com.yzk.androidbase.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yzk.androidbase.R;
import com.yzk.androidbase.bean.NewsBean;
import com.yzk.baselib.network.NetCallback;
import com.yzk.baselib.network.NetConstant;
import com.yzk.baselib.network.NetUtil;
import com.yzk.baselib.network.ResponseBase;
import com.yzk.baselib.tools.ImageUtil;
import com.yzk.baselib.tools.UiUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        RecyclerView mainRv = findViewById(R.id.main_rv);
        mainRv.setLayoutManager(new LinearLayoutManager(this));
        NewsAdapter adapter = new NewsAdapter(this);
        mainRv.setAdapter(adapter);

        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("count", "10");
        NetUtil.post(NetConstant.GET_NEWS, params, new NetCallback<NewsBean>() {
            @Override
            public void onFailure(int resultCode, String resultMsg) {
                UiUtils.toast(resultMsg);
            }

            @Override
            public void onResponse(NewsBean response) {
                if (response != null && response.result != null) {
                    adapter.appendList(response.result);
                }
            }
        });
    }

    public static class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int VIEW_TYPE_HEADER = 1;
        private static final int VIEW_TYPE_NEWS = 2;

        public Context context;
        public List<NewsBean.NewsResultBean> list;

        public NewsAdapter(Context context) {
            this.context = context;
            if (list == null) {
                list = new ArrayList<>();
            }
        }

        public void appendList(List<NewsBean.NewsResultBean> appendList) {
            this.list.addAll(appendList);

            if (list.size() == appendList.size()) {
                notifyDataSetChanged();
            } else {
                notifyItemChanged(list.size() - appendList.size(), appendList.size()); //不全部重刷的更新
            }
        }

        @NonNull
        @NotNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_HEADER) {
                return new HeaderHolder(LayoutInflater.from(context).inflate(R.layout.item_activity_recycler_header, parent, false));
            } else {
                return new NewsHolder(LayoutInflater.from(context).inflate(R.layout.item_activity_recycler, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof HeaderHolder) {
                ((HeaderHolder) holder).titleTv.setText("我是HEADER");
            } else if (holder instanceof NewsHolder) {
                int listPosition = position - 1; //第一个是Header
                ImageUtil.setImage(((NewsHolder) holder).mainSdv, list.get(listPosition).image);
                ((NewsHolder) holder).titleTv.setText(list.get(listPosition).title);
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return VIEW_TYPE_HEADER;
            } else {
                return VIEW_TYPE_NEWS;
            }
        }

        @Override
        public int getItemCount() {
            return list.size() + 1;
        }
    }

    public static class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView titleTv;

        public HeaderHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.tv_title);
        }
    }

    public static class NewsHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView mainSdv;
        public TextView titleTv;

        public NewsHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            mainSdv = itemView.findViewById(R.id.sdv_main);
            titleTv = itemView.findViewById(R.id.tv_title);
        }
    }
}