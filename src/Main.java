/* todo: make trainer and battle classes, trainer holds indamons, up to 3,
     battle simulates between two trainers indamon fights + holds battlefield stats
     implement dodge rate (affected by speed) and crit rate (innate luck stat?)*/

public class Main {
    public static void main(String[] args) {
        //Defining Indamon krollson (The best Indamon)
        Indamon krollson = new Indamon("krollson", 60, 50, 20,1, false);
        Indamon peter = new Indamon ("Peter", 50, 30, 20, 2,false);

        //testing speed stat
        Indamon luxia = new Indamon("Luxia", 35, 20, 30, 16,false);
        Indamon fablorv = new Indamon("fablorv", 80,25,40, 0.5F,false);
        /*
        //perform fight within loop below
        System.out.println("|------------------------------------------------------|");
        battleLoop(krollson, peter);
        //reset both hp, then do kroll vs luxia
        peter.heal();
        krollson.heal();
        //perform fight within loop below
        System.out.println("|------------------------------------------------------|\n\n");
        battleLoop(krollson, luxia);
        */
        //reset both hp, then do luxia vs fablorv
        luxia.heal();
        krollson.heal();
        //perform fight within loop below
        System.out.println("|------------------------------------------------------|");
        //battleLoop(fablorv, luxia);
        //reset both hp, then do kroll vs fablorv
        luxia.heal();
        fablorv.heal();
        //perform fight within loop below
        System.out.println("|------------------------------------------------------|");
        battleLoop(fablorv, krollson);
    }

    private static void battleLoop(Indamon battler1, Indamon battler2) {
        boolean whoFirst = battler1.fasterThan(battler2);
        int round = 0;
        while(true){
            round++;
            System.out.println("|------------------------------------------------------|");
            System.out.println("|                      Round " + round);
            if (whoFirst) battler1.doAttack(battler2);
            else battler2.doAttack(battler1);
            if(battler2.isFainted() || battler1.isFainted()){break;}
            //System.out.println("
            if (whoFirst) battler2.doAttack(battler1);
            else battler1.doAttack(battler2);
            if(battler2.isFainted() || battler1.isFainted()){break;}
        }
    }
}
