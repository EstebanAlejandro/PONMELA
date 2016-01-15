package com.estebanposada.ponmela;

/**
 * Created by usuario on 13/01/2016.
 */
public class LName {

    private int idsong;
    private String song;
    private int idarrow;
    private int idcheck;
    private int idcancel;

    public LName(int songic, String songst, int arrowic, int checkic, int cancelic){
        this.idsong = songic;
        this.song = songst;
        this.idarrow = arrowic;
        this.idcheck = checkic;
        this.idcancel = cancelic;
    }

    public int getIdarrow() {
        return idarrow;
    }

    public void setIdarrow(int idarrow) {
        this.idarrow = idarrow;
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
