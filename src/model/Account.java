package model;

import java.util.List;
import java.util.Vector;

public class Account {
	
	private int id;
	private String nom;
	private float solde;
	public static int count=1;
	private Vector<String> history;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(String nom, float solde, Vector<String> history) {
		super();
		this.id = count;
		this.nom = nom;
		this.solde = solde;
		this.history = history;
		this.count++;

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public float getSolde() {
		return solde;
	}
	public void setSolde(float solde) {
		this.solde = solde;
	}
	public static int getCount() {
		return count;
	}
	public static void setCount(int count) {
		Account.count = count;
	}
	public Vector<String> getHistory() {
		return history;
	}
	public void setHistory(Vector<String> history) {
		this.history = history;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", nom=" + nom + ", solde=" + solde + ", history=" + history + "]";
	}

	
	

	
	
}
