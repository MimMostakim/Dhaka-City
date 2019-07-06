package com.example.mim.dhakacity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> implements Filterable {

    private Context context;
    private List<Rest>restaurantlist;
    private List<Rest>filteredrestaurantList;
    private RestaurantUpdateDeleteListener listener;

    public RestaurantAdapter(Context context, List<Rest> restaurantlist) {
        this.context = context;
        this.restaurantlist = restaurantlist;
        this.filteredrestaurantList=restaurantlist;
        this.listener = (RestaurantUpdateDeleteListener) context;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RestaurantViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.course_row,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder restaurantViewHolder, final int i) {
        restaurantViewHolder.restaurantNameTv.setText(filteredrestaurantList.get(i).getRestaurantName());
        String restaurantId =filteredrestaurantList.get(i).getId();
      /*  restaurantViewHolder.menuTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu =new PopupMenu(context,view);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.contact_row_menu,popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        String restaurantId =filteredrestaurantList.get(i).getId();

                        switch (menuItem.getItemId()){
                            case R.id.item_view:
                                //hospitalUpdateDeleteListener.onSingleDataFetching(hopitalId,name,address,contact);
                                Toast.makeText(context, "view data", Toast.LENGTH_SHORT).show();

                                break;

                            case R.id.item_edit:
                                Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.item_delet:
                                //hospitalUpdateDeleteListener.onHospitalDelete(hopitalId);
                                listener.onrestaurantDelete(restaurantId);
                                Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
                                break;

                        }
                        return false;
                    }
                });
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return filteredrestaurantList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String queryString = charSequence.toString();
                if (queryString.isEmpty()){
                    filteredrestaurantList=restaurantlist;
                }else {
                    List<Rest> tempList = new ArrayList<>();
                    for (Rest r: restaurantlist){
                        if (r.getRestaurantName().toLowerCase().contains(queryString.toLowerCase())){
                            tempList.add(r);
                        }
                    }
                    filteredrestaurantList=tempList;

                }
                FilterResults results = new FilterResults();
                results.values = filteredrestaurantList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                filteredrestaurantList = (List<Rest>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }


    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private TextView restaurantNameTv,menuTv;
        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantNameTv = itemView.findViewById(R.id.row_courseName);
            menuTv = itemView.findViewById(R.id.row_menu);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Rest res= filteredrestaurantList.get(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    View v = LayoutInflater.from(context).inflate(R.layout.single_information_details, null);
                    //TextView id = v.findViewById(R.id.detailsid);
                    TextView NameTv = v.findViewById(R.id.details_name);
                    TextView addressTv = v.findViewById(R.id.details_address);
                    TextView contactTv = v.findViewById(R.id.details_contact);
                    builder.setView(v);
                    // id.setText(hospit.getId());
                    NameTv.setText(res.getRestaurantName());
                    addressTv.setText(res.getRestaurantAddress());
                    contactTv.setText(res.getPhoneNo());
                    builder.setTitle("Restaurant Information");
                    builder.setNegativeButton("Cancel", null);
                    builder.show();
                }
            });
        }
    }

    public  void updatrList(List<Rest>restaurantlist)
    {
        this.restaurantlist = restaurantlist;
        notifyDataSetChanged();
    }

    interface RestaurantUpdateDeleteListener{
        void onrestaurantUpdate(String restaurantId);
        void onrestaurantDelete(String resturantId);
        void onsingleDatafetched(String restaurentId,String name,String Address);
    }
}
