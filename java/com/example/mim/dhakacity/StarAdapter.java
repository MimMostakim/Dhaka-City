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

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder>implements Filterable {

    private Context context;
    private List<Star> hotellist;
    private List<Star> filteredHotelList;
    private HotelUpdateDeleteListener hotelUpdateDeleteListener;

    public StarAdapter(Context context, List<Star> hotellist) {
        this.context = context;
        this.hotellist = hotellist;
        this.filteredHotelList = hotellist;
        this.hotelUpdateDeleteListener= (HotelUpdateDeleteListener) context;

    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new StarViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.resturent_row,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder starViewHolder, final int i) {
        starViewHolder.hotelNameTv.setText(filteredHotelList.get(i).getHotelName());
        final  String hotelId = filteredHotelList.get(i).getId();
       /* starViewHolder.menuNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = filteredHotelList.get(i).getHotelName();
                String address = filteredHotelList.get(i).getHotelAdress();
                String contact = filteredHotelList.get(i).getHotelContactNo();
                PopupMenu popupMenu = new PopupMenu(context,view);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.contact_row_menu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem Item) {

                        final  String hotelId = filteredHotelList.get(i).getId();
                        switch (Item.getItemId()){
                            case R.id.item_view:
                                Toast.makeText(context, "view", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.item_edit:
                                Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.item_delet:
                                hotelUpdateDeleteListener.onHotelDelete(hotelId);
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
        return filteredHotelList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String queryString = charSequence.toString();
                if (queryString.isEmpty()){
                    filteredHotelList=hotellist;
                }else {
                    List<Star> tempList = new ArrayList<>();
                    for (Star s: hotellist){
                        if (s.getHotelName().toLowerCase().contains(queryString.toLowerCase())){
                            tempList.add(s);
                        }
                    }
                    filteredHotelList=tempList;

                }
                FilterResults results = new FilterResults();
                results.values = filteredHotelList;
                return results;
            }


            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredHotelList = (List<Star>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

    public  class StarViewHolder extends RecyclerView.ViewHolder {

        private TextView hotelNameTv,menuNameTv;
        public StarViewHolder(@NonNull View itemView) {
            super(itemView);

            hotelNameTv = itemView.findViewById(R.id.row_hotelNames);
            menuNameTv = itemView.findViewById(R.id.row_menuhotel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Star star = filteredHotelList.get(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    View v = LayoutInflater.from(context).inflate(R.layout.single_information_details, null);
                    //TextView id = v.findViewById(R.id.detailsid);
                    TextView NameTv = v.findViewById(R.id.details_name);
                    TextView addressTv = v.findViewById(R.id.details_address);
                    TextView contactTv = v.findViewById(R.id.details_contact);
                    builder.setView(v);
                    // id.setText(hospit.getId());
                    NameTv.setText(star.getHotelName());
                    addressTv.setText(star.getHotelAdress());
                    contactTv.setText(star.getHotelContactNo());
                    builder.setTitle("Hotels Information");
                    builder.setNegativeButton("Cancel", null);
                    builder.show();
                }
            });
        }
    }

    public  void updateList(List<Star>hotellist){
        this.hotellist = hotellist;
        notifyDataSetChanged();
    }

    interface HotelUpdateDeleteListener{
        void onHotelUpdate(String hotelId);
        void onHotelDelete(String hotelId);
    }
}
