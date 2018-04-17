package com.example.robda.androidacw;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class SelectPuzzleActivity extends AppCompatActivity
{

    String formattedResult = "";

    private class downloadPuzzleDefinition extends AsyncTask<String, String, String>
    {

        protected String doInBackground(String... args)
        {

            try
            {
                ContentValues values = new ContentValues();
                SQLiteDatabase db = m_DBHelper.getWritableDatabase();
                SQLiteDatabase dbRead = m_DBHelperRead.getReadableDatabase();


                InputStream stream = (InputStream) new URL(args[0]).getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String puzzle = args[0].substring(args[0].lastIndexOf('/')+1);
                Log.i("value", "" + puzzle);
                String line = "";
                String result = "";
                while (line != null)
                {
                    line = reader.readLine();
                    result += line;
                }

                JSONObject json = new JSONObject(result);
                String picture = json.getString("PictureSet");
                Log.i("picture", picture);
                String layout = json.getString("layout");
                Log.i("Layout", layout);

                String[] projection = {
                        PuzzleDBContract.PuzzleEntry._ID,
                        PuzzleDBContract.PuzzleEntry.COLUMN_NAME_NAME,
                        PuzzleDBContract.PuzzleEntry.COLUMN_PICTURE_SET_DEFINITION,
                        PuzzleDBContract.PuzzleEntry.COLUMN_LAYOUT_DEFINITION
                };
                Cursor c = dbRead.query(
                        PuzzleDBContract.PuzzleEntry.TABLE_NAME,
                        projection,
                        null, null, null, null, null
                );
                values.put(PuzzleDBContract.PuzzleEntry.COLUMN_LAYOUT_DEFINITION, layout);


                //db.update(PuzzleDBContract.PuzzleEntry.TABLE_NAME, )

                values.put(PuzzleDBContract.PuzzleEntry.COLUMN_PICTURE_SET_DEFINITION, picture);
                db.insert(PuzzleDBContract.PuzzleEntry.TABLE_NAME, null, values);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return "";
        }
    }

    PuzzleDBHelper m_DBHelper = new PuzzleDBHelper(this);
    PuzzleDBHelper m_DBHelperRead = new PuzzleDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_puzzle);

        SQLiteDatabase db = new PuzzleDBHelper(this).getReadableDatabase();
        String[] projection = {
                PuzzleDBContract.PuzzleEntry._ID,
                PuzzleDBContract.PuzzleEntry.COLUMN_NAME_NAME
        };

        Cursor c = db.query(
                PuzzleDBContract.PuzzleEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        ArrayList puzzleList = new ArrayList<Puzzle>();
        c.moveToFirst();

        String name = c.getString(c.getColumnIndexOrThrow(PuzzleDBContract.PuzzleEntry.COLUMN_NAME_NAME));
        int id = c.getInt(c.getColumnIndexOrThrow(PuzzleDBContract.PuzzleEntry._ID));
        puzzleList.add(new Puzzle(name, null, null, id));
        while (c.moveToNext())
        {
            name = c.getString(c.getColumnIndexOrThrow(PuzzleDBContract.PuzzleEntry.COLUMN_NAME_NAME));
            id = c.getInt(c.getColumnIndexOrThrow(PuzzleDBContract.PuzzleEntry._ID));
            puzzleList.add(new Puzzle(name, null, null, id));
        }

        PuzzleAdapter adapter = new PuzzleAdapter(this, android.R.layout.simple_list_item_1, puzzleList);
        final ListView puzzleListView = (ListView) findViewById(R.id.PirateListView);
        puzzleListView.setAdapter(adapter);


        puzzleListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {

                position++;
                new downloadPuzzleDefinition().execute("http://www.simongrey.net/08027/slidingPuzzleAcw/puzzles/puzzle" + position + ".json");
                Intent intent = new Intent(view.getContext(), gameActivity.class);
                intent.putExtra("puzzleLayout", formattedResult);
                startActivityForResult(intent, 0);
            }
        });

    }
}
