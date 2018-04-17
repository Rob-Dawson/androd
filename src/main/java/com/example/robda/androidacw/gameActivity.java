package com.example.robda.androidacw;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class gameActivity extends AppCompatActivity {
    String path = "/data/data/com.example.robda.androidacw/files/";
    GridView gridView;
    String row1WinCondition[] = {"empty", "12", "13",};
    String row2WinCondition[] = {"21", "22", "23"};
    String row3WinCondition[] = {"31", "32", "33"};

    PuzzleDBHelper m_DBHelper = new PuzzleDBHelper(this);
    PuzzleDBHelper m_DBHelperRead = new PuzzleDBHelper(this);
    String[] puzzleRow1 = {};
    String[] puzzleRow2 = {};
    String[] puzzleRow3 = {};
    String[] puzzleRow4 = {};
    String puzzlePicture = "";
    Bitmap tiles[];
    String[][] puzzle;
    GridAdapter adapter = new GridAdapter(gameActivity.this, tiles);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        puzzleRow1 = intent.getStringArrayExtra("puzzleLayout1");
        puzzleRow2 = intent.getStringArrayExtra("puzzleLayout2");
        puzzleRow3 = intent.getStringArrayExtra("puzzleLayout3");
        puzzleRow4 = intent.getStringArrayExtra("puzzleLayout4");
        String[] fullLayout = intent.getStringArrayExtra("fullPuzzleLayout");
        puzzlePicture = intent.getStringExtra("puzzleImage");
        final int rows = puzzleRow1.length;
        int columns = 3;
        if (puzzleRow4 != null) {
            columns = 4;
        }
        puzzle = new String[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                if (i == 0) {
                    puzzle[i][j] = fullLayout[j];
                } else if (i == 1) {
                    puzzle[i][j] = fullLayout[j + (i + 2)];
                } else if (i == 2) {
                    puzzle[i][j] = fullLayout[j + (i + 4)];
                } else if (i == 3) {
                    puzzle[i][j] = fullLayout[j + (i + 6)];
                }
            }
        }


        if (puzzleRow4 != null) {
            tiles = new Bitmap[puzzleRow1.length + puzzleRow2.length +
                    puzzleRow3.length + puzzleRow4.length];
        } else {
            tiles = new Bitmap[puzzleRow1.length + puzzleRow2.length + puzzleRow3.length];
        }

        gridView = (GridView) findViewById(R.id.puzzleGrid);
        if (puzzleRow1.length == 3) {
            gridView.setNumColumns(3);
        } else if (puzzleRow1.length == 4) {
            gridView.setNumColumns(4);
        }

        Bitmap bitmap;
        int pic = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {

                bitmap = BitmapFactory.decodeFile(path + puzzlePicture + puzzle[i][j] + ".JPEG");
                tiles[pic] = bitmap;
                pic++;
            }
        }

        adapter = new GridAdapter(gameActivity.this, tiles);
        gridView.setAdapter(adapter);
/*
12,    21,31
empty, 22,32
13     23,33
 */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Toast.makeText(gameActivity.this, "Selected: " + position, Toast.LENGTH_SHORT).show();
                checkMove(position);
                gridView.setAdapter(adapter);

            }
        });
    }

    public void checkMove(int position)
    {
        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 3; x++)
            {
                if (puzzle[y][x].equals("empty"))
                {
                    String a = puzzle[y][x-1];
                    puzzle[y][x-1] = puzzle[y][x];
                    puzzle[y][x] = a;

                }
            }
        }
    }
}

