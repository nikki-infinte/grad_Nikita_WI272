
import java.util.*;

class Player1 extends Thread {

    int currKm = 0;
    int target = 0;

    public Player1(String name) {
        super(name);
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public void run() {
        while (currKm < target) {

            currKm += 100;
            System.out.println(getName() + " covered " + currKm + " meters");

        }

    }

    public boolean isReached() {
        return currKm >= target;
    }

}

class Player2 extends Thread {

    int currKm, target;

    public Player2(String name) {
        super(name);
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public void run() {
        while (currKm < target) {

            currKm += 100;
            System.out.println(getName() + " covered " + currKm + " meters");

        }

    }

    public boolean isReached() {
        return currKm >= target;
    }

}

class Player3 extends Thread {

    int currKm, target;

    public Player3(String name) {
        super(name);
    }

    public void run() {
        while (currKm < target) {

            currKm += 100;
            System.out.println(getName() + " covered " + currKm + " meters");

        }

    }

    public void setTarget(int target) {
        this.target = target;
    }

    public boolean isReached() {
        return currKm >= target;
    }

}

class Player4 extends Thread {

    int currKm, target;

    public Player4(String name) {
        super(name);
    }

    public void run() {
        while (currKm < target) {

            currKm += 100;
            System.out.println(getName() + " covered " + currKm + " meters");

        }

    }

    public void setTarget(int target) {
        this.target = target;
    }

    public boolean isReached() {
        return currKm == target;
    }

    public int getCurrentCoveredDistance() {
        return currKm;
    }

}

class Player5 extends Thread {

    int currKm, target;

    public Player5(String name) {
        super(name);
    }

    public void run() {
        while (currKm < target) {
            try {
                Thread.sleep((int) (Math.random() * 400) + 100);
            } catch (InterruptedException e) {
                currKm += 100;
                System.out.println(getName() + " covered " + currKm + " meters");
            }
        }

    }

    public void setTarget(int target) {
        this.target = target;
    }

    public boolean isReached() {
        return currKm == target;
    }

    public int getCurrentCoveredDistance() {
        return currKm;
    }

}

public class BikeRacingMain {

    public static void displayWinners(String winners[]){

        System.out.println("--------------------Winners are ------------------");
        for(int i=0;i<3;i++){
            System.out.println((i+1)+" : "+winners[i]);
        }
    }

    public static void main(String[] args) {

        System.out.println("------------- Bike Racing  ----------------------");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the 10 biker name");
        System.out.println("Enter Player 1 Name");
        Player1 p1 = new Player1(sc.nextLine());
        System.out.println("Enter Player 2 Name");
        Player2 p2 = new Player2(sc.nextLine());

        System.out.println("Enter Player 3 Name");
        Player3 p3 = new Player3(sc.nextLine());

        System.out.println("Enter Player 4 Name");
        Player4 p4 = new Player4(sc.nextLine());

        System.out.println("Enter Player 5 Name");
        Player5 p5 = new Player5(sc.nextLine());

        System.out.println("Enter the Distance need to cover in Kilometer");
        int km = sc.nextInt();
        km *= 1000;

        p1.setTarget(km);
        p2.setTarget(km);
        p3.setTarget(km);
        p4.setTarget(km);
        p5.setTarget(km);

        System.out.println("--------------Lets start the race---------------");

        p1.start();
        p2.start();
        p4.start();
        p3.start();
        p5.start();

        int winnerCount = 3;
        boolean p1Done = false, p2Done = false, p3Done = false, p4Done = false, p5Done = false;
        String winners[]=new String[3];
        int winIndex =0;
        while (winnerCount > 0) {

            if (p1.isReached() && !p1Done) {
                System.out.println(p1.getName() + " finished!");
                p1Done = true;
                winnerCount--;
                winners[winIndex++]=p1.getName();

            }
            if (p2.isReached() && !p2Done) {
                System.out.println(p2.getName() + " finished!");
                p2Done = true;
                winnerCount--;
                winners[winIndex++]=p2.getName();
            }
            if (p3.isReached() && !p3Done) {
                System.out.println(p3.getName() + " finished!");
                p3Done = true;
                winnerCount--;
                winners[winIndex++]=p3.getName();
            }
            if (p4.isReached() && !p4Done) {
                System.out.println(p4.getName() + " finished!");
                p4Done = true;
                winnerCount--;
                winners[winIndex++]=p4.getName();
            }
            if (p5.isReached() && !p5Done) {
                System.out.println(p5.getName() + " finished!");
                p5Done = true;
                winnerCount--;
                winners[winIndex++]=p5.getName();
            }

        }


        displayWinners(winners);



    }

}
