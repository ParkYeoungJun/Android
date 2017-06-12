package com.example.park.business_card;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;

import java.util.*;
import java.util.List;

/**
 * Created by seungjin on 2015-12-07.
 */
public class ListActivity extends Activity {

    List<Card> list;
    SQLiteHelper dbHelper = new SQLiteHelper(this);
    ListView listView;
    EditText companyText, nameText, contactText;
    RatingBar ratingbar;
    Button delete, save, memoButton, deleteall;
    ArrayList<String> arr;
    ArrayAdapter<String> ad;
    int itemPosition;
    EditText memotext;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
  //      NfcAdapter mNfcAdapter;
  //      Intent intent = new Intent();
    //    Tag tagFromintent = intent.getParcelableArrayExtra(mNfcAdapter.EXTRA_TAG);
        listView=(ListView)findViewById(R.id.listView);
        companyText = (EditText)findViewById(R.id.modifyCompany);
        nameText = (EditText)findViewById(R.id.modifyName);
        contactText = (EditText)findViewById(R.id.modifyContact);
        ratingbar = (RatingBar)findViewById(R.id.modifyRatingbar);

        delete = (Button)findViewById(R.id.delete);
        save = (Button)findViewById(R.id.modifySave);
        memoButton = (Button)findViewById(R.id.memo);
        deleteall = (Button)findViewById(R.id.deleteAll);

/*
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(mNfcAdapter.EXTRA_NDEF_MESSAGES);
        Tag tagFromintent = intent.getParcelableArrayExtra(mNfcAdapter.EXTRA_TAG);
*/
        String str = "";
        arr = new ArrayList<String>();

        list = dbHelper.selectAllCard();

        for(Card card : list){

            str = "Company : " + card.getCompany() + "\nName : " + card.getName()
                    + "\nContact : " + card.getContact()
                    + "\nImportance : " + card.getRating() + "\n";

            arr.add(str);
        }

        ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(ad);
        ad.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                itemPosition = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                LayoutInflater inflater = ListActivity.this.getLayoutInflater();
                View inflatedView = inflater.inflate(R.layout.eachcard, null);

                companyText=(EditText)inflatedView.findViewById(R.id.modifyCompany);
                nameText=(EditText)inflatedView.findViewById(R.id.modifyName);
                contactText=(EditText)inflatedView.findViewById(R.id.modifyContact);
                ratingbar=(RatingBar)inflatedView.findViewById(R.id.modifyRatingbar);

                companyText.setText(list.get(position).getCompany().toString());
                nameText.setText(list.get(position).getName().toString());
                contactText.setText(list.get(position).getContact().toString());
                ratingbar.setRating(Float.parseFloat(list.get(position).getRating()));
                builder.setView(inflatedView);
                builder.show();

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

    public void clickDelete(View view)
    {
        dbHelper.delete(list.get(itemPosition));
        Toast.makeText(this, "Data has been deleted.", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void clickMemo(View view)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
        LayoutInflater inflater = ListActivity.this.getLayoutInflater();
        View inflatedView = inflater.inflate(R.layout.memo, null);

        memotext = (EditText)inflatedView.findViewById(R.id.memotext);
        memotext.setText(list.get(itemPosition).getMemo().toString());
        builder.setView(inflatedView);
        builder.setTitle("Modify memo");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dbHelper.updateCard(list.get(itemPosition));
                Toast.makeText(getApplicationContext(), "Data has been updated.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();

    }
    public void clickSave(View view)
    {
    /*    for(Card card : list)
        {
            if(card.getName().toString().equals(list.get(itemPosition).getName().toString()))
            {
                Card updateCard = new Card(card.getId(), companyText.getText().toString(),
                        nameText.getText().toString(), contactText.getText().toString(),
                        memotext.getText().toString(), ratingbar.getRating()+"");
                dbHelper.updateCard(updateCard);
            }
        }*/
        dbHelper.updateCard(list.get(itemPosition));
        Toast.makeText(this, "Data has been updated.", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void clickkakaolink(View view)
    {
  //      Intent intent = new Intent(ListActivity.this, Capture.class);
  //      startActivity(intent);

        try
        {
            final KakaoLink kl = KakaoLink.getKakaoLink(this);
            KakaoTalkLinkMessageBuilder b = kl.createKakaoTalkLinkMessageBuilder();

            b.addText("Company : " + list.get(itemPosition).getCompany() + "\nName : " + list.get(itemPosition).getName()
                    + "\nContact : " + list.get(itemPosition).getContact());

            b.addAppButton("앱 실행");
      //      b.addImage(Environment.getExternalStorageDirectory().toString()+"/capture.jpeg",1080, 1920);
            kl.sendMessage(b, this);

        }
        catch(KakaoParameterException e)
        {
            e.printStackTrace();
        }
    }

    public void clickShare(View view)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Business_card");
        intent.putExtra(Intent.EXTRA_TEXT, "Company : "+list.get(itemPosition).getCompany()+"\nName : "+list.get(itemPosition).getName()
                +"\nContact : "+list.get(itemPosition).getContact());
        intent.putExtra(Intent.EXTRA_TITLE, "Business_card");
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "Share"));
    }

}
