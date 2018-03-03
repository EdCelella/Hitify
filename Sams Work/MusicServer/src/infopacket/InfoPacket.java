/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infopacket;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author samal
 */
public class InfoPacket implements Serializable  {

    private String Service;
    private String SingleData;
    private ArrayList<String> MultipleData; 
    private byte[] FirstByteArray;
    private byte[] SecondByteArray;
    
    public void SetService(String Command)
    {
        this.Service = Command;
    }
    
    public void SetSingleData(String Data)
    {
        this.SingleData = Data;
    }
    
    public void SetArray(ArrayList<String> MultiData)
    {
        this.MultipleData = MultiData;
    }
    
    public void SetFirstByte (byte [] FirstByte)
    {
        this.FirstByteArray = FirstByte;
    }
    
    public void SetSecondByte (byte [] SecondByte)
    {
        this.SecondByteArray = SecondByte;
    }
           
    public String GetService()
    {
        return Service;
    }
    
    public String GetData()
    {
        return SingleData;
    }
    
    public ArrayList<String> GetArray()
    {
        return MultipleData;
    }
    
    public byte[] GetByteData()
    {
        return FirstByteArray;
    }
    
    public byte[] GetSecondData()
    {
        return SecondByteArray;
    }
}

