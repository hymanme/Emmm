package com.hymane.emmm.ui.binder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hymane.emmm.R;
import com.hymane.emmm.response.douban.MovieResp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/12/12
 * Description:
 */
public class MovieBinder extends ItemViewBinder<MovieResp.Subject, MovieBinder.MovieViewHolder> {
    @NonNull
    @Override
    protected MovieViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_movie_card, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull MovieViewHolder holder, @NonNull MovieResp.Subject item) {

    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
