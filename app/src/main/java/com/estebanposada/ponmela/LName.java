package com.estebanposada.ponmela;

/**
 * Created by usuario on 13/01/2016.
 */
public class LName {

    private int idsong;
    private String song;
    private int idcheck;
    private int idcancel;

    public LName(int songic, String songst, int checkic, int cancelic){
        this.idsong = songic;
        this.song = songst;
        this.idcheck = checkic;
        this.idcancel = cancelic;
    }


    public int getIdcancel() {
        return idcancel;
    }

    public void setIdcancel(int idcancel) {
        this.idcancel = idcancel;
    }

    public int getIdcheck() {
        return idcheck;
    }

    public void setIdcheck(int idcheck) {
        this.idcheck = idcheck;
    }

    public int getIdsong() {
        return idsong;
    }

    public void setIdsong(int idsong) {
        this.idsong = idsong;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }
}
