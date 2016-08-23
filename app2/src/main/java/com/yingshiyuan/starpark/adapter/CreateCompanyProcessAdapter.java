package com.yingshiyuan.starpark.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yingshiyuan.starpark.R;
import com.yingshiyuan.starpark.data.CompanyFile;
import com.yingshiyuan.starpark.listener.OnItemClickListener;
import com.yingshiyuan.starpark.view.timeline.ItemType;
import com.yingshiyuan.starpark.view.timeline.TimeLineMarker;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:公司设立流程的适配器
 * @author:袁东华 created at 2016/7/22 0022 上午 10:14
 */
public class CreateCompanyProcessAdapter extends RecyclerView.Adapter<CreateCompanyProcessAdapter.ViewHolder> {
    //头部item
    public static final int TYPE_HEADER = 0;
    //正常item
    public static final int TYPE_NORMAL = 1;
    private OnItemClickListener mOnItemClickListener;
    private List<String> mList = new ArrayList<>();
    private Activity activity;
    private View mHeaderView;

    public CreateCompanyProcessAdapter(Activity activity) {
        this.activity = activity;

    }

    public void setList(ArrayList<String> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    /**
     * @description:设置header
     * @author:袁东华 created at 2016/7/22 0022 上午 10:16
     */
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    @Override
    public int getItemViewType(int position) {
        int size = mList.size();
        if (size == 0 || size == 1 || position == 0) {
            return TYPE_HEADER;
        } else if (size == 2 && position == 1) {
            return ItemType.ATOM;
        } else if (size > 2 && position == 1) {
            return ItemType.BEGIN;
        } else if (position == size - 1) {
            return ItemType.END;
        } else {
            return ItemType.NORMAL;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        //绑定header
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            viewHolder = new ViewHolder(mHeaderView, mOnItemClickListener);
        } else {
            //绑定正常条目
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_create_company_process, parent, false);
            viewHolder = new ViewHolder(view, mOnItemClickListener);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            return;
        } else if (getItemViewType(position) == ItemType.ATOM) {
            holder.timeLineMarker.setBeginLine(null);
            holder.timeLineMarker.setEndLine(null);
            holder.timeLineMarker.setMarkerDrawable(null);
        } else if (getItemViewType(position) == ItemType.BEGIN) {
            holder.timeLineMarker.setBeginLine(null);
        } else if (getItemViewType(position) == ItemType.END) {
            holder.timeLineMarker.setEndLine(null);
        }
        holder.desc_tv.setText(mList.get(position));
        holder.timeLineMarker.setText(position+"");
    }

    //    public int getRealPosition(RecyclerView.ViewHolder holder) {
//        int position = holder.getLayoutPosition();
//        return mHeaderView == null ? position : position - 1;
//    }
    @Override
    public int getItemCount() {
        return mHeaderView != null && mList != null ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView desc_tv;
        private TimeLineMarker timeLineMarker;
        private OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            if (itemView == mHeaderView) {
                return;
            }
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            desc_tv = (TextView) itemView.findViewById(R.id.desc_tv);
            timeLineMarker = (TimeLineMarker) itemView.findViewById(R.id.timeLineMarker);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getLayoutPosition());

            }
        }
    }

    /**
     * @Description:设置条目点击监听,供外部调用
     */
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;

    }
}
