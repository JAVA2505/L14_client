import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String port = "8999";
        //TODO

        System.out.println("Enter your name:");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Enter your ip:");
        String ip = new Scanner(System.in).nextLine();

        while(true){
            try {
                System.out.println("Enter beneficiar name:");
                String benef = new Scanner(System.in).nextLine();
                System.out.println("Enter message:");
                String message = new Scanner(System.in).nextLine();
                Socket s = new Socket("localhost", 8899);
                ObjectOutputStream oos =
                        new ObjectOutputStream(s.getOutputStream());
                if("reg".equals(message)){
                    oos.writeObject(message);
                    oos.writeObject(name);
                    oos.writeObject(ip);
                    oos.writeObject(port);
                    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                    String answer = (String) ois.readObject();
                    ois.close();
                    System.out.println(answer);
                }else {
                    oos.writeObject(new StringBuilder(name).append(" ").append(benef).append(" ").append(message).toString());
                }
                oos.close();
                s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
