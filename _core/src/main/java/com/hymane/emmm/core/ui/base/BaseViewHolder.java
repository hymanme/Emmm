package com.hymane.emmm.core.ui.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/12/19
 * Description:ViewHolder基类
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    protected View mItemView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mItemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    protected abstract void bind(T data);
}
