package sg.edu.ntu.e.fang0074.ipet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 15/3/2018.
 */

public class SearchActivityAdapter extends RecyclerView.Adapter<SearchActivityAdapter.HolderView> {

    private List<ClinicItem> cliniclist;
    private Context context;

    public SearchActivityAdapter(List<ClinicItem> cliniclist, Context context) {
        this.cliniclist = cliniclist;
        this.context = context;
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.customitem, parent, false);

        return new HolderView(layout);
    }

    @Override
    public void onBindViewHolder(HolderView holder, final int position) {
        holder.v_name.setText(cliniclist.get(position).getName());
        holder.v_image.setImageResource(cliniclist.get(position).getPhoto());
        holder.v_phone.setText(String.valueOf(cliniclist.get(position).getPhone()));
        holder.v_rating.setText(cliniclist.get(position).getRating()); //rating should be 1 d.p.

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(context, cliniclist.get(position).getName(), Toast.LENGTH_LONG).show();
                Intent startIntent = new Intent(context, ClinicIntro.class);
                //putExtra: clinic name
                context.startActivity(startIntent);
                // infoToLoad = queryData
                // Base(cliniclist.get(position).getName())
                // showClinicInfoPage(infoToLoad);

            }
        });
    }

    @Override
    public int getItemCount() {
        return cliniclist.size();
    }

    public void setfilter(List<ClinicItem> listitem){
        cliniclist = new ArrayList<>();
        cliniclist.addAll(listitem);
        notifyDataSetChanged();
    }

    class HolderView extends RecyclerView.ViewHolder{

        ImageView v_image;
        TextView v_name;
        TextView v_phone;
        TextView v_rating;

        HolderView(View itemView) {
            super(itemView);

            v_image = (ImageView)itemView.findViewById(R.id.clinic_image);
            v_name = (TextView)itemView.findViewById(R.id.clinic_title);
            v_phone = (TextView) itemView.findViewById(R.id.clinic_phone);
            v_rating = (TextView) itemView.findViewById(R.id.clinic_rating);
        }
    }
}
