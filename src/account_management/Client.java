package account_management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
public static void main(String[] args) throws Exception {
		
	Socket soc = new Socket("127.0.0.1",5000);
	while(true) {
	//3
	BufferedReader in_soc = new BufferedReader(new InputStreamReader(soc.getInputStream()));
	//4
	PrintWriter out_soc = new PrintWriter(soc.getOutputStream(),true);
	//1
	BufferedReader in_clavier  = new BufferedReader(new InputStreamReader(System.in));

	String msg = in_clavier.readLine();

	out_soc.println(msg);

	String msg2 = in_soc.readLine();

	System.out.println(msg2);

		
	}

}}
