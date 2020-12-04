package account_management;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.Socket;
import java.util.Vector;

import model.Account;

public class Traitement extends Thread {

	private Socket sc;

	public Traitement(Socket sc) {
		super();
		this.sc = sc;
	}

	public void run() {
		try {
			System.out.println("WELCOME");
			BufferedReader in_socket = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			PrintWriter out_socket = new PrintWriter(sc.getOutputStream(), true);
			while (true) {
				String msg = in_socket.readLine();
				if (msg.startsWith("CREATION")) {
					String nom = msg.substring(9, msg.length());

					boolean test = false;
					for (Account a : Server.list) {
						if (a.getNom().equals(nom)) {
							test = true;
						}
					}
					if (test == true) {
						out_socket.println("User Exist");

					} else {
						Vector<String> h=new Vector<String>();
						h.add("");
						Account a = new Account(nom, 0,h);
						out_socket.println("Bienvenue " + nom + " pour déposer $$ taper CREDIT");
						account_management.Server.list.add(a);
						while (true) {
							String msg2 = in_socket.readLine();
							if (msg2.startsWith("CREDIT")) {
								String soldes = msg2.substring(7, msg2.length());
								String solde = soldes + "f";
								float f = Float.parseFloat(solde);
								a.setSolde(f);
								System.out.println(a);
								Vector<String> h1=a.getHistory();
								h1.add("Credit: +"+soldes);
								a.setHistory(h1);
								out_socket.println("Credit avec succé, votre nouveau solde est: " + f);

							}
							if (msg2.startsWith("DEBIT")) {
								String soldes = msg2.substring(5, msg2.length());
								String solde = soldes + "f";
								System.out.println(solde);
								float f = Float.parseFloat(solde);
								if (f > a.getSolde()) {
									out_socket.println("Impossible !! Votre solde est " + a.getSolde());
									

								}
								
								a.setSolde(a.getSolde() - f);
								Vector<String> h1=a.getHistory();
								h1.add("Debit: -"+soldes);
								System.out.println(a);
								out_socket.println("Debit Avec succé de " + f);

							}
							if (msg2.startsWith("SOLDE")) {
								float solde = a.getSolde();
								out_socket.println("Votre solde est: " + solde);

							}
							if (msg2.startsWith("TRANSFERT")) {
								String[] tab = msg2.split(">");
								for (Account to : Server.list) {
									if (to.getNom().equals(tab[1])) {
										String solde = tab[2] + "f";
										float f = Float.parseFloat(solde);
										if(a.getSolde()>=f) {
											a.setSolde(a.getSolde()-f);
											to.setSolde(to.getSolde()+f);
											out_socket.println("Vous avez envoyez: " + f+" à "+ to.getNom());
											Vector<String> h1=a.getHistory();
											h1.add("Transfert -"+tab[2]+" à "+tab[1]);
											Vector<String> h2=to.getHistory();
											h2.add("Transfert +"+tab[2]+" de "+a.getNom());
										}
										
									}
								}
								float solde = a.getSolde();
								out_socket.println("Votre solde est: " + solde);

							}
							if (msg2.startsWith("HISTORY")) {
								Vector<String> h2=a.getHistory();
								out_socket.println("Votre historique est: " + h2);
							}

						}
					}

				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}