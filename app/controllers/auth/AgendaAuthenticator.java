package controllers.auth;

import play.mvc.Http;
import play.mvc.Security.Authenticator;
import controllers.auth.BasicAuthHelper.BasicAuthUser;

/**
 * Created by Samsideen on 03/05/2017.
 */
public class AgendaAuthenticator extends Authenticator{

    @Override
    public String getUsername(Http.Context context) {

       BasicAuthUser basicAuthUser = BasicAuthHelper.auth(context);

       if(basicAuthUser == null){
           return null;
       }

       if(basicAuthUser.name.equals("Sam") && basicAuthUser.password.equals("Sam")){

           return basicAuthUser.name;
       }else{
           return null;
       }

    }
}
