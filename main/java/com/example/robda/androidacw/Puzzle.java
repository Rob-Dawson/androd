package com.example.robda.androidacw;

/**
 * Created by robda on 14/03/2018.
 */

public class Puzzle
{
    private String m_Name;
    //private String m_row1;
    private String m_PictureSet;
    private String m_layoutDef;
    private int m_ID;

    @Override
    public String toString()
    {
        return m_Name;
    }

    public Puzzle(String pName, /*String pRow1, */ String pPictureSet, String pLayoutDef, int pID)
    {
        this.m_Name = pName;
        //this.m_row1 = pRow1;
        this.m_PictureSet = pPictureSet;
        this.m_layoutDef = pLayoutDef;
        this.m_ID = pID;
    }

    public String Name(){return m_Name;}
    //public String X1Y1(){return m_row1;}
    public String PictureSet(){return m_PictureSet;}
    public String LayoutDef(){return m_layoutDef;}
    public int ID(){return m_ID;}
}
