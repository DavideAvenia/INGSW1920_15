package com.example.appmobile.controller;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ControllerLoginTest extends TestCase {

    ControllerLogin controllerLogin;
    @Before
    public void setUp() throws Exception {
        controllerLogin = ControllerLogin.getControllerLogin();
    }

    /************** TEST CASE BLACK BOX *******************/
    @Test
    public void checkEmailValida() {
        assertTrue(controllerLogin.checkEmail("giuseppeporcaro@gmail.com"));
    }
    @Test
    public void checkEmailValidaWithNumbers() {
        assertTrue(controllerLogin.checkEmail("email123withNumbers982@gmail.com"));
    }
    @Test
    public void checkEmailNonValida() {
        assertTrue(!controllerLogin.checkEmail("gi@"));
    }
    @Test
    public void checkEmailWithOnlyUsername() {
        assertTrue(!controllerLogin.checkEmail("giuseppeporcaro"));
    }
    @Test
    public void checkEmailValidaWithOnlyUsername_MailServer() {
        assertTrue(!controllerLogin.checkEmail("giuseppeporcaro@gmail"));
    }
    @Test
    public void checkEmailNonValida2() {
        assertTrue(!controllerLogin.checkEmail("@gmail.com"));
    }

    @Test
    public void checkEmailWithSpecialCharacters() {
        assertTrue(!controllerLogin.checkEmail("-E£2a[[+?^!*_:EW&/&%$@example.com"));
    }

    @Test
    public void checkEmailWith_admin_() {
        assertTrue(!controllerLogin.checkEmail("admin_utenteNuovo@test.com"));
    }



    /************** TEST CASE WHITE BOX *******************/
    @Test
    public void checkEmail_True_True(){
        assertTrue(!controllerLogin.checkEmail("admin_email123withNumbers982"));
    }

    @Test
    public void checkEmail_True_False(){
        assertTrue(!controllerLogin.checkEmail("admin_giuseppe@gmail.com"));
    }

    @Test
    public void checkEmail_False_True(){
        assertTrue(!controllerLogin.checkEmail("emailFalseTrue"));
    }

    @Test
    public void checkEmail_False_False(){
        assertTrue(controllerLogin.checkEmail("emailValida@gmail.com"));
    }
}