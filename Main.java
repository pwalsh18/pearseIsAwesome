//Pearse Walsh Pitt Project #1

package Pitt;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {


        String password = "euthanize all squibs";

        System.out.println("Note 1: We appreciate exact change!\n\n" +
                "Note 2: Operator does not have more than 20 Galleons on the cart at any time!\n\n" +
                "Note 3: Recall our currency options:\n" +
                "       29 Knuts == 1 Sickle\n" +
                "       17 Sickles == 1 Galleon == 493 Knuts\n\n\n");

        for (; ; ) {

            boolean d = false;
            int cake = 0, bag = 0, frog = 0, sbeer = 0, lbeer = 0;
            int cake_p = 10, bag_p = 55, frog_p = 20, sbeer_p = 50, lbeer_p = 75;
            //PRICES: cake price, cake deal, frog price, beer prices respectively


            System.out.println("Welcome to Dumbledore's Delicious Delicacies!\n[Hogsmeade Convenience Location]");
            boolean ans = true;

            System.out.print("Is there a customer in line? (true / false) > ");

            ans = input.nextBoolean();
            input.nextLine(); //Throwaway to deal with issues presented by nextBoolean
            if (!ans)
                System.exit(2);


            for (int i = 0; i < 2; i++) {
                System.out.print("What is the password? ");
                String p = input.nextLine();
                if (p.equalsIgnoreCase(password)) {
                    System.out.println("\nWelcome Dumbledore's Army member!\n" +
                            "You will get special discounts at DDD!\n" +
                            "        5 Knuts off Cauldron Cake Bags!\n" +
                            "        5 Knuts off Chocolate Frogs! \n" +
                            "        Large Butterbeer is 50 Knuts too!\n" +
                            "        AND 10% the whole order!!! W O W !");
                    d = true;
                    break;
                } else if (i == 0) {
                    System.out.println("Sorry but that is not right.  We will give you one more chance.");
                } else {
                    System.out.println("\nPlease enjoy our items at their regular prices.");
                }


            }

            if (d) {
                bag_p -= 5;
                frog_p -= 5;
                lbeer_p = sbeer_p;
            }

            showPrice(cake_p, bag_p, frog_p, sbeer_p, lbeer_p);
            boolean loop = true;

            while (loop) {
                System.out.println("Please choose an option:\n" +
                        "        1) Update Cauldron Cakes Order\n" +
                        "               (order by the cake, not by the bag)\n" +
                        "        2) Update Chocolate Frogs Order\n" +
                        "        3) Update Butterbeer Order\n" +
                        "        4) Show price list\n" +
                        "        5) Check Out");
                String option = input.nextLine();
                int o = Integer.parseInt(option);
                switch (o) {
                    case 1:
                        cake = getStuff();
                        break;
                    case 2:
                        frog = getStuff();
                        break;
                    case 3:
                        int size = 0;
                        System.out.println("Small or Large? (1 = small, 2 = large) > ");
                        size = input.nextInt();
                        switch (size) {
                            case 1:
                                sbeer = getStuff();
                                break;
                            case 2:
                                lbeer = getStuff();
                                break;
                            default:
                                System.out.println("error");
                                break;
                        }
                    case 4:
                        showPrice(cake_p, bag_p, frog_p, sbeer_p, lbeer_p);
                        break;
                    case 5:
                        loop = false;
                        break;
                    default:
                        break;
                }

            }

            bag = cake / 6;
            cake %= 6;
            int total = 0;
            System.out.println("Here is your subtotal:\n");
            if (bag > 0) {
                System.out.println(bag + " Bag(s) of Cauldron Cakes at " + bag_p + " Knuts ea.:" +
                        "\t\t\t" + (bag * bag_p) + " Knuts");
                total += (bag * bag_p);
            }
            if (cake > 0) {
                System.out.println(cake + " Single Cauldron Cake(s) at " + cake_p + " Knuts ea.:" +
                        "\t\t\t" + (cake * cake_p) + " Knuts");
                total += (cake * cake_p);
            }
            if (frog > 0) {
                System.out.println(frog + " Chocolate Frog(s) at " + frog_p + " Knuts ea.:" +
                        "\t\t\t\t" + (frog * frog_p) + " Knuts");
                total += (frog * frog_p);
            }
            if (sbeer > 0) {
                System.out.println(sbeer + " Small Butterbeer(s) at " + sbeer_p + " Knuts ea.:" +
                        "\t\t\t\t" + (sbeer * sbeer_p) + " Knuts");
                total += (sbeer * sbeer_p);
            }
            if (lbeer > 0) {
                System.out.println(lbeer + " Large Butterbeer(s) at " + lbeer_p + " Knuts ea.:" +
                        "\t\t\t\t" + (lbeer * lbeer_p) + " Knuts");
                total += (lbeer * lbeer_p);
            }
            if (d) {
                System.out.println("DISCOUNT" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t" + (-.1 * total) + " Knuts");
                total *= .9;
            }
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t---\n" +
                    "Total: \t\t\t\t\t\t\t\t\t\t\t\t" + total);

            System.out.println("Please enter a payment amount in the following format:\n" +
                    "        <amount><space><currency>\n" +
                    "                Where <amount> = an integer\n" +
                    "                Where <space> = a blank space\n" +
                    "                Where <currency> = {Knuts, Sickles, Galleons}\n" +
                    "        You may enter as many times as you like.  Each entry will be\n" +
                    "        added to your total until sufficient funds have been obtained.\n" +
                    "        Recall the currency exchange:\n" +
                    "                29 Knuts = 1 Sickle\n" +
                    "                493 Knuts = 17 Sickles = 1 Galleon\n");


            int bal = 0;
            while (bal < total) {
                System.out.println("Payment > ");
                String s = input.nextLine();
                int x = ((Number) NumberFormat.getInstance().parse(s)).intValue();

                if (s.indexOf("Galleon", 0) != -1) {
                    x *= 493;
                } else if (s.indexOf("Sickle", 0) != -1) {
                    x *= 29;
                } else if (s.indexOf("Knut", 0) == -1) continue;

                bal += x;
                System.out.println("You have added " + x + " Knuts to your total" +
                        "You have paid " + bal + " out of " + total + " Knuts");
            }
            System.out.println("Thank you!");
            if (bal > total) {
                System.out.println("You have overpaid by " + (bal - total) + " Knuts\n" +
                        "Here is your change: ");
                int dif = (bal - total);
                int g = dif / 493;
                dif -= (g * 493);
                int s = dif / 29;
                dif -= (s * 29);
                if (g > 0) {
                    System.out.println(g + " Galleon(s)");
                }
                if (s > 0) {
                    System.out.println(s + " Sickles(s)");
                }
                if (dif > 0) {
                    System.out.println(dif + " Knut(s)");
                }

            }
            System.out.println("Thank you for shopping at DDD!\n\n\n");
        }
    }

    public static void showPrice(int cake_p, int bag_p, int frog_p, int sbeer_p, int lbeer_p) {
        System.out.print("\nHere is our price list:\n" +
                "        Cauldron Cake (single)\t\t\t" + cake_p + " Knuts\n" +
                "               only " + bag_p + " Knuts for a bag of 6" +
                "\n        Chocolate Frogs (each)\t\t\t" + frog_p + " Knuts" +
                "\n        Butterbeer (small)\t\t\t\t" + sbeer_p + " Knuts" +

                "\n        Butterbeer (large)\t\t\t\t" + lbeer_p + " Knuts\n\n");

    }

    public static int getStuff() {
        int x;
        for (; ; ) {
            System.out.println("How many? (keep in mind this will overwrite any previous amount) > ");
            x = input.nextInt();
            input.nextLine();
            if (x > 0)
                break;
        }
        return x;
    }

}

