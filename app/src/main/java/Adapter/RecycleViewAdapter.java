package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.me.baby_needs.List;
import com.me.baby_needs.MainActivity;
import com.me.baby_needs.R;

import java.util.ArrayList;

import Data.DataBaseHandler;
import Model.BabyNeeds;
import Util.Util;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
     private Context context;
     private ArrayList<BabyNeeds> Malist;
     private AlertDialog.Builder builder;
     private AlertDialog dialog;
    private AlertDialog.Builder builder2;
    private AlertDialog dialog2;
     private LayoutInflater inflater;

    public RecycleViewAdapter(Context context, ArrayList<BabyNeeds> malist) {
        this.context = context;
       this.Malist = malist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.baby_row,viewGroup,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BabyNeeds babyNeeds=Malist.get(position);
        holder.itemName.setText("Name: "+babyNeeds.getItem_name());
        holder.Qte.setText("Qte: "+String.valueOf(babyNeeds.getQuantity()));
        holder.ColorItem.setText("Color: "+babyNeeds.getColor());
        holder.Size.setText("Size: "+String.valueOf(babyNeeds.getSize()));
       // holder.date.setText();



    }




    @Override
    public int getItemCount() {
        return Malist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       private ImageButton b1,b2;
        public TextView itemName,Qte,ColorItem,Size,date;
        public ImageButton EditB,deletB;

        public ViewHolder(@NonNull View itemView,Context ctx) {
            super(itemView);
            context=ctx;
            itemName =itemView.findViewById(R.id.itemName);
            Qte=itemView.findViewById(R.id.Qte);
            ColorItem=itemView.findViewById(R.id.ColorItem);
            Size=itemView.findViewById(R.id.Size);
            date=itemView.findViewById(R.id.Dte);
           EditB =itemView.findViewById(R.id.EditB);
            deletB=itemView.findViewById(R.id.deletB);
            EditB.setOnClickListener(this);
            deletB.setOnClickListener(this);

        }



        @Override
        public void onClick(View view) {
            int position =getAdapterPosition();
            switch (view.getId()){
                case R.id.EditB:{
                    update(getAdapterPosition());

                    Toast.makeText(itemView.getContext(),"yes"+Malist.get(position).getItem_name()  ,Toast.LENGTH_LONG).show();

                    break;
                }
                case R.id.deletB:{



                    deletei(position);
                   // notifyDataSetChanged();
                 //   notifyItemChanged(getAdapterPosition());
                   // notify();

                    //Toast.makeText(itemView.getContext(),"yall",Toast.LENGTH_LONG).show();

                   //notifyDataSetChanged();
                    // Malist.remove(getAdapterPosition());
                    break;
                }
            }

        }
        private void update( final int id){
            DataBaseHandler db=new DataBaseHandler(context);


            BabyNeeds babyNeeds1=db.getBabyN(Malist.get(id).getItem_Id());
             Button save;
            final EditText babyItem,Itemcolor,itemQuntity,itemSize;
            TextView txtV;
            builder2=new AlertDialog.Builder(context);
            inflater=LayoutInflater.from(context);
                View view=inflater.inflate(R.layout.popup,null);
                txtV=view.findViewById(R.id.title);

            babyItem=view.findViewById(R.id.baby_item);
            save=view.findViewById(R.id.SaveButton);
            Itemcolor=view.findViewById(R.id.itemcolor);
            itemQuntity=view.findViewById(R.id.itemQuantity);
            itemSize=view.findViewById(R.id.itemSize);
            txtV.setText("Update:");
            save.setText("UPDATE");

             babyItem.setText(babyNeeds1.getItem_name());
           Itemcolor.setText(babyNeeds1.getColor());
            itemQuntity.setText(String.valueOf(babyNeeds1.getQuantity()));
            itemSize.setText(String.valueOf(babyNeeds1.getSize()));
            builder2.setView(view);
            dialog2=builder2.create();
            dialog2.show();

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String txtName=  babyItem.getText().toString().trim();
                    String txtColor =Itemcolor.getText().toString().trim();
                    String txtQ =itemQuntity.getText().toString().trim();
                    String txtSize=itemSize.getText().toString().trim();
                    if (txtName.isEmpty()||txtColor.isEmpty()||txtQ.isEmpty()||txtSize.isEmpty()){
                        Toast.makeText(context,"Empty Data:)",Toast.LENGTH_LONG)
                                .show();
                    }else {
                        DataBaseHandler db=new DataBaseHandler(context);


                        BabyNeeds babyNeeds=db.getBabyN(Malist.get(id).getItem_Id());
                       babyNeeds.setItem_name(txtName);
                     // String s=txtQ.substring(6);
                        babyNeeds.setQuantity(Double.valueOf(txtQ));
                        babyNeeds.setColor(txtColor);
                        babyNeeds.setSize(Double.valueOf(txtSize));

                        db.UpdateB(babyNeeds);
                        // BabyNeeds babyNeed=db.getBabyN(3);


                        //Toast.makeText(getApplicationContext(),babyNeed.getColor(),Toast.LENGTH_LONG).show();
                      /**notifyItemChanged(id,babyNeeds);important**/
                     // Malist.notifyAll();
                      // notifyDataSetChanged();
                        /**this works finally after many straggles**/
                     /*====>*/Malist.clear();
                     /*====>*/Malist.addAll(db.getAll());
                     /*====>*/notifyDataSetChanged();
                        /**--------------------------------**/




                        dialog2.dismiss();
                        Toast.makeText(context,"updated",Toast.LENGTH_LONG)
                                .show();


                    }

                }
            });


        }
        private void deletei(final int id){
            final DataBaseHandler db=new DataBaseHandler(context);
            builder=new AlertDialog.Builder(context);
            inflater=LayoutInflater.from(context);
            View view=inflater.inflate(R.layout.alert,null);
            b1=view.findViewById(R.id.ok);
            b2=view.findViewById(R.id.no);
            builder.setView(view);
            dialog=builder.create();
            dialog.show();
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    BabyNeeds b=db.getBabyN(Malist.get(id).getItem_Id());
                   db.DeleteB(b);
                    Malist.remove(id);
                    //Malist.notify();; makhadamach
                    // notifyItemChanged();
                  notifyDataSetChanged();
                  //  Toast.makeText(context,String.valueOf(Malist.get(id).getItem_Id()),Toast.LENGTH_LONG).show();
                    dialog.dismiss();

                    Toast.makeText(context,"deleted",Toast.LENGTH_LONG).show();

                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"no",Toast.LENGTH_LONG).show();
                    dialog.dismiss();

                }
            });




        }
    }
}
