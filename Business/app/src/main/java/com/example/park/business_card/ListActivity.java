package com.example.park.business_card;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Park on 2015-12-08.
 */

// List Button

public class ListActivity extends Activity {

    List<Card> list;

    SQLiteHelper dbHelper = new SQLiteHelper(this);

    ListView listView;

    Button delete, deleteall;

    ArrayList<String> arr;

    ArrayAdapter<String> ad;

    AlertDialog each;

    int itemPosition;

    TextView e_company,e_name,e_contact, e_impor,e_memo;

    Button e_edit, e_share, e_delete, viewmemo;

    String companytemp, nametemp, contacttemp, memotemp,ratingtemp;

    boolean whe; // Set Position Whether exist my Card

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        listView=(ListView)findViewById(R.id.listView);

        delete = (Button)findViewById(R.id.delete);
        deleteall = (Button)findViewById(R.id.deleteAll);

        String str = "";
        arr = new ArrayList<String>();

        list = dbHelper.selectAllCard();

        whe = false;

        for(Card card : list){

            if(!card.getRating().equals("100.0")) {
                str = "Company : " + card.getCompany() + "\nName : " + card.getName()
                        + "\nContact : " + card.getContact()
                        + "\nImportance : " + card.getRating() + "\n";
                arr.add(str);
            }
            else
                whe = true;
        }

        ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(ad);
        ad.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (whe)
                    itemPosition = position + 1;
                else
                    itemPosition = position;

                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                LayoutInflater inflater = ListActivity.this.getLayoutInflater();
                View inflatedView = inflater.inflate(R.layout.each, null);

                builder.setView(inflatedView);

                e_company = (TextView) inflatedView.findViewById(R.id.e_company);
                e_name = (TextView) inflatedView.findViewById(R.id.e_name);
                e_contact = (TextView) inflatedView.findViewById(R.id.e_contact);
                e_impor = (TextView) inflatedView.findViewById(R.id.e_impor);

                e_company.setText(list.get(itemPosition).getCompany().toString());
                e_name.setText(list.get(itemPosition).getName().toString());
                e_contact.setText(list.get(itemPosition).getContact().toString());
                e_impor.setText("Importance : " + list.get(itemPosition).getRating().toString());

                e_edit = (Button) inflatedView.findViewById(R.id.edit);
                e_share = (Button) inflatedView.findViewById(R.id.share);
                e_delete = (Button) inflatedView.findViewById(R.id.delete);
                viewmemo = (Button) inflatedView.findViewById(R.id.viewmemo);

                viewmemo.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder dia = new AlertDialog.Builder(ListActivity.this);
                        LayoutInflater inflater = ListActivity.this.getLayoutInflater();
                        final View view = inflater.inflate(R.layout.seememo, null);

                        dia.setView(view);

                        dia.setTitle("Memo");

                        e_memo = (TextView) view.findViewById(R.id.memoview);

                        e_memo.setText(list.get(itemPosition).getMemo().toString());

//                        dia.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        });

                        dia.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder dia = new AlertDialog.Builder(ListActivity.this);
                                LayoutInflater inflater = ListActivity.this.getLayoutInflater();
                                final View view = inflater.inflate(R.layout.memo, null);

                                dia.setView(view);

                                dia.setTitle("Edit Memo");

                                final EditText mem = (EditText) view.findViewById(R.id.memotext);

                                mem.setText(list.get(itemPosition).getMemo().toString());

                                dia.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        list.get(itemPosition).set_Memo(mem.getText().toString());

                                        dbHelper.updateCard(list.get(itemPosition));

                                        Toast.makeText(ListActivity.this, "Edit Memo is Done",
                                                Toast.LENGTH_SHORT).show();

                                        list = dbHelper.selectAllCard();

                                        each.dismiss();
                                    }
                                });

                                AlertDialog alert_edit = dia.create();
                                alert_edit.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                                alert_edit.show();
                            }
                        });

                        AlertDialog alert_memo = dia.create();
                        alert_memo.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                        alert_memo.show();

                    }
                });

                e_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder dia = new AlertDialog.Builder(ListActivity.this);
                        LayoutInflater inflater = ListActivity.this.getLayoutInflater();
                        final View view = inflater.inflate(R.layout.makecard, null);

                        dia.setView(view);

                        dia.setTitle("Save Change");

                        final EditText companyText, nameText, contactText;

                        companyText = (EditText)view.findViewById(R.id.company);
                        nameText = (EditText)view.findViewById(R.id.name);
                        contactText = (EditText)view.findViewById(R.id.contact);

                        companyText.setText(list.get(itemPosition).getCompany());
                        nameText.setText(list.get(itemPosition).getName());
                        contactText.setText(list.get(itemPosition).getContact());

                        dia.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                companytemp = companyText.getText().toString();
                                nametemp = nameText.getText().toString();
                                contacttemp = contactText.getText().toString();

                                Toast.makeText(ListActivity.this, companytemp, Toast.LENGTH_SHORT).show();

                                final AlertDialog.Builder memo = new AlertDialog.Builder(ListActivity.this);
                                final LayoutInflater inflater = ListActivity.this.getLayoutInflater();
                                View view = inflater.inflate(R.layout.memo, null);

                                memo.setView(view);

                                memo.setTitle("Memo");

                                final EditText memotext = (EditText) view.findViewById(R.id.memotext);

                                memotext.setText(list.get(itemPosition).getMemo().toString());

                                memo.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(final DialogInterface dialog, int which) {


                                        memotemp = memotext.getText().toString();

                                        /**
                                         *  Rating bar
                                         */

                                        AlertDialog.Builder rate = new AlertDialog.Builder(ListActivity.this);
                                        LayoutInflater inflate = ListActivity.this.getLayoutInflater();
                                        View view = inflate.inflate(R.layout.rating, null);

                                        rate.setTitle("Choose Importance");
                                        rate.setView(view);

                                        final RatingBar rating = (RatingBar) view.findViewById(R.id.ratingbar);

                                        rating.setRating(Float.parseFloat(list.get(itemPosition).getRating()));

                                        rate.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                ratingtemp = String.valueOf(rating.getRating());

                                                list.get(itemPosition).set_Company(companytemp);
                                                list.get(itemPosition).set_Name(nametemp);
                                                list.get(itemPosition).set_Contact(contacttemp);
                                                list.get(itemPosition).set_Memo(memotemp);
                                                list.get(itemPosition).set_Rating(ratingtemp);

                                                dbHelper.updateCard(list.get(itemPosition));

                                                Toast.makeText(ListActivity.this, "Data has been updated.", Toast.LENGTH_SHORT).show();

                                                /**
                                                 * Chainge listview
                                                 */

                                                list = dbHelper.selectAllCard();

                                                String str = "";

                                                arr = new ArrayList<String>();

                                                for(Card card : list){

                                                    if(!card.getRating().equals("100.0")) {
                                                        str = "Company : " + card.getCompany() + "\nName : " + card.getName()
                                                                + "\nContact : " + card.getContact()
                                                                + "\nImportance : " + card.getRating() + "\n";
                                                        arr.add(str);
                                                    }
                                                    else
                                                        whe = true;
                                                }

                                                ad = new ArrayAdapter<String>(ListActivity.this, android.R.layout.simple_list_item_1, arr);
                                                listView.setAdapter(ad);
                                                ad.notifyDataSetChanged();

                                                each.dismiss();

                                                /**
                                                 *  finish Change listview
                                                 */

                                            }
                                        });

                                        AlertDialog alert_ratingbar = rate.create();
                                        alert_ratingbar.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                                        alert_ratingbar.show();

                               /**
                               **   finish rating bar
                                */

                                    }
                                });

                                AlertDialog alert_memo = memo.create();
                                alert_memo.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                                alert_memo.show();

                            }
                        });

                        AlertDialog alert_change = dia.create();
                        alert_change.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                        alert_change.show();

                    }
                });

                // Create New View, Capture View and Share Image form

                e_share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder dia = new AlertDialog.Builder(ListActivity.this);
                        LayoutInflater inflater = ListActivity.this.getLayoutInflater();
                        final View captureview = inflater.inflate(R.layout.already, null);

                        dia.setView(captureview);

                        TextView com = (TextView) captureview.findViewById(R.id.al_company);
                        TextView nam = (TextView) captureview.findViewById(R.id.al_name);
                        TextView con = (TextView) captureview.findViewById(R.id.al_contact);

                        com.setText(list.get(itemPosition).getCompany());
                        nam.setText(list.get(itemPosition).getName());
                        con.setText(list.get(itemPosition).getContact());

                        dia.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                captureview.buildDrawingCache();

                                Bitmap capturebitmap = captureview.getDrawingCache();

                                FileOutputStream fos;

                                try {

                                    fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString() + "/capture.jpeg");
                                    capturebitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

                                    File route = Environment.getExternalStorageDirectory();

                                    Intent intent = new Intent(Intent.ACTION_SEND);

                                    intent.setType("image/*");

                                    intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(route + "/capture.jpeg"));

                                    startActivity(Intent.createChooser(intent, "Share"));

                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        dia.show();


                    }
                });

                e_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbHelper.delete(list.get(itemPosition));


                        list = dbHelper.selectAllCard();

                        String str;

                        arr = new ArrayList<String>();

                        for (Card card : list) {

                            if (!card.getRating().equals("100.0")) {
                                str = "Company : " + card.getCompany() + "\nName : " + card.getName()
                                        + "\nContact : " + card.getContact()
                                        + "\nImportance : " + card.getRating() + "\n";
                                arr.add(str);
                            } else
                                whe = true;
                        }

                        ad = new ArrayAdapter<String>(ListActivity.this, android.R.layout.simple_list_item_1, arr);
                        listView.setAdapter(ad);
                        ad.notifyDataSetChanged();

                        each.dismiss();

                        Toast.makeText(ListActivity.this, "Data has been deleted.", Toast.LENGTH_SHORT).show();
                    }
                });

                each = builder.create();
                each.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                each.show();
            }
        });

        deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHelper.deleteall();
                finish();
                Toast.makeText(getApplicationContext(), "All datas have been deleted.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
