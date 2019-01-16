package com.hymane.emmm.ui.binder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hymane.emmm.R;
import com.hymane.emmm.core.ui.base.BaseViewHolder;
import com.hymane.emmm.mvp.contract.IMovieContract;
import com.hymane.emmm.response.douban.MovieResp;

import androidx.annotation.NonNull;
import butterknife.BindView;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/12/12
 * Description:
 */
public class MovieBinder extends ItemViewBinder<MovieResp.Subject, MovieBinder.MovieViewHolder> {
    private IMovieContract.View view;

    public MovieBinder(IMovieContract.View view) {
        this.view = view;
    }

    @NonNull
    @Override
    protected MovieViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_movie_card, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull MovieViewHolder holder, @NonNull MovieResp.Subject item) {
        holder.bind(item);
    }

    public class MovieViewHolder extends BaseViewHolder<MovieResp.Subject> {
        @BindView(R.id.pic)
        ImageView pic;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.author)
        TextView author;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(MovieResp.Subject data) {
            Glide.with(mItemView).load(data.images.medium)
                    .into(pic);
            title.setText(data.title);
            author.setText(data.directors.get(0).name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
