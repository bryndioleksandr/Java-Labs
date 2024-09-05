import java.util.Date;
import java.util.Scanner;

public class Calc {
    private double m;
    private double c;
    private double t;
    private double b;

    public void setB(double b) {
        this.b = b;
    }
    public double getB() {
        return b;
    }
    public void setM(double m) {
        this.m = m;
    }
    public double getM() {
        return m;
    }
    public void setC(double c) {
        this.c = c;
    }
    public double getC() {
        return c;
    }
    public void setT(double t) {
        this.t = t;
    }
    public double getT() {
        return t;
    }

    public void showDate(){
        Date d = new Date();
        System.out.printf("Зараз %1$te %1$tB %1$tY %1$tk:%1$tM:%1$tS:%1$tL", d);
    }

    public void inputData(){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter an value for b: ");
        this.setB(sc.nextDouble());
        System.out.print("Enter an value for c: ");
        this.setC(sc.nextDouble());
        System.out.print("Enter an value for m: ");
        this.setM(sc.nextDouble());
        System.out.print("Enter an value for t: ");
        this.setT(sc.nextDouble());
    }

    public void showData(){
        System.out.print("Value of b:" + this.getB() + "\n");
        System.out.print("Value of m:" + this.getM() + "\n");
        System.out.print("Value of c:" + this.getC() + "\n");
        System.out.print("Value of t:" + this.getT() + "\n");
    }

    public void calcF(){
        double f;
        f = Math.pow(this.getB() * this.getT() * this.getM() * this.getT() + Math.abs(this.getC() * Math.sin(this.getT())), 1.0/3.0);
        System.out.print("\nCalc of F: " + f);
    }

    public void calcZ(){
        double z;
        z = this.getM() * Math.cos(this.getB()*this.getT()*Math.sin(this.getT())) + this.getC();
        System.out.print("\nCalc of Z: " + z);
    }

    public static void main(String[] args) {
        Calc prog = new Calc();
        prog.showDate();
        prog.inputData();
        prog.showData();
        prog.calcF();
        prog.calcZ();
    }
}
