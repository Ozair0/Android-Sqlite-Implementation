package com.example.employeinfo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    LayoutInflater layoutInflater;
    List<Employee> KardanEmployees;
    Context context;
    DatabaseHelper DB;


    public MyAdapter(List<Employee> KUEmployees, Context context) {
        this.KardanEmployees = KUEmployees;
        this.context = context;
        DB = new DatabaseHelper(this.context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myview = layoutInflater.inflate(R.layout.list_item,null);
        return new ViewHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Employee Emp  = KardanEmployees.get(i);
        viewHolder.EmployeeNameTV.setText(Emp.getEmployeeName());
        viewHolder.ProfileDetailTV.setText(Emp.getProfileDetail());
        viewHolder.EmployeeRating.setRating(Emp.getRating());
        viewHolder.EmployeeImage.setImageBitmap(Emp.getImg());
        viewHolder.DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.DeleteRecord(Emp.getEmployeeName());
                KardanEmployees.remove(viewHolder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return KardanEmployees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView EmployeeNameTV;
        TextView ProfileDetailTV;
        RatingBar EmployeeRating;
        ImageView EmployeeImage;
        Button DeleteBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            EmployeeNameTV = (TextView)itemView.findViewById(R.id.EmployeeNameTextView);
            ProfileDetailTV = (TextView)itemView.findViewById(R.id.ProfileDetailTextView);
            EmployeeRating = (RatingBar)itemView.findViewById(R.id.EmployeeRatingBar);
            EmployeeImage = (ImageView)itemView.findViewById(R.id.EmployeeImageView);
            DeleteBtn = (Button)itemView.findViewById(R.id.DeleteData);
        }


    }

}
