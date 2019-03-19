package com.loopar.zp.Model;

public class ModelShowWeb {

    private String nameSite;
    private String linkSite;
    //private String name;


    public ModelShowWeb() {
    }

    public ModelShowWeb(String nameSite, String linkSite) {
        this.nameSite = nameSite;
        this.linkSite = linkSite;
        //this.name = name;
    }


    public String getNameSite() {
        return nameSite;
    }

    public void setNameSite(String nameSite) {
        this.nameSite = nameSite;
    }

    public String getLinkSite() {
        return linkSite;
    }

    public void setLinkSite(String linkSite) {
        this.linkSite = linkSite;
    }

}




