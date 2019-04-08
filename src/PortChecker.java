import java.io.*;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PortChecker {
    public static void main(String[] args) throws Exception{
        int start = 1;
        File temp = new File("LastPort.txt");
        if(temp.exists()) {
            try{
                Scanner scan = new Scanner(temp);
                start = scan.nextInt()+1;
            } catch (NoSuchElementException e) {}
        }
        PrintWriter openList;
        PrintWriter lastPort;
        for (int port =start; port<=65535; port++) {
            try {
                Socket connection = new Socket();
                long ti = System.currentTimeMillis();
                connection.connect(new InetSocketAddress("portquiz.net",port), 300);
                long tf = System.currentTimeMillis();
                System.out.println("Port "+port+" is open; connected in " + (tf-ti)+ " ms");
                openList = new PrintWriter(new BufferedWriter(new FileWriter("Open Port List.txt",true)));
                openList.println(port);
//                openList.println(Math.random());
                openList.close();
            } catch (ConnectException | SocketTimeoutException e) {
                System.out.println("Port " + port + " is not open");
            }
            lastPort = new PrintWriter("LastPort.txt");
            lastPort.println(port);
            lastPort.close();
        }
    }

}
