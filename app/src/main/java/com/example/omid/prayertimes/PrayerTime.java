package com.example.omid.prayertimes;

/**
 * Created by omid on 5/27/16.
 */
public class PrayerTime {
    private String dateFor;
    private String temp;
    private String fajr;
    private String dhuhr;
    private String asr;
    private String maghrib;
    private String isha;
    private String direction;

    public void setDate(String date){
        this.dateFor = date;
    }
    public void setTemp(String temp){
        this.temp = temp;
    }
    public void setFajr(String fajr){
        this.fajr = fajr;
    }
    public void setDhuhr(String dhuhr){
        this.dhuhr = dhuhr;
    }
    public void setAsr(String asr){
        this.asr = asr;
    }
    public void setMaghrib(String maghrib){
        this.maghrib = maghrib;
    }
    public void setIsha(String isha){
        this.isha = isha;
    }
    public void setDirection(String direction){
        this.direction = direction;
    }
    public String getDateFor(){
        return dateFor;
    }
    public String getTemp(){
        return temp;
    }
    public String getFajr(){
        return fajr;
    }
    public String getDhuhr(){
        return dhuhr;
    }
    public String getAsr(){
        return asr;
    }
    public String getMaghrib(){
        return maghrib;
    }
    public String getIsha(){
        return isha;
    }
    public String getDirection(){
        return direction;
    }
}
