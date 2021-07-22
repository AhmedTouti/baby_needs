package com.me.baby_needs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Adapter.RecycleViewAdapter;
import Data.DataBaseHandler;
import Model.BabyNeeds;

public class List extends AppCompatActivity {
    private Button save;
    private EditText babyItem,Itemcolor,itemQuntity,itemSize;
   private AlertDialog.Builder Builder;
   private AlertDialog dialog;
    private RecyclerView recyclerView;
    private RecycleViewAdapter recycleViewAdapter;
    private java.util.List<BabyNeeds> listB;
    private FloatingActionButton fabList;
    ArrayList<BabyNeeds> BString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView=findViewById(R.id.recycle);
        fabList=findViewById(R.id.fabList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


       noty();


        fabList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(),"yeyyyy",Toast.LENGTH_LONG)
                 //       .show();
                dialogs();
            }
        });
    }

    private void noty() {
        DataBaseHandler db =new DataBaseHandler(this);
        BString=new ArrayList<>();

        BString=db.getAll();
        // for (BabyNeeds baby:listB ){
        // Toast.makeText(getApplicationContext(),BString.get(0).getItem_name(),Toast.LENGTH_LONG).show();
        // }
       // BString.notify();

        recycleViewAdapter =new RecycleViewAdapter(this,BString);
        recyclerView.setAdapter(recycleViewAdapter);

        recycleViewAdapter.notifyDataSetChanged();



    }

    public void dialogs() {
        Builder=new AlertDialog.Builder(this);
        View view=getLayoutInflater().inflate(R.layout.popup,null);
        babyItem=view.findViewById(R.id.baby_item);
        save=view.findViewById(R.id.SaveButton);
        Itemcolor=view.findViewById(R.id.itemcolor);
        itemQuntity=view.findViewById(R.id.itemQuantity);
        itemSize=view.findViewById(R.id.itemSize);
        Builder.setView(view);
        dialog=Builder.create();
        dialog.show();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtName=  babyItem.getText().toString().trim();
                String txtColor =Itemcolor.getText().toString().trim();
                String txtQ =itemQuntity.getText().toString().trim();
                String txtSize=itemSize.getText().toString().trim();
                if (txtName.isEmpty()||txtColor.isEmpty()||txtQ.isEmpty()||txtSize.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Empty Data:)",Toast.LENGTH_LONG)
                            .show();
                }else {
                    DataBaseHandler db=new DataBaseHandler(getApplicationContext());
                    BabyNeeds babyNeeds=new BabyNeeds(txtName,Double.valueOf(txtQ),txtColor,Double.valueOf( txtSize));
                    db.AddBabyN(babyNeeds);
                    // BabyNeeds babyNeed=db.getBabyN(3);


                    //Toast.makeText(getApplicationContext(),babyNeed.getColor(),Toast.LENGTH_LONG).show();
                    noty();

                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG)
                            .show();

                }

            }
        });


    }

}
