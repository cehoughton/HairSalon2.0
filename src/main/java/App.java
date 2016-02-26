import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
      staticFileLocation("/public");
          String layout = "templates/layout.vtl";

          get("/", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylists.all());
            // model.put("clients", Clients.all());
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());

          post("/", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("stylistname");
             Stylists newstylist = new Stylists(name);
             newstylist.save();
             response.redirect("/");
             return null;
          });

          post("/delete-stylist", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            Stylists stylist = Stylists.find(Integer.parseInt(request.queryParams("id-stylist")));
            stylist.delete();
            model.put("stylist", stylist);
            response.redirect("/");
            return null;
          });

          post("/update-stylist", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            String newName = request.queryParams("newName-stylist");
            Stylists stylist = Stylists.find(Integer.parseInt(request.queryParams("id-stylist")));
            stylist.update(newName);
            model.put("stylist", stylist);
            response.redirect("/");
            return null;
          });

        //RESTful ARCHITECTURE
        //Use POST to create something on the server
        //Use GET to retrieve something from the server
        //Use PUT to change or update something on the server
        //Use DELETE to remove or delete something on the server
        //Keep URLs intuitive
        //Each request from client contains all info necessary for that request

        //ROUTES: Home Page

        // get("/", (request, response) -> {
        //     HashMap<String, Object> model = new HashMap<String, Object>();

        //     model.put("template", "templates/index.vtl");
        //     return new ModelAndView(model, layout);
        // }, new VelocityTemplateEngine());

        //ROUTES: Identification of Resources

        //ROUTES: Changing Resources

    }
}
