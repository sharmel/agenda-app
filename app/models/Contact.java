package models;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Samsideen on 01/05/2017.
 */

@Entity
public class Contact extends Model {


    @Id
    @GeneratedValue

    public Long id;

    @Required
    public String name;

    @Required
    public String phone;

    @Required
    @Email
    public String email;

    public static Finder<Long, Contact> find = new Finder<Long, Contact>(Long.class, Contact.class);

}
