import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	boolean first = false;
    	System.out.println("Go first ? (Y/n): ");
    	Scanner sc = new Scanner(System.in);
    	String input = sc.nextLine();
    	if(input.equals("y") || input.equals("Y")) first = true;
        new Game(first);
        sc.close();
    }
}

