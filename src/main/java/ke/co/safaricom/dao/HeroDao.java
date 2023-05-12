package ke.co.safaricom.dao;

import ke.co.safaricom.config.Database;
import ke.co.safaricom.model.Hero;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class HeroDao {
    private final static Sql2o sql2o = Database.getDatabase();

    // SquadDao.getNumberOfHeroes(1) // SELECT * FROM heroes WHERE squad_id = :id
    public static void add(Hero newHero){
        try(Connection db = sql2o.open()){
            String sql = "INSERT INTO heroes (name, price, description) VALUES (:name, :price, :description);";
            db.createQuery(sql)
                    .bind(newHero)
                    .executeUpdate();
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
