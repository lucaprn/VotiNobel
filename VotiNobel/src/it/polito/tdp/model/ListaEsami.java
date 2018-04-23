package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;

public class ListaEsami {
	
	List<Esame> esami;
	int punteggio;
	int crediti;
	

	

	public ListaEsami(ListaEsami parziale) {
		this.esami = new ArrayList<>(parziale.getEsami());
		this.punteggio=0;
		this.crediti=0;
	}

	public ListaEsami() {
		this.esami = new ArrayList<>();
		this.punteggio=0;
		this.crediti=0;
		
	}
	public ListaEsami(List<Esame> esami) {
		this.esami = new ArrayList<>(esami);
		this.punteggio=0;
		this.crediti=0;
		
	}
	
	public int getPunteggio() 
		
	{
		this.punteggio=0;
		for(Esame e : esami) {
			this.punteggio+=e.getCrediti()*e.getVoto();
		}
		return punteggio;
	}

	public List<Esame> getEsami() {

		return esami;
	}

	public void add(Esame e) {
		this.esami.add(e);
		this.crediti+=e.getCrediti();
		
	}

	public boolean isCorretto(int creditiTot) {
		if(this.crediti <= creditiTot) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * controlla che non ci siano esami duplicati nella sequenza
	 * @return
	 */
	
	public boolean isFinito() {
		
		for(Esame e : this.esami) {
			int contatore = 0;
			for(Esame i : this.esami) {
				if(e.getCodins().equals(i.getCodins())) {
					contatore++;
				}
			}
			if(contatore > 1) {
				return false;
			}
		}
		return true;
	}

	public void backTrack() {
		int indice = esami.size();
		this.crediti-=this.esami.get(indice-1).getCrediti();
		this.esami.remove(indice-1);
		
		
	}
	/**
	 * ritorna la sequenza di esami stampata
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Esame e : this.esami) {
			sb.append(e.toString()+"\n");
		}
		return sb.toString();
	}

	public int getCrediti() {
		
		return this.crediti;
	}
	
	public void reset() {
		this.esami.clear();
		this.crediti=0;
		this.punteggio=0;
	}

}
