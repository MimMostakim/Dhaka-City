package com.example.mim.dhakacity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> implements  Filterable
 {
    private Context context;
    private List<Model> courseList;
    private List<Model> filterdCourseList;
    // private  CourseUpdateDeleteListener courseUpdateDeleteListener;

    public CourseAdapter (Context context, List<Model> courseList) {
        this.context = context;
        this.courseList = courseList;
         this.filterdCourseList = courseList;

    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CourseViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.place_view_row, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CourseViewHolder courseViewHolder, final int i) {
        final String name=filterdCourseList.get(i).getName();
        final String details = filterdCourseList.get(i).getDescription();
        final String imageLink=filterdCourseList.get(i).getImage();
        final String addTv=filterdCourseList.get(i).getAddress();
        String id =filterdCourseList.get(i).getId();
        courseViewHolder.courseNameTv.setText(filterdCourseList.get(i).getName());


        courseViewHolder.courseNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView nameTv,DetailsTv,addressTv;
                ImageView imageView;

                Dialog dialog=new Dialog(context,R.style.AppTheme);
                dialog.setContentView(R.layout.popup_details_layout);
                dialog.show();


                nameTv =(TextView) dialog.findViewById(R.id.placeName);
                DetailsTv=(TextView)dialog.findViewById(R.id.placeDetailsId);
                imageView = (ImageView)dialog.findViewById(R.id.placeImageID);
                addressTv=(TextView)dialog.findViewById(R.id.address) ;

                nameTv.setText(name);
                DetailsTv.setText(details);
                addressTv.setText(addTv);
                Picasso.with(context).load(imageLink).into(imageView);

            }
        });



    }

    @Override
    public int getItemCount() {
        return filterdCourseList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence consgtraint) {
                String queryString = consgtraint.toString();
                if (queryString.isEmpty()){
                    filterdCourseList=courseList;
                }else {
                    List<Model> tempList = new ArrayList<>();
                    for (Model m: courseList){
                        if (m.getName().toLowerCase().contains(queryString.toLowerCase())){
                            tempList.add(m);
                        }
                    }
                    filterdCourseList = tempList;
                }
                FilterResults results = new FilterResults();
                results.values = filterdCourseList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                filterdCourseList = (List<Model>) results.values;
                notifyDataSetChanged();

            }
        };
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private TextView courseNameTv;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseNameTv = itemView.findViewById(R.id.CourseName);

        }
    }

    public void updateList(List<Model> courseList){
        this.courseList = courseList;
        notifyDataSetChanged();
    }
    interface CourseUpdateDeleteListener{
        void onCourseUpdate(String courseId);
        void OnCourseDelete(String courseId);
    }
}
