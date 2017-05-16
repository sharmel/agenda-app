import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.libs.F.*;
import play.api.mvc.EssentialFilter;
import play.GlobalSettings;
import play.filters.csrf.CSRFFilter;
import static play.mvc.Results.*;

public class Global extends GlobalSettings {

    public Promise<SimpleResult> onHandlerNotFound(RequestHeader request) {
        return Promise.<SimpleResult>pure(notFound(
                views.html.notFound.render()
        ));
    }
    @Override
    public <T extends EssentialFilter> Class<T>[] filters() {
        return new Class[]{CSRFFilter.class};
    }

}