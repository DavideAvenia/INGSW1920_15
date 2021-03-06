package com.example.appmobile.services;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ForgotPasswordContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.ForgotPasswordHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GetDetailsHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.UpdateAttributesHandler;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentityprovider.AmazonCognitoIdentityProviderClient;
import com.amazonaws.services.cognitoidentityprovider.model.CodeMismatchException;
import com.amazonaws.services.cognitoidentityprovider.model.ExpiredCodeException;
import com.amazonaws.services.cognitoidentityprovider.model.InvalidParameterException;
import com.amazonaws.services.cognitoidentityprovider.model.LimitExceededException;
import com.amazonaws.services.cognitoidentityprovider.model.NotAuthorizedException;
import com.amazonaws.services.cognitoidentityprovider.model.SignUpResult;
import com.amazonaws.services.cognitoidentityprovider.model.UserNotConfirmedException;
import com.amazonaws.services.cognitoidentityprovider.model.UserNotFoundException;
import com.amazonaws.services.cognitoidentityprovider.model.UsernameExistsException;
import com.example.appmobile.Dao.UtenteDao;
import com.example.appmobile.entity.Utente;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AWSCognito implements UtenteDao {

    protected static CognitoUserPool userPool = null;
    protected static CognitoUser user = null;
    private static AmazonCognitoIdentityProviderClient identityProviderClient = new AmazonCognitoIdentityProviderClient(new AnonymousAWSCredentials(), new ClientConfiguration());
    private static ForgotPasswordContinuation resultContinuation;
    private final String USER_POOL_ID = "";
    private final String CLIENT_ID = "";
    private final String CLIENT_SECRET = "";

    private Utente currentUser = null;


    public AWSCognito(Context context) {
        identityProviderClient.setRegion(Region.getRegion(Regions.EU_WEST_1));
        userPool = new CognitoUserPool(context, USER_POOL_ID, CLIENT_ID, CLIENT_SECRET, identityProviderClient);
        user = userPool.getUser();
    }

    @Override
    public void login(final String usernameId, final String password, final Context context) {

        AuthenticationHandler authenticationHandler = new AuthenticationHandler() {

            @Override
            public void onSuccess(CognitoUserSession userSession, CognitoDevice newDevice) {
                showToast(context, "Login effettuato");
                //chiude l'Activity Login
                ((Activity) context).finish();

                controllerLogin.setIsLogged();
                controllerLogin.setUserIdLogged(usernameId);

                //recupero di tutti gli attributi dell'utente appena loggato
                getUtenteLoggatoByUserId(usernameId);

                //Continua collegamento api incremento numero login utente in background
                OkHttpClient client = new OkHttpClient();
                JSONObject tmp = new JSONObject();
                try {
                    tmp.put("username", usernameId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody requestBody = RequestBody.create(JSON, tmp.toString());
                Request req = new Request.Builder().url("https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/incrementalogincounter").post(requestBody).build();

                try {
                    Response res = client.newCall(req).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String userId) {
                // The API needs user sign-in credentials to continue
                AuthenticationDetails authenticationDetails = new AuthenticationDetails(usernameId, password, null);

                // Pass the user sign-in credentials to the continuation
                authenticationContinuation.setAuthenticationDetails(authenticationDetails);

                // Allow the sign-in to continue
                authenticationContinuation.continueTask();
            }

            @Override
            public void getMFACode(MultiFactorAuthenticationContinuation multiFactorAuthenticationContinuation) {
                /*
                 * Non implementato il login a più fattori
                 * */
            }

            @Override
            public void authenticationChallenge(ChallengeContinuation continuation) {

            }

            @Override
            public void onFailure(Exception exception) {
                String errore = "";
                if (exception instanceof NotAuthorizedException) {
                    errore = "Nome utente e/o password errati!";
                }
                if (exception instanceof UserNotConfirmedException) {
                    errore = "Devi verificare la registrazione prima di poter accedere!";
                }
                if (exception instanceof InvalidParameterException) {
                    errore = "Assicurati di aver riempito tutti i campi!";
                }
                showToast(context, "Login fallito: " + errore);
            }
        };
        user.getSessionInBackground(authenticationHandler);

    }

    @Override
    public void registration(String userId, String nome, String cognome, String cellulare, String email, String password, final Context context) {
        CognitoUserAttributes userAttributes = new CognitoUserAttributes();
        userAttributes.addAttribute("email", email);
        userAttributes.addAttribute("name", nome);
        userAttributes.addAttribute("family_name", cognome);
        userAttributes.addAttribute("phone_number", cellulare);
        userAttributes.addAttribute("nickname", "no_nickname");
        userAttributes.addAttribute("custom:numeroLogin", "0");
        userAttributes.addAttribute("custom:isMod", "0");
        userAttributes.addAttribute("custom:useNick", "0");

        GenericHandler confirmationCallback = new GenericHandler() {
            @Override
            public void onSuccess() {
                Log.i("confCall", "utente confermato");
            }

            @Override
            public void onFailure(Exception exception) {
                Log.i("confCallFail", "utente non confermato");
            }
        };

        SignUpHandler signUpHandler = new SignUpHandler() {
            @Override
            public void onSuccess(CognitoUser user, SignUpResult signUpResult) {
                showToast(context, "Registrazione effettuata!");
                ((Activity) context).finish();

                //collegamento api creazione statistiche per l'utente utente
                OkHttpClient client = new OkHttpClient();
                JSONObject tmp = new JSONObject();
                try {
                    tmp.put("username", userId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody requestBody = RequestBody.create(JSON, tmp.toString());
                Request req = new Request.Builder().url("https://5ecbygudm4.execute-api.eu-west-1.amazonaws.com/API_Alpha/insertnewstatisticheutente").post(requestBody).build();

                try {
                    Response res = client.newCall(req).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Exception exception) {
                String errore = "";
                if (exception instanceof UsernameExistsException) {
                    errore = "Esiste già un utente con questo username!";
                } else {
                    errore = exception.toString();
                }
                showToast(context, "Registrazione fallita!: " + errore);
                System.out.println(exception);
            }
        };

        userPool.signUpInBackground(userId, password, userAttributes, null, signUpHandler);
    }


    @Override
    public void showToast(Context context, String messaggio) {
        Toast.makeText(context, messaggio, Toast.LENGTH_LONG).show();
    }

    @Override
    public void recuperaCodiceResetPassword(final Context context, String userId) {

        CognitoUser cognitoUser = userPool.getUser(userId);

        ForgotPasswordHandler forgotPasswordHandler = new ForgotPasswordHandler() {
            @Override
            public void onSuccess() {
                showToast(context, "Password cambiata con successo!");
                ((Activity) context).finish();
            }

            @Override
            public void getResetCode(ForgotPasswordContinuation continuation) {
                CognitoUserCodeDeliveryDetails codeSentHere = continuation.getParameters();
                showToast(context, "Il codice è stato spedito a " + codeSentHere.getDestination());


                resultContinuation = continuation;
            }

            @Override
            public void onFailure(Exception exception) {
                String errore = "";
                if (exception instanceof InvalidParameterException) {
                    errore = "Parametri non validi. Assicurati di aver riempito i campi opportuni!";
                }
                if(exception instanceof CodeMismatchException){
                    errore = "Il codice inserito è errato!";
                }
                if(exception instanceof ExpiredCodeException){
                    errore = "Il codice inserito non è valido!";
                }
                if(exception instanceof LimitExceededException){
                    errore = "Hai effettuato troppe richieste. Riprova più tardi";
                }
                if(exception instanceof UserNotFoundException){
                    errore = "Utente inesistente";
                }
                showToast(context, "Fallimento reset password: " + errore);
            }
        };

        cognitoUser.forgotPasswordInBackground(forgotPasswordHandler);

    }

    @Override
    public void resetPassword(String code, String password, Context context) {
        if (resultContinuation == null) {
            showToast(context, "Bisogna prima richiedere un codice di verifica!");
        } else {
            resultContinuation.setPassword(password);
            resultContinuation.setVerificationCode(code);
            resultContinuation.continueTask();
        }
        resultContinuation = null;
    }

    @Override
    public void signout(String userId) {
        userPool.getUser(userId).signOut();
    }

    @Override
    public void recuperaNameToShowUtente(String userId, int position) {
        CognitoUser user = userPool.getUser(userId);

        user.getDetailsInBackground(new GetDetailsHandler() {
            @Override
            public void onSuccess(CognitoUserDetails cognitoUserDetails) {
                String nameToShow = userId;

                CognitoUserAttributes cognitoAttributes = cognitoUserDetails.getAttributes();
                Map<String, String> attributes = cognitoAttributes.getAttributes();

                if (Integer.parseInt(attributes.get("custom:useNick")) == 0) {
                    nameToShow = nameToShow + "\n" + attributes.get("name") + " " + attributes.get("family_name");
                } else {
                    nameToShow = nameToShow + "\n" + attributes.get("nickname");
                }
                leggereRecensioniController.notifyNameToShow(nameToShow, position);
            }

            @Override
            public void onFailure(Exception exception) {
                //niente da notificare
            }
        });
    }

    private void getUtenteLoggatoByUserId(String userId) {

        CognitoUser user = userPool.getUser(userId);

        user.getDetailsInBackground(new GetDetailsHandler() {
            @Override
            public void onSuccess(CognitoUserDetails cognitoUserDetails) {
                Map<String, String> attributes = cognitoUserDetails.getAttributes().getAttributes();

                Map<String, String> attributiUtenteLoggato = new HashMap<String, String>();
                attributiUtenteLoggato.put("name", attributes.get("name"));
                attributiUtenteLoggato.put("family_name", attributes.get("family_name"));
                attributiUtenteLoggato.put("nickname", attributes.get("nickname"));
                attributiUtenteLoggato.put("email", attributes.get("email"));
                attributiUtenteLoggato.put("phone_number", attributes.get("phone_number"));
                attributiUtenteLoggato.put("custom:isMod", attributes.get("custom:isMod"));
                attributiUtenteLoggato.put("custom:useNick", attributes.get("custom:useNick"));

                controllerLogin.setUtenteLogged(attributiUtenteLoggato);
            }

            @Override
            public void onFailure(Exception exception) {
                //Se il login è andato a buon fine, questa richiesta a cognito non può fallire
            }
        });
    }

    public void cambioPassword(String oldPsw, String psw, String userID, Context context) {
        CognitoUser user = userPool.getUser(userID);
        final GenericHandler handler = new GenericHandler() {
            @Override
            public void onSuccess() {
                Toast.makeText(context, "Password cambiata con successo", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Exception exception) {
                Toast.makeText(context, "Errore nel cambio della password\n" +
                        "controlla di aver scritto correttamente la vecchia Password", Toast.LENGTH_LONG).show();
            }
        };
        user.changePasswordInBackground(oldPsw, psw, handler);
    }

    public void cambioEmail(String email, String userID, Context context) {
        CognitoUser user = userPool.getUser(userID);
        CognitoUserAttributes userAttributes = new CognitoUserAttributes();
        userAttributes.addAttribute("email", email);
        user.updateAttributesInBackground(userAttributes, new UpdateAttributesHandler() {
            @Override
            public void onSuccess(List<CognitoUserCodeDeliveryDetails> attributesVerificationList) {
                Toast.makeText(context, "Email cambiata con successo in" + email, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Exception exception) {
                Toast.makeText(context, "Errore nel cambio della email", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void cambioCell(String numCell, String userID, Context context) {
        CognitoUser user = userPool.getUser(userID);
        CognitoUserAttributes userAttributes = new CognitoUserAttributes();
        userAttributes.addAttribute("phone_number", numCell);
        user.updateAttributesInBackground(userAttributes, new UpdateAttributesHandler() {
            @Override
            public void onSuccess(List<CognitoUserCodeDeliveryDetails> attributesVerificationList) {
                Toast.makeText(context, "Numero di Cellulare cambiato con successo in " + numCell, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Exception exception) {
                Toast.makeText(context, "Errore nel cambio del numero di Cellulare!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void cambioNick(Context context, String nuovoNick, String userID) {
        CognitoUser user = userPool.getUser(userID);
        CognitoUserAttributes userAttributes = new CognitoUserAttributes();
        userAttributes.addAttribute("nickname", nuovoNick);
        user.updateAttributesInBackground(userAttributes, new UpdateAttributesHandler() {
            @Override
            public void onSuccess(List<CognitoUserCodeDeliveryDetails> attributesVerificationList) {
                Toast.makeText(context, "Nickname cambiato con successo in " + nuovoNick, Toast.LENGTH_LONG).show();
                userAttributes.addAttribute("custom:useNick", String.valueOf(1));
                user.updateAttributesInBackground(userAttributes, new UpdateAttributesHandler() {
                    @Override
                    public void onSuccess(List<CognitoUserCodeDeliveryDetails> attributesVerificationList) {
                    }

                    @Override
                    public void onFailure(Exception exception) {
                    }
                });
            }

            @Override
            public void onFailure(Exception exception) {
                Toast.makeText(context, "Impossibile cambiare Nickname al momento...", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setUseNickFalse(Context context, String userID){
        CognitoUser user = userPool.getUser(userID);
        CognitoUserAttributes userAttributes = new CognitoUserAttributes();
        userAttributes.addAttribute("custom:useNick", String.valueOf(0));
        user.updateAttributesInBackground(userAttributes, new UpdateAttributesHandler() {
            @Override
            public void onSuccess(List<CognitoUserCodeDeliveryDetails> attributesVerificationList) {
            }

            @Override
            public void onFailure(Exception exception) {
            }
        });
    }

}
