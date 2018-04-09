package sg.edu.ntu.e.fang0074.ipet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 22/3/2018.
 */

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.HolderView> {

    private List<PetItem> petlist;
    private Context context;

    public PetAdapter(List<PetItem> petlist, Context context) {
        this.petlist = petlist;
        this.context = context;
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.custompetname, parent, false);
        return new HolderView(layout);
    }

    @Override
    public void onBindViewHolder(HolderView holder, final int position) {
        holder.v_name.setText(petlist.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Toast.makeText(context, cliniclist.get(position).getName(), Toast.LENGTH_LONG).show();
                Intent startIntent = new Intent(context, Profile.class);
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
        return petlist.size();
    }

    class HolderView extends RecyclerView.ViewHolder{

        TextView v_name;

        HolderView(View itemView) {
            super(itemView);
            v_name = (TextView)itemView.findViewById(R.id.cus_pet_name);
        }
    }
}
