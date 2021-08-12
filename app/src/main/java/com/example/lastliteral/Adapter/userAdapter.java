package com.example.lastliteral.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastliteral.Model.userModel;
import com.example.lastliteral.R;
import com.example.lastliteral.showDetailActivity;

import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userAdapter.MyViewHolder> {

    Context context;
    ArrayList<userModel> list;

    public userAdapter(Context context, ArrayList<userModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        userModel user = list.get(position);

        holder.watchName.setText(user.getItemName());
        holder.watchHeight.setText(user.getItemHeight());
        holder.watchWeight.setText(user.getItemWeight());
        holder.watchDob.setText(user.getItemBirthDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView watchName, watchHeight, watchWeight, watchDob;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            watchName = itemView.findViewById(R.id.watchName);
            watchHeight = itemView.findViewById(R.id.watchHeight);
            watchWeight = itemView.findViewById(R.id.watchWeight);
            watchDob = itemView.findViewById(R.id.watchDob);

        }

        @Override
        public void onClick(View v) {

            int position = this.getAdapterPosition();
            userModel user = list.get(position);


            String username = user.getItemName();
            String height = user.getItemHeight();
            String weight = user.getItemWeight();
            String birthdate = user.getItemBirthDate();

            Toast.makeText(context, "Clicked on " + username, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, showDetailActivity.class);
            intent.putExtra("name", username);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            intent.putExtra("birthdate", birthdate);
            context.startActivity(intent);

        }
    }
}
