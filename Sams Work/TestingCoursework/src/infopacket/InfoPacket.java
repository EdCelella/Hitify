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
    
    public void CreateArrayPacket (String service, ArrayList<String> data2)
    {
        this.Service = service;
        this.MultipleData = data2;
    }
    
    public void CreateSingleDataPacket (String service, String data1)
    {
        this.Service = service;
        this.SingleData = data1;
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

}
