package com.example.sqlite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {

    private List<userModel> userlist;
    DatabaseHelper db;


    public AdapterRecycler(List<userModel> list)
    {
        this.userlist = list;
    }

    @NonNull
    @Override
    public AdapterRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customlayout,parent,false);
        db=new DatabaseHelper(view.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycler.ViewHolder holder, int position) {
        String name=userlist.get(position).getName();
        String section=userlist.get(position).getSection();
        String address=userlist.get(position).getAddress();
        int id=userlist.get(position).getId();
        holder.setData(name,section,address);
        holder.update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(),update.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("section", section);
                bundle.putString("address", address);
                bundle.putBoolean("update", true);
                bundle.putInt("id", id);
                i.putExtras(bundle);
                view.getContext().startActivity(i);
            }
        });
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Delete");
                alert.setMessage("Are you sure you want to delete?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean delete=db.deleteUser(id);

                        if(delete){
                            userlist.remove(position);
                            Toast.makeText(view.getContext(), "User Deleted", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                        notifyDataSetChanged();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name_text,address_text,section_text;
        private ImageView update_btn,delete_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_text=itemView.findViewById(R.id.customname);
            section_text=itemView.findViewById(R.id.customsection);
            address_text=itemView.findViewById(R.id.customaddress);
            update_btn=itemView.findViewById(R.id.update);
            delete_btn=itemView.findViewById(R.id.delete);
        }

        public void setData(String name,String section,String address){
            name_text.setText(name);
            section_text.setText(section);
            address_text.setText(address);
        }
    }


}
