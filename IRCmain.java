import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class IRCmain {
	
	private static String nick;
	private static String username;
	private static String realname;
	
	private static String host;
	private static int port;
	private static String chan;
	
	
	private static PrintWriter out;
	private static Scanner in;
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		nick = "Mitchs IRC Bot";
		username = "mitchircbot";
		realname = "mitchell";
		
		host = "46.148.20.27";
		port = 443;
	
		chan = "#test";
		
		Socket socket = new Socket(host, port);
		
		out = new PrintWriter(socket.getOutputStream());
		in = new Scanner(socket.getInputStream());

		write("NICK " + nick);
		
		
		
		
		//pls ignore this half-assed piece of code
		String pongRet = "";
		boolean haveYouPinged = false;
		while(in.hasNext()) {
			if(haveYouPinged) {
				pongRet = in.next();
				break;
			}
			if(in.next().equals("PING"))
				haveYouPinged = true;
		}
		write("PONG " + pongRet);
		
		
		
		write("USER " + username + " 0 * :" + realname);
		write("OPER God xss");
		write("JOIN " + chan);
		sendMSG("Hi, I am " + nick + ". Nice to meet you!");
		
		while(in.hasNext()) {
			System.out.println("<<< " + in.nextLine());
		}
		in.close();
		out.close();
		socket.close();
		System.out.println("Done");
		
	}
	public static void write(String msg) {
		System.out.println(">>> " + msg);
		out.println(msg + "\r\n");
		out.flush();
	}
	
	public static void sendMSG(String msg) {
		write("PRIVMSG " + chan + " :" + msg);
	}
}

