import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Indamon {
    //defining variables
    private final String name;
    private float maxHP;
    private float attack;
    private float defense;
    private float speed;
    private float currHP;
    private boolean fainted;
    //defining required getters
    public float getAttack(){
        return this.attack;
    }
    public float getDefense(){
        return this.defense;
    }
    public float getCurrHp(){
        return this.currHP;
    }
    public float getMaxHP(){
        return this.maxHP;
    }
    public float getSpeed(){
        return this.speed;
    }
    public String getName(){
        return this.name;
    }
    public boolean isFainted(){
        return this.fainted;
    }

    //statcheck
    public boolean fasterThan(Indamon x) {
        return this.getSpeed() >= x.getSpeed();
    }

    //setters
    public void setHp(float newHP){
        this.currHP = newHP;
    }
    public void heal(){
        this.setHp(this.getMaxHP());
    }

    //attack cycle
    public void doAttack(Indamon target){
        float aAttack = this.getAttack();
        float bDefense = target.getDefense();
        //default to one if damage is less
        float attackDamage = Math.max(aAttack / bDefense, 1);
        //num of hits = binary log of hitter speed/target speed, minimum 1.
        int numHits = Math.max(log2(this.getSpeed() / target.getSpeed()), 1);
        int holdHits = numHits;

        //do the damage, then print
        do{hit(target, attackDamage); numHits--;}
        while(numHits > 0 && target.getCurrHp() != 0);

        printDamage(target, attackDamage, holdHits);

        //if target faints after strike
        if (target.getCurrHp() <= 0.0) {
            target.fainted = true;
            System.out.println("| Indamon " + target.getName() + " has fainted!");
        }
    }
    //affecting healthbar
    private static void hit(Indamon target, float damage){
        if(target.getCurrHp() <= damage){
            target.setHp(0);
        }else {
            target.setHp(target.getCurrHp() - damage);
        }
    }
    //defining constructor for general Indamon
    public Indamon(String name, float hp, float attack, float defense, float speed, boolean fainted){
        this.name = name;
        this.maxHP = hp;
        this.currHP = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.fainted = fainted;
    }
    //necessary math
    private static int log2(float x){
        return (int) (Math.log(x) / Math.log(2));
    }

    //prints
    public void printInfo(){
        System.out.println("INFO");
        System.out.println("Indamon: " + this.name);
        System.out.println("Max HP: " + this.maxHP);
        System.out.println("Current HP: " + this.currHP);
        System.out.println("Attack: " + this.attack);
        System.out.println("Defense: " + this.defense);
        System.out.println("Speed: " + this.speed);
        System.out.println("Fainted: " + this.fainted);
    }
    public void printDamage(Indamon target, float dmg, int num){
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.HALF_EVEN);

        String tn = target.getName();
        String hn = this.getName();
        String dm = df.format(dmg);
        if(num > 1){
            System.out.printf("| Indamon %s hit %s for %s damage (%d times)! \n", hn, tn, dm, num);
        }else{
            System.out.printf("| Indamon %s hit %s for %s damage! \n", hn, tn, dm);
        }
        System.out.println("| Indamon " + tn + " has " + df.format(target.getCurrHp()) + " HP left.");
        System.out.println("|         -----------------------------------          |");

    }
}