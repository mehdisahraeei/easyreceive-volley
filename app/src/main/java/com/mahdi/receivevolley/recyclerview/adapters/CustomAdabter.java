package com.mahdi.receivevolley.recyclerview.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.mahdi.receivevolley.R;
import com.mahdi.receivevolley.recyclerview.model.Model;
import java.util.List;


public class CustomAdabter extends RecyclerView.Adapter<CustomAdabter.customView> {

    private Context context;
    private List<Model> model_oneList;


    public CustomAdabter(Context context, List<Model> model_oneList) {
        this.context = context;
        this.model_oneList = model_oneList;
    }



    @NonNull
    @Override
    public customView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.body_recyclerview, parent, false);
        return new customView(view);
    }




    @Override
    public void onBindViewHolder(@NonNull customView holder, int position) {

        Model model = model_oneList.get(position);
        holder.textView.setText(model.getName());
        Glide.with(context)
                .load("http://10.0.3.2/my/uploadimage/upload/" + model.getImage())
                .into(holder.imageView);

    }



    @Override
    public int getItemCount() {
        return model_oneList.size();
    }


    public class customView extends RecyclerView.ViewHolder {
        RelativeLayout rel1;
        TextView textView;
        ImageView imageView;

        public customView(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.txt1);
            imageView = itemView.findViewById(R.id.img1);
            rel1 = itemView.findViewById(R.id.rel1);
        }
    }


}
