package kz.djunglestones.taskforjob;



import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Aws on 28/01/2018.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {

    RequestOptions options ;
    private Context mContext ;
    private List<User> mData ;


    public RvAdapter(Context mContext, List<User> lst) {


        this.mContext = mContext;
        this.mData = lst;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_view,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        // click listener here
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ProfileInfoActivity.class);
                intent.putExtra("first_name",mData.get(viewHolder.getAdapterPosition()).getFirst_name());
                intent.putExtra("last_name",mData.get(viewHolder.getAdapterPosition()).getLast_name());
                intent.putExtra("email",mData.get(viewHolder.getAdapterPosition()).getEmail());
                intent.putExtra("gender",mData.get(viewHolder.getAdapterPosition()).getGender());
                intent.putExtra("ip_address",mData.get(viewHolder.getAdapterPosition()).getIp_address());
                intent.putExtra("photo",mData.get(viewHolder.getAdapterPosition()).getPhoto());
                intent.putExtra("name",mData.get(viewHolder.getAdapterPosition()).getName());
                intent.putExtra("position",mData.get(viewHolder.getAdapterPosition()).getPosition());

                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.userName.setText(mData.get(position).getFirst_name());
        holder.lastName.setText(mData.get(position).getLast_name());
        holder.gender.setText(mData.get(position).getGender());

        Glide.with(mContext).load(mData.get(position).getPhoto()).apply(options).into(holder.userImage);

    }

    public void setFilter(List<User> list){

        mData = new ArrayList<>();
        mData.addAll(list);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView userName,lastName,gender;
        CircleImageView userImage;
        CardView view_container;


        public MyViewHolder(View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.main_info_view);
            userName = itemView.findViewById(R.id.user_name);
            lastName = itemView.findViewById(R.id.last_name);
            gender = itemView.findViewById(R.id.gender);
            userImage = itemView.findViewById(R.id.user_image);
        }
    }


}
