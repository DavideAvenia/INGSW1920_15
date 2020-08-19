package Services;

import DAO.UtenteDao;
import Entity.Utente;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;

import java.util.ArrayList;
import java.util.Collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AWSCognito implements UtenteDao {

    private final String USERPOOLID = "eu-west-1_KWhWZTu1x";
    private final String CLIENTID = "66eho5mi1f4ift40cjtmvo03id";
    private AWSCognitoIdentityProvider identityProvider;

    public AWSCognito() {
        ClasspathPropertiesFileCredentialsProvider propertiesFileCredentialsProvider = new ClasspathPropertiesFileCredentialsProvider();
        identityProvider = AWSCognitoIdentityProviderClientBuilder.standard().withCredentials(propertiesFileCredentialsProvider).withRegion(Regions.EU_WEST_1).build();
    }


    @Override
    public List<String> getAllUtenti(String filtro) {
        List<String> listaUsername = new ArrayList<String>();

        ListUsersRequest listUsersRequest = new ListUsersRequest().withUserPoolId(USERPOOLID);
        ListUsersResult listUsersResult = identityProvider.listUsers(listUsersRequest);

        if (filtro.equals("Username")) {
            for (UserType user : listUsersResult.getUsers()) {
                listaUsername.add(user.getUsername());
            }
        } else {
            switch (filtro) {
                case "Cognome":
                    break;
                case "Email":
                    break;
                case "Cellulare":
                    break;
            }
        }
        return listaUsername;
    }

    @Override
    public Utente getUtenteByUserID(String username) {
        AdminGetUserRequest adminGetUserRequest = new AdminGetUserRequest().withUserPoolId(USERPOOLID).withUsername(username);

        AdminGetUserResult userResult = identityProvider.adminGetUser(adminGetUserRequest);
        
        List<AttributeType> listaAttributiUtente = userResult.getUserAttributes();

        String tokens[] = username.split("_");

        if(tokens[0].equals("admin")){
            String cellulare = listaAttributiUtente.get(3).getValue();
            String email = listaAttributiUtente.get(4).getValue();
            return new Utente(username,cellulare,email);
        }

        String nome = listaAttributiUtente.get(5).getValue();
        String cognome = listaAttributiUtente.get(8).getValue();
        String nickname = listaAttributiUtente.get(6).getValue();
        String cellulare = listaAttributiUtente.get(7).getValue();
        String email = listaAttributiUtente.get(10).getValue();
        boolean isMod = false;
        boolean useNick = false;

        if (Integer.parseInt(listaAttributiUtente.get(4).getValue()) == 1) {
            isMod = true;
        }
        if (Integer.parseInt(listaAttributiUtente.get(9).getValue()) == 1) {
            useNick = true;
        }
        Utente utente = new Utente(username, nome, cognome, nickname, cellulare, email, useNick, isMod);

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
        if(utente.isMod()){
            isMod.setValue("1");
        }else{
            isMod.setValue("0");
        }
        useNick.setName("custom:useNick");
        if(utente.isUseNick()){
            useNick.setValue("1");
        }else{
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

        identityProvider.adminUpdateUserAttributes(request);

        return true;
    }

    @Override
    public boolean cancellaUtente(String utente) {

        AdminDeleteUserRequest adminDeleteUserRequest = new AdminDeleteUserRequest().withUserPoolId(USERPOOLID).withUsername(utente);

        identityProvider.adminDeleteUser(adminDeleteUserRequest);
        return true;
    }

    @Override
    public void effettuaLogin(String email, String password) {
        AdminInitiateAuthRequest request = new AdminInitiateAuthRequest();
        final Map authMap = new HashMap<String,String>();
        authMap.put("username", email);
        authMap.put("password", password);

        request.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .withUserPoolId(USERPOOLID)
                .withClientId(CLIENTID)
                .withAuthParameters(authMap);

        AdminInitiateAuthResult authenticationResult = identityProvider.adminInitiateAuth(request);

        AuthenticationResultType authenticationResultType = authenticationResult.getAuthenticationResult();

        //da finire, cosa serve il type?
    }
}