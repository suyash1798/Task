package ADAPTER;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.suyash.task.MainActivity;
import com.suyash.task.R;

import java.util.List;
import java.util.zip.Inflater;

import Database.database;
import Model.Item;

/**
 * Created by SUYASH on 10-02-2018.
 */

public class recyclerviewAdapter extends RecyclerView.Adapter<recyclerviewAdapter.Viewholder> {

    Context context;
    List<Item> itemlist;

    public recyclerviewAdapter(Context context, List<Item> itemlist) {
        this.context = context;
        this.itemlist = itemlist;
    }

    @Override
    public recyclerviewAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(recyclerviewAdapter.Viewholder holder, int position) {

        Item item = itemlist.get(position);
        holder.task.setText(item.getTask());
        holder.Description.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView task;
        TextView Description;
       Button edit;
        Button delete;
        boolean comp = false;
      Viewholder(View view){
          super(view);

          task = view.findViewById(R.id.task);
          Description = view.findViewById(R.id.decription);
          delete = view.findViewById(R.id.Delete);
         // edit = view.findViewById(R.id.Edit);

          delete.setOnClickListener(this);
         // edit.setOnClickListener(this);
          //edit.setBackgroundColor(0xFFFF0000);

      }

        public void deleteall()
        {
            notifyItemRangeRemoved(0,getItemCount());
        }

        @Override
        public void onClick(View view) {

          if(view.getId()==R.id.Delete) {
              database db = new database(context);
              Item item = itemlist.get(getAdapterPosition());
              db.delete(item.getid());
              //Intent intent = new Intent(context,MainActivity.class);
              //context.(intent);
              itemlist.remove(getAdapterPosition());
              notifyItemRemoved(getAdapterPosition());
          }


        }
    }

}
