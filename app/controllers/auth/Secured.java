package controllers.auth;


import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import models.*;

/**
 * Created by Samsideen on 15/05/2017.
 */

public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.User.login());
    }
}