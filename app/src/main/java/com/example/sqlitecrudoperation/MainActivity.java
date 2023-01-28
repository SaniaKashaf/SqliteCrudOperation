package com.example.sqlitecrudoperation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    Button buttonAdd,buttonView,buttonUpdate,buttonDelete;
    EditText editTextOne,editTextTwo,editTextThree,editTextFour,editTextFive;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        editTextOne=findViewById(R.id.id);
        editTextTwo=findViewById(R.id.name);
        editTextThree=findViewById(R.id.surname);
        editTextFour=findViewById(R.id.marks);
        editTextFive=findViewById(R.id.address);

        buttonAdd=findViewById(R.id.add);
        buttonView=findViewById(R.id.view);
        buttonUpdate=findViewById(R.id.update);
        buttonDelete=findViewById(R.id.delete);





        myDb = new DatabaseHelper(this);

        myDb.getWritableDatabase();

        //INSERT

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean IsInserted= myDb.insertData(editTextTwo.getText().toString(),
                        editTextThree.getText().toString(),editTextFour.getText().toString(),editTextFive.getText().toString());



                if(IsInserted==true)
                {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }
                else {


                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });
//FETCH

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = myDb.getAllData();


                if(cursor.getCount() == 0 )
                {
                    showMessage("Error","No Data Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while (cursor.moveToNext())
                {

                    buffer.append("ID: "+ cursor.getString(0)+"\n");
                    buffer.append("Name: "+ cursor.getString(1)+"\n");
                    buffer.append("SurName: "+ cursor.getString(2)+"\n");
                    buffer.append("Marks: "+ cursor.getString(3)+"\n");
                    buffer.append("Address: "+ cursor.getString(4)+"\n\n\n\n");

                }

                showMessage("Data",buffer.toString());
            }
        });
//UPDATE
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean isUpdate = myDb.UpdateData(editTextOne.getText().toString(),editTextTwo.getText().toString()
                        , editTextThree.getText().toString(),editTextFour.getText().toString(),editTextFive.getText().toString());


                if(isUpdate==true)
                {
                    Toast.makeText(MainActivity.this, "Data Update", Toast.LENGTH_SHORT).show();


                }

                else {

                    Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                }

            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Integer deletedRow =  myDb.deleteData(editTextOne.getText().toString());

                if(deletedRow>0)
                {
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                }


            }
        });




    }


    public void showMessage(String title, String Message)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();




    }
}