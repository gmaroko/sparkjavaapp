package ke.co.safaricom.dao;

import ke.co.safaricom.config.Database;
import ke.co.safaricom.model.Product;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class ProductDao {
    private final static Sql2o sql2o = Database.getDatabase();
    public static void add(Product newProduct){
        try(Connection db = sql2o.open()){
            String sql = "INSERT INTO products (name, price, description) VALUES (:name, :price, :description);";
            db.createQuery(sql)
                    .bind(newProduct)
                    .executeUpdate();
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static List<Product> getAllProducts(){
        List<Product> output = null;
        try(Connection db = sql2o.open()){
            String sql = "SELECT * FROM products;";
            output = db.createQuery(sql).executeAndFetch(Product.class);
        } catch(Exception exception){
            System.out.println(exception.getMessage());
            return output;
        }
        return output;
    }
}
