package controllers;

import play.libs.F.Function;
import play.libs.WS;
import play.libs.WS.Response;
import play.mvc.*;
import play.mvc.Result;
import static play.libs.F.Promise;


import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return redirect(routes.Agenda.list());
    }

    //public static Result externalWS(){
        //This is syncronous result ie if this request take so long to respond, other request will have to wait.
        //return ok(WS.url("https://www.googleapis.com/books/v1/volumes").setQueryParameter("q","isbn:0747532699").get().get().asJson().findPath("publisher"));

        //This is using asynchronous. ie eternal request will not stop other request being made

//        return async(WS.url("https://www.googleapis.com/books/v1/volumes").setQueryParameter("q", "isbn:0747532699").get().map(new Function<WS.Response, Result>() {
//
//            @Override
//            public Result apply(Response response) throws Throwable {
//                return ok(response.asJson().findPath("publisher"));
//            }
//        }));
//
//    }
    public static Promise<Result> externalWS() {
        final Promise<Result> resultPromise = WS.url("https://www.googleapis.com/books/v1/volumes").setQueryParameter("q", "isbn:0747532699").get().map(
                new Function<WS.Response, Result>() {
                    public Result apply(WS.Response response) {
                        return ok("Book publisher:" + response.asJson().findPath("publisher"));
                    }
                }
        );
        return resultPromise;
    }

}
