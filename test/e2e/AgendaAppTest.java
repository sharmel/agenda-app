package e2e;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import org.junit.Test;

import play.libs.WS;
import play.mvc.Result;

import static play.libs.F.Function;
import static play.libs.F.Promise;

public class AgendaAppTest {

    @Test
    public void testNewContactFormPage() {
        running(testServer(6666), new Runnable() {
            public void run() {
                assertThat(
                        WS.url("http://localhost:6666").get().get().getStatus()
                ).isEqualTo(OK);
            }
        });
    }

}
