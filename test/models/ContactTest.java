package models;

import org.junit.Test;

import static org.junit.Assert.*;

import static org.junit.Assert.*;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

/**
 * Created by Samsideen on 01/05/2017.
 */
public class ContactTest {

    @Test
    public void testContactModel(){

        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            @Override
            public void run() {

                Contact contact = new Contact();
                contact.name = "test sam";
                contact.phone = "12345555";
                contact.email = "test@g.com";

                contact.save();

                Contact contactFromDb = contact.find.byId(contact.id);

                assertThat(contactFromDb).isNotNull();

                assertThat(contactFromDb.name).isEqualTo(contact.name);
            }
        });
    }

}