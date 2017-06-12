package com.example.park.listview;

        import java.util.ArrayList;
        import java.util.Arrays;

        import android.app.Activity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

public class MainActivity extends Activity {
    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainListView = (ListView) findViewById(R.id.mainListView);

        // Create and populate a List of days.
        String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday"};
        ArrayList<String> daylist = new ArrayList<String>();
        daylist.addAll(Arrays.asList(days));

        // Create ArrayAdapter using the day list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, daylist);

        // Add more Days. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
        listAdapter.add("BusyDay");
        listAdapter.add("RainingDay");
        listAdapter.add("SunnyDay");


        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter(listAdapter);


    }
    public AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int $pos, long $rid)
        {

        }
    };

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
}
