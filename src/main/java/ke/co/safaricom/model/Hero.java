package ke.co.safaricom.model;

public class Hero {
    private int id;
    private String name;
    private int age;
    private int powerId;
    private int weaknessId; // SELECT name FROM weakness WHERE id = :weaknessId
    // WeaknessDao.getWeaknessNameById(int id)
    private int squadId;
}
/*
    - Add new hero
    - List all heroes
    - update hero
    - delete hero
 */
