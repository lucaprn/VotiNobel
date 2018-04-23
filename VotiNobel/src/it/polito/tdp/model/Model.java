package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.dao.EsameDAO;

public class Model {
	
	ListaEsami migliore = new ListaEsami();
	EsameDAO esameDAO = new EsameDAO();

	public void calcolaSottoinsiemeEsami(int numeroCrediti, int crediti, ListaEsami parziale, ListaEsami sostenuti) {
		
		
		//deep copy
		ListaEsami tmp = new ListaEsami(parziale);
		
		//condizione di terminazione
		if(numeroCrediti == crediti) 
		{
			if(parziale.getPunteggio() > migliore.getPunteggio()) 
			{
				//System.out.println(tmp);
				this.migliore = tmp;
				
				return;
			}
			return;
		}
		
		//passo intermedio con soluzione parziale
		
		for(Esame e : sostenuti.getEsami()) {
			parziale.add(e);
			//crediti <= numeroCrediti
			if(parziale.isCorretto(numeroCrediti) && parziale.isFinito()) {
				this.calcolaSottoinsiemeEsami(numeroCrediti, parziale.getCrediti(), parziale, sostenuti);
			}
			//backtrasck
			parziale.backTrack();
		}
		
		
		return;
	}
	/**
	 * ritoena gli esami sostenuti 
	 * @return
	 */
	public ListaEsami getTuttiEsamiSostenuti(){
		
		List<Esame> sostenuti = new ArrayList<>(this.esameDAO.getTuttiEsami());
		ListaEsami listaSostenuti = new ListaEsami(sostenuti);
		
		return listaSostenuti;
		
	}
	
	/**
	 * ritorna la sequenza trovata
	 * @return
	 */
	
	public List<Esame> getListaEsami(){
		List<Esame> tmp = new ArrayList<>(this.migliore.getEsami());
		return tmp;
	}
	
	/**
	 * inizializza le strutture nel modello 
	 */
	
	public void reset() {
		this.migliore.reset();
		this.esameDAO = new EsameDAO();
	}

}
