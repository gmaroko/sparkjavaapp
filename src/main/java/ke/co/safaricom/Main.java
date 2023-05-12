package ke.co.safaricom;

import ke.co.safaricom.config.Database;
import ke.co.safaricom.dao.ProductDao;
import ke.co.safaricom.model.Product;
import org.sql2o.Connection;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        staticFileLocation("/public");

        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

        get("/add", (req, res)->{
            return new ModelAndView(new HashMap<>(), "add.hbs");
        }, engine);

        post("/add-product", (req, res)->{
            String name = req.queryParams("name");
            double price = Double.parseDouble(req.queryParams("price")); // parseInt
            String description = req.queryParams("description");
            Product newProduct = new Product(name, description, price);
            ProductDao.add((newProduct));
            res.redirect("/");
            return null;
        }, engine);


        get("/", (req, res)->{
            List<Product> output = ProductDao.getAllProducts();
            Map<String, List<Product>> payload = new HashMap<>();
            payload.put("listOfProducts", output);
            return new ModelAndView(payload, "home.hbs");
        }, engine);

        get("/about-us", (req, res)->{
            return new ModelAndView(new HashMap<>(), "about.hbs");
        }, engine);

        get("/delete/:name", (req, res)->{
            String name = req.params().get(":name");
            // TODO - move below logic to DAO class
            try(Connection db = Database.getDatabase().open()){
                String sql = "DELETE FROM products WHERE name= :name;";
                 db.createQuery(sql)
                         .addParameter("name", name)
                         .executeUpdate();
            } catch(Exception exception){
                System.out.println(exception.getMessage());
            }
                res.redirect("/");
            return null;
        }, engine);

    }
}