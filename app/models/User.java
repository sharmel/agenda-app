package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by Samsideen on 12/05/2017.
 */
@Entity
public class User extends Model{

    @Id
    @GeneratedValue

    public Long id;
    @Constraints.Required
    @Constraints.Email
    public String email;

    @Constraints.Required
    @Column(unique = true)
    public String username;

    @Constraints.Required
    public String password;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }


    public static Finder<Long,User> find = new Finder<Long,User>(
            Long.class, User.class
    );

    public static User authenticate(String email, String password) {

        User user = User.find.where().eq("email", email).findUnique();

        if (user != null && BCrypt.checkpw(password, user.password)) {
            return user;
        } else {
            return null;
        }
    }
}
