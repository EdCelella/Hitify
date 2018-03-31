package infopacket;

import java.io.Serializable;
import java.util.ArrayList;

public class InfoPacket implements Serializable  {

    private String Service;
    private String SingleData;
    private ArrayList MultipleData;
    private ArrayList<ArrayList<String>> MultipleArrayData;
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
    
    public void SetMultipleArray (ArrayList<ArrayList<String>> MultipleArrayData)
    {
        this.MultipleArrayData = MultipleArrayData;
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
    
    public ArrayList<ArrayList<String>> GetMultipleArray()
    {
        return MultipleArrayData;
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
