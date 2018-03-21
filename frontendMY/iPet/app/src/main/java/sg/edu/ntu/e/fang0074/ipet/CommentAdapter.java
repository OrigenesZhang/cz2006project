package sg.edu.ntu.e.fang0074.ipet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 22/3/2018.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.HolderView> {

    private List<CommentItem> commentlist;
    private Context context;

    public CommentAdapter(List<CommentItem> commentlist, Context context) {
        this.commentlist = commentlist;
        this.context = context;
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.customcomment, parent, false);
        return new HolderView(layout);
    }

    @Override
    public void onBindViewHolder(HolderView holder, final int position) {
        holder.v_name.setText(commentlist.get(position).getName());
        holder.v_comment.setText(commentlist.get(position).getComment());
        holder.v_date.setText(commentlist.get(position).getDate());
        holder.v_rating.setText(commentlist.get(position).getRating()); //rating should be 1 d.p.
        /*
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
        }); */
    }

    @Override
    public int getItemCount() {
        return commentlist.size();
    }

    class HolderView extends RecyclerView.ViewHolder{

        TextView v_name;
        TextView v_comment;
        TextView v_rating;
        TextView v_date;

        HolderView(View itemView) {
            super(itemView);

            v_comment = (TextView)itemView.findViewById(R.id.comment_content);
            v_name = (TextView)itemView.findViewById(R.id.comment_user_name);
            v_date = (TextView) itemView.findViewById(R.id.single_comment_date);
            v_rating = (TextView) itemView.findViewById(R.id.single_user_rating);
        }
    }
}
