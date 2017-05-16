package controllers;

import play.Logger;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.SimpleResult;


/**
 * Created by Samsideen on 03/05/2017.
 */
public class LogRequestAction extends Action<LogRequest> {
    @Override
    public F.Promise<SimpleResult> call(Http.Context context) throws Throwable {

            Logger.info("Calling action for " + context);
        return delegate.call(context);
    }
}
