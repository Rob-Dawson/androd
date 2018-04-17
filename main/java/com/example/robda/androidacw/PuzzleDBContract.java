package com.example.robda.androidacw;

import android.provider.BaseColumns;

/**
 * Created by robda on 14/03/2018.
 */

public final class PuzzleDBContract
{

    private PuzzleDBContract()
    {

    }

    public static abstract class PuzzleEntry implements BaseColumns
    {

        public static final String TABLE_NAME = "Puzzle";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_PICTURE_SET_DEFINITION = "PictureSet";
        public static final String COLUMN_LAYOUT_DEFINITION = "LayoutDef";
        //public static final String COLUMN_ROW = "";
    }

//    public static abstract class LayoutEntry implements BaseColumns
//    {
//
//        public static final String TABLE_NAME = "Layouts";
//
//        //Row 1
//        public static final String row1Col1 = "XY";
//        public static final String row1Col2 = "XY";
//        public static final String row1Col3 = "XY";
//
//        //Row 2
//        public static final String row2Col1 = "XY";
//        public static final String row2Col2 = "XY";
//        public static final String row2Col3 = "XY";
//
//        //Row 3
//        public static final String row3Col1 = "XY";
//        public static final String row3Col2 = "XY";
//        public static final String row3Col3 = "XY";
//    }

    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_PUZZLE_TABLE = "CREATE TABLE " + PuzzleEntry.TABLE_NAME +
            " (" + PuzzleEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP + PuzzleEntry.COLUMN_NAME_NAME +
            COMMA_SEP + PuzzleEntry.COLUMN_PICTURE_SET_DEFINITION + COMMA_SEP + PuzzleEntry.COLUMN_LAYOUT_DEFINITION + " )";

//    public static final String SQL_CREATE_PUZZLE_TABLE = "CREATE TABLE " + PuzzleEntry.TABLE_NAME +
//            " (" + PuzzleEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP + PuzzleEntry.COLUMN_NAME_NAME +
//            COMMA_SEP + PuzzleEntry.COLUMN_ROW + " )";


/*
    public static final String SQL_CREATE_Layout_TABLE = "CREATE TABLE " + LayoutEntry.TABLE_NAME + " (" +
            LayoutEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP + LayoutEntry.row1Col1 + COMMA_SEP +
            LayoutEntry.row1Col2 + COMMA_SEP + LayoutEntry.row1Col3 + " )";
*/

    public static final String SQL_DELETE_PUZZLE_TABLE = "DROP TABLE IF EXISTS " + PuzzleEntry.TABLE_NAME;
}
