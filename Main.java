package Function_predictor;
import java.util.Vector;
import java.util.*;
import java.util.Scanner;


public class Main {
    /// X store all values of xi s
    public static Vector<Double> X = new Vector<Double> (10, 10);
    /// Scanner class
    public static Scanner sc = new Scanner(System.in);
    /// store polynomials 
    public static Vector<Polynomials> Pols = new Vector<Polynomials> (10, 10);
    /// Final Polynomial
    public static Polynomials Final = null;


    /**
     * Update Pols and X on new input
     *
     * @param      x     value of x
     * @param      y     value of f(x)
     */
    public static void addNewTerms(double x, double y) {
        Polynomials P = new Polynomials(0);
        P.setCoef(0, y);
        for (int i = 0 ; i < Pols.size(); i++) {
            Polynomials E = new Polynomials(1);
            E.setCoef(0, -x);
            E.setCoef(1, 1.0);
            E = E.scalerProduct(1 / (X.get(i) - x));
            E = (Pols.get(i)).Multply(E);
            Pols.set(i, E);
            Polynomials F = new Polynomials(1);
            F.setCoef(0, -X.get(i));
            F.setCoef(1, 1.0);
            F = F.scalerProduct(1 / (x - X.get(i)));
            P = P.Multply(F);
        }

        Pols.add(P);
        X.add(x);
    }


    /**
     * Update Final Polynomial and Print it
     */
    public static void printFnction() {
        Polynomials P = new Polynomials(0);
        for (int i = 0 ; i < Pols.size() ; i++) {
            P = P.add(Pols.get(i));
        }
        Final = P;
        P.PrintPolynomial();
    }


    public static void main(String[] args) {

        /// take inputs from user
        while (true) {
            double x , y;
            System.out.println("enter the value of x and f(x)");
            String line = sc.nextLine();

            /// breaks loop 
            if (line.equals("DONE")) break;
            String [] s = line.split(" ");
            x = Double.parseDouble(s[0]);
            y = Double.parseDouble(s[1]);

            /// check if x is already in X
            if (X.contains(x)) {
                System.out.println("Already Exist");
                continue;
            }
            addNewTerms(x, y);
            /// Print Updated Final Function
            printFnction();
        }

        /// Take x value from user 
        /// and return f(x)
        while (true){
            double x;
            System.out.println("enter the value of x");
            String line = sc.nextLine();
            /// exit
            if (line.equals("EXIT")) break;
            x = Double.parseDouble(line);
            System.out.println(Final.FunctionValue(x));
        }

    }
}
