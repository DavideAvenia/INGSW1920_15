package com.example.appmobile.controller;

public class ScrivereRecensioniController {
    private static ScrivereRecensioniController recensioniController = null;

    private ScrivereRecensioniController(){}

    public static ScrivereRecensioniController getScrivereRecensioniController(){
        if(recensioniController == null)
            recensioniController = new ScrivereRecensioniController();
        return recensioniController;
    }


}
