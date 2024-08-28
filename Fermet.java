import java.util.*;
class Fermet{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the value of a: ");
    int a = sc.nextInt();
    System.out.println("Enter the value of p: ");
    int p = sc.nextInt();
    
    checkFermat(a, p);
  }
  static void checkFermat(int a, int p){
    int n = (int) Math.pow(a, p-1) % p;
    if(n==1) System.out.println("Fermat's Little Theorem holds true");
    else System.out.println("Fermat's Little Theorem holds false");
  }
}