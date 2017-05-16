package controllers.auth;
import play.*;
import static play.data.Form.*;
import play.data.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import models.*;
import org.mindrot.jbcrypt.BCrypt;


/**
 * Created by Samsideen on 12/05/2017.
 */
public class User extends Controller {

    public static class Login {

        public String email;
        public String password;

        public String validate() {
            if (models.User.authenticate(email, password) == null) {
                return "Invalid email or password";
            }
            return null;
        }

    }
    public static Result login(){

        return ok(
                login.render(Form.form(Login.class))
        );

    }
    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    controllers.routes.Agenda.list()
            );
        }
    }
    /**
     * Logout and clean the session.
     */
    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
                routes.User.login()
        );
    }
    public static Result signup(){

        Form<models.User> signUpForm = Form.form(models.User.class);
        return ok(signup.render(signUpForm));
    }
    public static Result newUser(){
        Form<models.User> signUpForm = Form.form(models.User.class).bindFromRequest();

        if (signUpForm.hasErrors()){

            flash("error", "All the fields are required");
            return badRequest(signup.render(signUpForm));

        }else{

            models.User user = new models.User(signUpForm.get().email, signUpForm.get().username, BCrypt.hashpw(signUpForm.get().password, BCrypt.gensalt()));

            //models.User user = signUpForm.get();
            user.save();
            return redirect(routes.User.login());

        }
    }



}

