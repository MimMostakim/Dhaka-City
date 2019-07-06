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

public class HospitAdapter extends RecyclerView.Adapter<HospitAdapter.HospitalViewHolder> implements Filterable {

    private Context context;
    private List<Hospit>hospitList;
    private List<Hospit>filteredHospitalList;
    private HospitalUpdateDeleteListener hospitalUpdateDeleteListener;



    public HospitAdapter(Context context, List<Hospit> hospitList) {
        this.context = context;
        this.hospitList = hospitList;
        this.filteredHospitalList = hospitList;
        this.hospitalUpdateDeleteListener= (HospitalUpdateDeleteListener) context;


    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HospitalViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.hospital_row,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder hospitalViewHolder, final int i) {

        hospitalViewHolder.hospitalNameTv.setText(filteredHospitalList.get(i).getHospitalsName());

        hospitalViewHolder.menuTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  String hopitalId = filteredHospitalList.get(i).getId();
                final String name = filteredHospitalList.get(i).getHospitalsName();
                final String address = filteredHospitalList.get(i).getHospitalAddress();
                final String contact = filteredHospitalList.get(i).getHospitalContanctNo();
               /* PopupMenu popupMenu = new PopupMenu(context,view);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.contact_row_menu,popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem Item) {

                        final  String hopitalId = filteredHospitalList.get(i).getId();


                        switch (Item.getItemId()){
                            case R.id.item_view:
                                hospitalUpdateDeleteListener.onSingleDataFetching(hopitalId,name,address,contact);
                                Toast.makeText(context, "view data", Toast.LENGTH_SHORT).show();

                                break;

                            case R.id.item_edit:
                                Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.item_delet:
                                hospitalUpdateDeleteListener.onHospitalDelete(hopitalId);
                                Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
                                break;

                        }


                        return false;
                    }
                });*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return filteredHospitalList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String queryString = charSequence.toString();
                if (queryString.isEmpty()){
                    filteredHospitalList=hospitList;
                }else {
                    List<Hospit> tempList = new ArrayList<>();
                    for (Hospit h: hospitList){
                        if (h.getHospitalsName().toLowerCase().contains(queryString.toLowerCase())){
                            tempList.add(h);
                        }
                    }
                    filteredHospitalList=tempList;

                }
                FilterResults results = new FilterResults();
                results.values = filteredHospitalList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                filteredHospitalList = (List<Hospit>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

    public class HospitalViewHolder extends RecyclerView.ViewHolder  {
        private TextView hospitalNameTv,menuTv;
        public HospitalViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalNameTv=itemView.findViewById(R.id.row_hospitalsNames);
            menuTv=itemView.findViewById(R.id.row_menuhospital);

         itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 int position = getAdapterPosition();
                 Hospit hospit = filteredHospitalList.get(position);
                 AlertDialog.Builder builder = new AlertDialog.Builder(context);
                 View v = LayoutInflater.from(context).inflate(R.layout.single_information_details, null);
                 //TextView id = v.findViewById(R.id.detailsid);
                 TextView NameTv = v.findViewById(R.id.details_name);
                 TextView addressTv = v.findViewById(R.id.details_address);
                 TextView contactTv = v.findViewById(R.id.details_contact);
                 builder.setView(v);
                 // id.setText(hospit.getId());
                 NameTv.setText(hospit.getHospitalsName());
                 addressTv.setText(hospit.getHospitalAddress());
                 contactTv.setText(hospit.getHospitalContanctNo());
                 builder.setTitle("Hospital Information");
                 builder.setNegativeButton("Cancel", null);
                 builder.show();
             }
         });


        }


    }



    public  void updateList(List<Hospit>hospitList){
        this.hospitList = hospitList;
        notifyDataSetChanged();

    }

    interface HospitalUpdateDeleteListener{

        void onHospitalUpdate(String hospitalId);
        void onHospitalDelete(String hospitalId);
        void onSingleDataFetching(String hospitalId,String name,String address, String contact);
    }




}
