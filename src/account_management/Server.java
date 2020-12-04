package account_management;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import model.Account;


public class Server {
	static ArrayList<Account> list;
	
	public static void main(String[] args) throws Exception {
		
		list = new ArrayList<Account>();
		ServerSocket soc = new ServerSocket(5000);
		while(true) {
		Socket sc = soc.accept();
		Traitement tr = new Traitement(sc);
		tr.start();
		}
	}


}
