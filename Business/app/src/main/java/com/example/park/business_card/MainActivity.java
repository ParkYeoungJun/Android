package com.example.park.business_card;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    Button my_card,create, show, share, kakao;
    ImageView image;

    // Make
    EditText company, name, contact;
    EditText m_company, m_name, m_contact;

    //sql
    SQLiteHelper dbHelper = new SQLiteHelper(this);

    //
    String company_temp, name_temp, contact_temp, memo_temp, rating_temp;

    // NFC
    NfcAdapter mNfcAdapter;
    PendingIntent pendingIntent;

    //  back key
    private BackPressCloseHandler backPressCloseHandler;

    // Dialog view
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_card = (Button)findViewById(R.id.mycard);
        create = (Button)findViewById(R.id.create);
        show = (Button)findViewById(R.id.show);
        share = (Button)findViewById(R.id.share);
        kakao = (Button)findViewById(R.id.sharekakao);

        image = (ImageView)findViewById(R.id.image);

        // Back key
        backPressCloseHandler = new BackPressCloseHandler(this);


        // Appear Animation
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.click);
        Animation apear = AnimationUtils.loadAnimation(this, R.anim.apear);
        my_card.setAnimation(anim);
        create.setAnimation(anim);
        show.setAnimation(anim);
        share.setAnimation(anim);
        kakao.setAnimation(anim);
        image.setAnimation(apear);

        // Start Screen
        startActivity(new Intent(this, Start.class));

        /**
         *  NFC in onCreate
         */

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        Intent intent = new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        if(mNfcAdapter == null)
        {
            Toast.makeText(MainActivity.this, "Not Supply NFC", Toast.LENGTH_SHORT).show();
        }

        /**
        ** finish NFC in onCreate
        */

        my_card.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                my_card.startAnimation(anim);


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
                    view = inflater.inflate(R.layout.already, null);

                    mycard.setView(view);
                    mycard.setTitle("My Business Card");

                    TextView m_com = (TextView)view.findViewById(R.id.al_company);
                    TextView m_nam = (TextView)view.findViewById(R.id.al_name);
                    TextView m_con = (TextView)view.findViewById(R.id.al_contact);

                    m_com.setText(myCompany);
                    m_nam.setText(myName);
                    m_con.setText(myContact);

                }else{
                    view =inflater.inflate(R.layout.mycard, null);

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
                            View view = inflater.inflate(R.layout.mycard, null);

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
                                                    , r_name.getText().toString(), r_contact.getText().toString(), "", "100.0");

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

                    mycard.setNeutralButton("Share", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            view.buildDrawingCache();

                            Bitmap capture = view.getDrawingCache();

                            FileOutputStream fos;

                            try {

                                fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString() + "/capture.jpeg");
                                capture.compress(Bitmap.CompressFormat.JPEG, 100, fos);

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
            public void onClick(View v) {
               create.startAnimation(anim);


               /**
               **   make card
                */

               Create_Card();

               /**
               ** finish card
                */
           }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.startAnimation(anim);


                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);

            }
        });

        share.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                share.startAnimation(anim);

                Intent intent = new Intent(MainActivity.this, Write_NFC.class);
                startActivity(intent);
            }
        });

        kakao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                kakao.startAnimation(anim);


                try
                {
                    final KakaoLink kl = KakaoLink.getKakaoLink(MainActivity.this);
                    KakaoTalkLinkMessageBuilder b = kl.createKakaoTalkLinkMessageBuilder();

                    String str = "http://images.freeimages.com/images/previews/32d/hands-4-holding-a-poster-1427643.jpg";

                    b.addImage(str,320,250);
                    b.addText("\n" + "What is Your Business Card ?");

                    b.addAppButton("Implement App");

                    kl.sendMessage(b, MainActivity.this);
                }
                catch(KakaoParameterException e)
                {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
    ** NFC
     */

    @Override
    protected void onResume(){
        super.onResume();
        if(mNfcAdapter != null){
            mNfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(mNfcAdapter != null)
            mNfcAdapter.disableForegroundDispatch(this);
    }


    // if NFC Contact Run automatically

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);

        boolean check = false;

        List<Card> list = dbHelper.selectAllCard();
        for(Card card : list){
            if(card.getRating().equals("100.0"))
                check = true;
        }

        if(intent == null)
            return;

        // Read

        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(mNfcAdapter.EXTRA_NDEF_MESSAGES);

        if(rawMsgs != null) {

            NdefMessage msg = (NdefMessage) rawMsgs[0];
            NdefRecord[] rec = msg.getRecords();
            byte[] bt = rec[0].getPayload();

            String text = new String(bt);

            StringTokenizer st = new StringTokenizer(text,"/");

            int count = 0;

            while(st.hasMoreTokens())
            {
                if(count == 0)
                {
                    company_temp = st.nextToken();

                    count++;
                }
                else if(count == 1)
                {
                    name_temp = st.nextToken();

                    count++;
                }
                else if(count == 2)
                {
                    contact_temp = st.nextToken();

                    // NFC TAG를 쓰지 않았을 때

                    if(contact_temp.equals("apps"))
                    {
                        Toast.makeText(MainActivity.this, "Please Click NFC Button",
                                Toast.LENGTH_LONG).show();

                        return;
                    }

                    count = 0;
                }
            }

            final AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
            LayoutInflater inflater = MainActivity.this.getLayoutInflater();
            View view = inflater.inflate(R.layout.makecard, null);
            dia.setView(view);
            dia.setTitle("Business Card");

            company = (EditText) view.findViewById(R.id.company);
            name = (EditText) view.findViewById(R.id.name);
            contact = (EditText) view.findViewById(R.id.contact);

            company.setText(company_temp);
            name.setText(name_temp);
            contact.setText(contact_temp);

            dia.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    company = (EditText) ((AlertDialog) dialog).findViewById(R.id.company);
                    name = (EditText) ((AlertDialog) dialog).findViewById(R.id.name);
                    contact = (EditText) ((AlertDialog) dialog).findViewById(R.id.contact);

                    company_temp = company.getText().toString();
                    name_temp = name.getText().toString();
                    contact_temp = contact.getText().toString();


                    /**
                     **   Memo
                     */

                    Memo_Rating();

                    /**
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


        }

    }

    /**
     *  finish NFC
     */

    private void Create_Card()
    {
        final AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.makecard, null);
        dia.setView(view);
        dia.setTitle("Business Card");

        dia.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                company = (EditText)((AlertDialog) dialog).findViewById(R.id.company);
                name = (EditText) ((AlertDialog) dialog).findViewById(R.id.name);
                contact = (EditText) ((AlertDialog) dialog).findViewById(R.id.contact);

                company_temp = company.getText().toString();
                name_temp = name.getText().toString();
                contact_temp = contact.getText().toString();


                /**
                 **   Memo
                 */

                Memo_Rating();

                /**
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

    }

    public void Memo_Rating()
    {
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

                        Toast.makeText(MainActivity.this, "Card Saved", Toast.LENGTH_SHORT).show();

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

    }

    // Back key

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }

}