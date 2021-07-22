package com.me.baby_needs;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.RecycleViewAdapter;
import Data.DataBaseHandler;
import Model.BabyNeeds;

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button save;
    private EditText babyItem,Itemcolor,itemQuntity,itemSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatePopupDialog();
               /** Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

    }

    private void CreatePopupDialog() {
        builder=new AlertDialog.Builder(this);
        View view=getLayoutInflater().inflate(R.layout.popup,null);
        babyItem=view.findViewById(R.id.baby_item);
        save=view.findViewById(R.id.SaveButton);
        Itemcolor=view.findViewById(R.id.itemcolor);
        itemQuntity=view.findViewById(R.id.itemQuantity);
        itemSize=view.findViewById(R.id.itemSize);
        builder.setView(view);
        dialog=builder.create();
        dialog.show();///creating our dialog object
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

                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG)
                            .show();

                }
            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.action_settings){
          //  DataBaseHandler db=new DataBaseHandler(getApplicationContext());
           // db.AddBabyN(new BabyNeeds(1,"wipes",5,"red",5));
           // BabyNeeds babyNeeds=db.getBabyN(2);


           // Toast.makeText(getApplicationContext(),babyNeeds.getColor(),Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
