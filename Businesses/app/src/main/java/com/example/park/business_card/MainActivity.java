package com.example.park.business_card;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button my_card,create, show, share;

    // Make
    EditText company, name, contact;
    EditText m_company, m_name, m_contact;

    //sql
    SQLiteHelper dbHelper = new SQLiteHelper(this);
    //
    String company_temp, name_temp, contact_temp, memo_temp, rating_temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_card = (Button)findViewById(R.id.mycard);
        create = (Button)findViewById(R.id.create);
        show = (Button)findViewById(R.id.show);
        share = (Button)findViewById(R.id.share);

        //test textview
       // test = (TextView)findViewById(R.id.test);

//        Log.d("destiny", "Insert");
//        dbHelper.insert(new Card("kakao", "kim", "010-7137-4971"));
//        dbHelper.insert(new Card("samsung", "lee", "010-7137-4971"));
//        dbHelper.insert(new Card("sk", "park", "010-7137-4971"));
//        Log.d("destiny", "===============");
//
//        Log.d("destiny", "Read all data");
//        List<Card> list = dbHelper.selectAllCard();
//        for(Card card : list){
//            Log.d("destiny", "Company : " + card.getCompany() + " Name : "
//                + card.getName() + " Phone : " + card.getContact());
//        }
//
//        Log.d("destiny", "===================");
//
//        Log.d("destiny", "update Park : change name and phon number");
//        list = dbHelper.selectAllCard();
//
//        for(Card card : list){
//            if(card.getName().equals("park")){
//                Card updateCard = new Card(card.getId(), "sk", "han", "010-3075-4971");
//
//                dbHelper.updateCard(updateCard);
//            }
//        }
//
//        Log.d("destiny", "After Update");
//        list = dbHelper.selectAllCard();
//        for(Card card : list){
//            Log.d("destiny", "Company : " + card.getCompany() + " Name : " + card.getName()
//                        + " Contact : " + card.getContact());
//        }
//        Log.d("destiny", "=============================");
//
//        Log.d("destiny", "Delete Lee");
//        list = dbHelper.selectAllCard();
//        for(Card card : list){
//            if(card.getName().equals("lee")){
//                dbHelper.delete(card);
//            }
//        }
//
//        Log.d("destiny", "After Delete");
//        list = dbHelper.selectAllCard();
//        for(Card card : list){
//            Log.d("destiny", "Company : " + card.getCompany() + " Name : " + card.getName()
//                    + " Contact : " + card.getContact());
//        }
//        Log.d("destiny", "=============================");

        my_card.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder mycard = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();

                List<Card> my_list = dbHelper.selectAllCard();

                boolean exist = false;

                String myCompany = "", myName = "", myContact = "";

                for(Card card : my_list){
                     if(card.getRating().equals("100.0")){

                        exist = true;

                         myCompany = card.getCompany();
                         myName = card.getName();
                         myContact = card.getContact();

                     }
                 }

                if(exist){
                    View view =inflater.inflate(R.layout.already, null);

                    mycard.setView(view);
                    mycard.setTitle("My Business Card");

                    TextView m_com = (TextView)view.findViewById(R.id.al_company);
                    TextView m_nam = (TextView)view.findViewById(R.id.al_name);
                    TextView m_con = (TextView)view.findViewById(R.id.al_contact);

                    m_com.setText(myCompany);
                    m_nam.setText(myName);
                    m_con.setText(myContact);

                }else{
                    View view =inflater.inflate(R.layout.mycard, null);

                    mycard.setView(view);
                    mycard.setTitle("Create My Business Card");
                }

                if(exist) {
                    mycard.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    mycard.setNegativeButton("Set", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder recard = new AlertDialog.Builder(MainActivity.this);
                            LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                            View view =inflater.inflate(R.layout.mycard, null);

                            recard.setView(view);
                            recard.setTitle("Set My Card");

                            recard.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    List<Card> reCard = dbHelper.selectAllCard();

                                    EditText r_company = (EditText) ((AlertDialog) dialog).findViewById(R.id.m_company);
                                    EditText r_name = (EditText) ((AlertDialog) dialog).findViewById(R.id.m_name);
                                    EditText r_contact = (EditText) ((AlertDialog) dialog).findViewById(R.id.m_contact);


                                    for (Card card : reCard) {
                                        if (card.getRating().equals("100.0")) {
                                            Card updateCard = new Card(card.getId(), r_company.getText().toString()
                                                    , r_name.getText().toString(), r_contact.getText().toString(), "","100.0");

                                            dbHelper.updateCard(updateCard);
                                        }
                                    }

                                    Toast.makeText(MainActivity.this, "Setting Complete", Toast.LENGTH_SHORT).show();
                                }
                            });

                            AlertDialog alert_re = recard.create();
                            alert_re.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                            alert_re.show();

                        }
                    });
                }else{
                    mycard.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            m_company = (EditText) ((AlertDialog) dialog).findViewById(R.id.m_company);
                            m_name = (EditText) ((AlertDialog) dialog).findViewById(R.id.m_name);
                            m_contact = (EditText) ((AlertDialog) dialog).findViewById(R.id.m_contact);

                            Card in = new Card(m_company.getText().toString(),
                                    m_name.getText().toString(), m_contact.getText().toString(), "100.0");

                            dbHelper.insert(in);

                            Toast.makeText(MainActivity.this, "Card Saved", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                AlertDialog alert_my = mycard.create();
                alert_my.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                alert_my.show();

            }
        });

        create.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v)
           {

               /*
               **   make card
                */

               final AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
               LayoutInflater inflater = MainActivity.this.getLayoutInflater();
               View view = inflater.inflate(R.layout.makecard, null);
               dia.setView(view);
               dia.setTitle("Business Card");

               dia.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                       company = (EditText) ((AlertDialog) dialog).findViewById(R.id.company);
                       name = (EditText) ((AlertDialog) dialog).findViewById(R.id.name);
                       contact = (EditText) ((AlertDialog) dialog).findViewById(R.id.contact);

                       company_temp = company.getText().toString();
                       name_temp = name.getText().toString();
                       contact_temp = contact.getText().toString();


                       /*
                       **   Memo
                        */

                       final AlertDialog.Builder memo = new AlertDialog.Builder(MainActivity.this);
                       final LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                       View view = inflater.inflate(R.layout.memo, null);
                       memo.setView(view);

                       memo.setTitle("Memo");

                       memo.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(final DialogInterface dialog, int which) {

                               EditText memotext = (EditText) ((AlertDialog) dialog).findViewById(R.id.memotext);

                               memo_temp = memotext.getText().toString();


                               /**
                                *  Rating bar
                                */

                               AlertDialog.Builder rate = new AlertDialog.Builder(MainActivity.this);
                               LayoutInflater inflate = MainActivity.this.getLayoutInflater();
                               View view = inflate.inflate(R.layout.rating, null);

                               rate.setTitle("Choose Importance");
                               rate.setView(view);

                               rate.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {

                                       RatingBar rating = (RatingBar) ((AlertDialog) dialog).findViewById(R.id.ratingbar);

                                       rating_temp = String.valueOf(rating.getRating());

                                       dbHelper.insert(new Card(company_temp, name_temp, contact_temp, memo_temp, rating_temp));

                                    /*
                                     ** test
                                    */
/*
                                       String testString = "test" + "\n\n";

                                       List<Card> list = dbHelper.selectAllCard();
                                       for (Card card : list) {
                                           testString += "Company : " + card.getCompany() + "\nName : "
                                                   + card.getName() + "\nPhone : " + card.getContact() + "\nMemo : "
                                                   + card.getMemo() + "\nRating : " + card.getRating() + "\n\n";
                                       }

                                       test.setText(testString);
*/
                                    /*
                                    * finish test
                                    */

                                   }
                               });

                               AlertDialog alert_ratingbar = rate.create();
                               alert_ratingbar.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                               alert_ratingbar.show();

                               /*
                               **   finish rating bar
                                */

                           }
                       });

                       AlertDialog alert_memo = memo.create();
                       alert_memo.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                       alert_memo.show();

                       /*
                       **   memo
                        */

                   }
               });

               dia.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                   }
               });

               AlertDialog alert_card = dia.create();
               alert_card.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

               alert_card.show();

               /*
               ** finish card
                */
           }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
        {
            if(event.getAction() == KeyEvent.ACTION_UP)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Business Card");
                builder.setMessage("Exit?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        System.exit(0);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }*/
}
