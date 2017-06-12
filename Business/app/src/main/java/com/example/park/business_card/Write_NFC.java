package com.example.park.business_card;

import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.Charset;

/**
 * Created by Park on 2015-12-08.
 */
public class Write_NFC extends Activity implements NfcAdapter.CreateNdefMessageCallback,
    NfcAdapter.OnNdefPushCompleteCallback {

    SQLiteHelper dbHelper = new SQLiteHelper(this);

    private static final int MESSAGE_SENT = 1; //추후 Handler 메시지에 사용

    private NfcAdapter mNfcAdapter; //NfcAdapter 를 선언

    private TextView mTextView;

    String str = "";

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);


        setContentView(R.layout.nfc_aleady);
        mTextView = (TextView) findViewById(R.id.text);


        // Check serviceable NFC device

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mNfcAdapter != null) {
            mTextView.setText("Tap to beam to another NFC device");
        } else {
            mTextView.setText("This phone is not NFC enabled.");
        }

        // Select own Card

        java.util.List<Card> reCard = dbHelper.selectAllCard();

        for (Card card : reCard) {
            if (card.getRating().equals("100.0")) {

                str += "" + card.getCompany() + "/" + card.getName() + "/" + card.getContact();

            }
        }

        // If not have own Card
        // If send success

        if(!str.equals("")) {
            mNfcAdapter.setNdefPushMessageCallback(this, this);
            mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
        }
        else {
            Toast.makeText(this, "Please Make Your own Card", Toast.LENGTH_SHORT).show();

            finish();
        }
    }



    // Set type of NFC message;

    public NdefRecord createMimeRecord(String mimeType, byte[] payload) {
        byte[] mimeBytes = mimeType.getBytes(Charset.forName("US-ASCII"));
        NdefRecord mimeRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA,
                mimeBytes, new byte[0], payload);

        return mimeRecord;
    }

    // Create NFC Message ( NdefMessage )

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {

        NdefMessage msg = new NdefMessage(new NdefRecord[] { createMimeRecord(
                "com.example.park.business_card", str.getBytes())
        });
        return msg;
    }



    // If NFC send Successfully

    // But it is not do in Main Thread, using handler(like Toast)

    @Override
    public void onNdefPushComplete(NfcEvent arg0) {
        mHandler.obtainMessage(MESSAGE_SENT).sendToTarget();
    }

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SENT:
                    Toast.makeText(getApplicationContext(), "send message!!!", Toast.LENGTH_SHORT)
                            .show();
                    break;
            }
        }
    };

}

