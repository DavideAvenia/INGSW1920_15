package Services;

import DAO.UtenteDao;
import Entity.Utente;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.amazonaws.services.cognitoidp.model.*;
import com.amazonaws.util.StringUtils;
import okhttp3.*;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class AWSCognito implements UtenteDao {

    private final String USERPOOLID = "";
    private final String CLIENTID = "";
    private final String SECRET = "";
    private AWSCognitoIdentityProvider identityProvider;

    public AWSCognito() {
        ClasspathPropertiesFileCredentialsProvider propertiesFileCredentialsProvider = new ClasspathPropertiesFileCredentialsProvider();
        identityProvider = AWSCognitoIdentityProviderClientBuilder.standard().withCredentials(propertiesFileCredentialsProvider).withRegion(Regions.EU_WEST_1).build();
    }


    @Override
    public List<String> getAllUtenti(String filtro) {
        List<String> listaUtenti = new ArrayList<String>();

        ListUsersRequest listUsersRequest = new ListUsersRequest().withUserPoolId(USERPOOLID);
        ListUsersResult listUsersResult = identityProvider.listUsers(listUsersRequest);

        if (filtro.equals("Username")) {
            for (UserType user : listUsersResult.getUsers()) {
                listaUtenti.add(user.getUsername());
            }
        } else {
            for (UserType user : listUsersResult.getUsers()) {
                List<AttributeType> attributiUtente = user.getAttributes();

                /*A causa dell'ordine degli attributi non mantenuto tra i singoli utenti, risulta
                 * necessario controllare il nome di ogni attributo di ogni singolo utente*/
                for (int i = 0; i < attributiUtente.size(); i++) {
                    String nomeAttributo = attributiUtente.get(i).getName();
                    switch (filtro) {
                        case "Cognome":
                            if (nomeAttributo.equals("family_name")) {
                                listaUtenti.add(user.getUsername() + " " + attributiUtente.get(i).getValue());
                            }
                            break;
                        case "Email":
                            if (nomeAttributo.equals("email")) {
                                listaUtenti.add(user.getUsername() + " " + attributiUtente.get(i).getValue());
                            }
                            break;
                        case "Cellulare":
                            if (nomeAttributo.equals("phone_number")) {
                                listaUtenti.add(user.getUsername() + " " + attributiUtente.get(i).getValue());
                            }
                            break;
                        case "Moderatori":
                            if (nomeAttributo.equals("custom:isMod")) {
                                if (Integer.parseInt(attributiUtente.get(i).getValue()) == 1) {
                                    listaUtenti.add(user.getUsername());
                                }
                            }
                            break;
                    }
                }
            }
        }
        return listaUtenti;
    }

    @Override
    public Utente getUtenteByUserID(String username) {
        String tokens[] = username.split(" ");
        String userId = tokens[0];

        String nome = "", cognome = "", nickname = "", cellulare = "", email = "";
        boolean isMod = false, useNick = false;

        AdminGetUserRequest adminGetUserRequest = new AdminGetUserRequest().withUserPoolId(USERPOOLID).withUsername(userId);
        AdminGetUserResult userResult = identityProvider.adminGetUser(adminGetUserRequest);

        List<AttributeType> listaAttributiUtente = userResult.getUserAttributes();

        /*In cognito l'ordine degli attributi degli utenti non è cosistente. Utenti diversi hanno gli attributi
         * ordinati in maniera diversa: è necessario recuperare gli attributi per nome e non per indice*/
        for (int i = 0; i < listaAttributiUtente.size(); i++) {
            switch (listaAttributiUtente.get(i).getName()) {
                case "name":
                    nome = listaAttributiUtente.get(i).getValue();
                    break;
                case "family_name":
                    cognome = listaAttributiUtente.get(i).getValue();
                    break;
                case "nickname":
                    nickname = listaAttributiUtente.get(i).getValue();
                    break;
                case "phone_number":
                    cellulare = listaAttributiUtente.get(i).getValue();
                    break;
                case "email":
                    email = listaAttributiUtente.get(i).getValue();
                    break;
                case "custom:isMod":
                    if (Integer.parseInt(listaAttributiUtente.get(i).getValue()) == 1) {
                        isMod = true;
                    }
                    break;
                case "custom:useNick":
                    if (Integer.parseInt(listaAttributiUtente.get(i).getValue()) == 1) {
                        useNick = true;
                    }
                    break;
            }
        }

        Utente utente = new Utente(userId, nome, cognome, nickname, cellulare, email, useNick, isMod);

        return utente;
    }

    @Override
    public boolean aggiornaUtente(Utente utente) {

        Collection<AttributeType> attributeTypes = new ArrayList<AttributeType>();
        AttributeType nome = new AttributeType();
        AttributeType cognome = new AttributeType();
        AttributeType nickname = new AttributeType();
        AttributeType email = new AttributeType();
        AttributeType cellulare = new AttributeType();
        AttributeType isMod = new AttributeType();
        AttributeType useNick = new AttributeType();

        nome.setName("name");
        nome.setValue(utente.getNome());
        cognome.setName("family_name");
        cognome.setValue(utente.getCognome());
        nickname.setName("nickname");
        nickname.setValue(utente.getNickname());
        email.setName("email");
        email.setValue(utente.getEmail());
        cellulare.setName("phone_number");
        cellulare.setValue(utente.getCellulare());
        isMod.setName("custom:isMod");
        if (utente.isMod()) {
            isMod.setValue("1");
        } else {
            isMod.setValue("0");
        }
        useNick.setName("custom:useNick");
        if (utente.isUseNick()) {
            useNick.setValue("1");
        } else {
            useNick.setValue("0");
        }

        Collection<AttributeType> attributiUtente = new ArrayList<AttributeType>();
        attributiUtente.add(nome);
        attributiUtente.add(cognome);
        attributiUtente.add(nickname);
        attributiUtente.add(email);
        attributiUtente.add(cellulare);
        attributiUtente.add(isMod);
        attributiUtente.add(useNick);

        AdminUpdateUserAttributesRequest request = new AdminUpdateUserAttributesRequest().withUsername(utente.getUserId()).withUserPoolId(USERPOOLID).withUserAttributes(attributiUtente);

        try {
            identityProvider.adminUpdateUserAttributes(request);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean cancellaUtente(String utente) {
        AdminDeleteUserRequest adminDeleteUserRequest = new AdminDeleteUserRequest().withUserPoolId(USERPOOLID).withUsername(utente);
        identityProvider.adminDeleteUser(adminDeleteUserRequest);
        return true;
    }

    @Override
    public boolean effettuaLogin(String email, String password) {
        AuthenticationResultType authenticationResult = null;
        AdminInitiateAuthRequest request = new AdminInitiateAuthRequest();

        SecretKeySpec signingKey = new SecretKeySpec(
                SECRET.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256");

        String SECRET_HASH = new String();

        try {
            final Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            mac.update(email.getBytes(StringUtils.UTF8));
            final byte[] rawHmac = mac.doFinal(CLIENTID.getBytes(StringUtils.UTF8));
            SECRET_HASH = Base64.getEncoder().encodeToString(rawHmac);
        } catch (final Exception e) {
            throw new RuntimeException("errors in HMAC calculation");
        }


        final Map authMap = new HashMap<String, String>();
        authMap.put("USERNAME", email);
        authMap.put("PASSWORD", password);
        authMap.put("SECRET_HASH", SECRET_HASH);


        final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
        authRequest.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .withClientId(CLIENTID)
                .withUserPoolId(USERPOOLID)
                .withAuthParameters(authMap);

        try {
            AdminInitiateAuthResult result = identityProvider.adminInitiateAuth(authRequest);
            incrementaLoginCounter(email);
            return true;
        } catch (UserNotFoundException e) {
            System.out.println("Siamo nel catch");
            return false;
        } catch (NotAuthorizedException e){
            return false;
        }

    }

    public void incrementaLoginCounter(String email) {
        Thread tIncrement = new Thread(() -> {
            JSONObject tmp = new JSONObject();
            tmp.put("username", email);

            OkHttpClient client = new OkHttpClient();
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody requestBody = RequestBody.create(JSON, tmp.toString());
            Request request = new Request.Builder().url(APIINCREMENTALOGINCOUNTER).post(requestBody).build();
            Response res = null;

            try {
                res = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Thread finito");
        });

        tIncrement.start();
    }
}
