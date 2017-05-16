package controllers;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.Contact;
import play.data.Form;
import play.libs.Json;
import play.mvc.BodyParser;
//import play.mvc.BodyParser.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;
import models.User;


/**
 * Created by Samsideen on 01/05/2017.
 */
@LogRequest
//@Authenticated(AgendaAuthenticator.class)
@Security.Authenticated(controllers.auth.Secured.class)
public class Agenda extends Controller{

    public static Result list(){
        List<Contact> contacts  = Contact.find.all();
        //return ok(views.html.index.render(contacts, User.find.byId(request().username())));

        return ok(views.html.list.render(contacts));
    }

    public static Result show(Long id){

        Contact contact = Contact.find.byId(id);

        if (contact == null){

            return notFound();
        }

        return ok(views.html.show.render(contact));
    }
    public static Result newContact(){
        Form<Contact> contactForm = Form.form(Contact.class);
        return ok(newContact.render(contactForm));
    }

    public static Result createContact(){
        Form<Contact> contactForm = Form.form(Contact.class).bindFromRequest();
        //System.out.println(contactForm);
        if (contactForm.hasErrors()){
            //return badRequest(views.html.newContact.render(contactForm));
            return badRequest(newContact.render(contactForm));

        }else{
            Contact contact = contactForm.get();
            contact.save();

            return redirect(routes.Agenda.list());

        }
    }
    @BodyParser.Of(BodyParser.Json.class)
    public static Result createContactJson(){

        JsonNode json = request().body().asJson();

        String name = json.findPath("name").textValue();
        String phone = json.findPath("phone").textValue();
        String email = json.findPath("email").textValue();

        ObjectNode result = Json.newObject();
        //System.out.println(email + "email");
        if (email == null || name == null || phone == null) {

            //System.out.println(email);
            result.put("Status", "Failed");
            return badRequest(result);
            //return badRequest("All fields must be filled!");
        }else{
            Contact contact = new Contact();
            contact.name = name;
            contact.phone = phone;
            contact.email = email;
            contact.save();

            result.put("Status","OK");

            return ok(result);
            //return ok("Contact Saved.");
        }

    }
}
