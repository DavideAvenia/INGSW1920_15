import Controller.LoginDesktopController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.*;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertTrue;

public class LoginDesktopControllerTest extends ApplicationTest {

    private static LoginDesktopController controller;
    private static Boolean isRunning = false;

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        Parent parent = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
        stage.setTitle("TestApplication");
        stage.setScene(new Scene(parent,100,100));
        stage.show();
    }

    @Before
    public void setUp(){
        controller = LoginDesktopController.getInstanzaLoginDesktopController();
    }
    @Test
    public void testControllaCredenzialiPerLoginAdminSuccesso1() throws Exception {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try{
                    assertTrue(controller.controllaCredenzialiPerLogin("admin_giuseppe","Prova12345"));
                }catch (Exception e){
                    assertTrue(false);
                }
            }
        });
    }

    @Test
    public void testControllaCredenzialiPerLoginAdminSuccesso2() throws Exception {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try{
                    assertTrue(controller.controllaCredenzialiPerLogin("admin_davide","Prova12345"));
                }catch (Exception e){
                    assertTrue(false);
                }
            }
        });

    }

    @Test
    public void testControllaCredenzialiPerLoginUtenteNonPresente() throws Exception {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try{
                    assertTrue(controller.controllaCredenzialiPerLogin("utente","Prova12345"));
                } catch (Exception e){
                    assertTrue(false);
                }
            }
        });

    }

    @Test
    public void testControllaCredenzialiPerLoginAdminPasswordErrata() throws Exception {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try{
                    assertTrue(!controller.controllaCredenzialiPerLogin("admin_giuseppe","word"));
                }catch (Exception e){
                    assertTrue(false);
                }
            }
        });

    }

    @Test
    public void testControllaCredenzialiPerLoginModNonAbilitato() throws Exception {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try{
                    assertTrue(!controller.controllaCredenzialiPerLogin("xspartantt","Guido123"));
                } catch (Exception e){
                    assertTrue(false);
                }
            }
        });

    }

    @Test
    public void testControllaCredenzialiPerLoginModAbilitato() throws Exception {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try{
                    assertTrue(controller.controllaCredenzialiPerLogin("marechiaro","Ferrary2001"));
                } catch (Exception e){
                    assertTrue(false);
                }
            }
        });
    }

    @Test
    public void testControllaCredenzialiPerLoginModPasswordErrata() throws Exception {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try{
                    assertTrue(!controller.controllaCredenzialiPerLogin("marechiaro","PasswordErrata"));
                }catch (Exception e){
                    assertTrue(false);
                }
            }
        });
    }
}