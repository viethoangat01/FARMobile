package com.example.farmobile.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmobile.R;

import java.util.List;

public class IpRecyclerAdapter extends RecyclerView.Adapter<IpRecyclerAdapter.myViewHolder> {

    private Context context;

    private List<String> ipList;

    public IpRecyclerAdapter(List<String> ipList, Context context) {
        this.ipList = ipList;
        this.context=context;
    }

    @NonNull
    @Override
    public IpRecyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ip,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IpRecyclerAdapter.myViewHolder holder, int position) {
        String ip=ipList.get(position);
        if(ip.length()>0){
            holder.txtIp.setText(ip);
        }
    }

    @Override
    public int getItemCount() {
        return ipList.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtIp;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIp=(TextView) itemView.findViewById(R.id.textview_rowip_ip);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
