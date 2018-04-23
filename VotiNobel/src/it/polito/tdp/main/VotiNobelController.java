package it.polito.tdp.main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.model.Model;
import it.polito.tdp.model.Esame;
import it.polito.tdp.model.ListaEsami;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class VotiNobelController {

	Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInput;
    
    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCalcolaCombinazione(ActionEvent event) {
    		try {
    			int numeroCrediti = Integer.parseInt(txtInput.getText());
    			this.model.reset();
    			this.model.calcolaSottoinsiemeEsami(numeroCrediti, 0, new ListaEsami(), new ListaEsami(model.getTuttiEsamiSostenuti()));
    			List<Esame> sequenza = this.model.getListaEsami();
    			StringBuilder sb = new StringBuilder();
    			for(Esame e : sequenza) {
    				sb.append(e.toString()+"\n");
    			}
    			this.txtResult.appendText(sb.toString());
    			
    		} catch (NumberFormatException e) {
    			txtResult.setText("Inserire un numero di crediti > 0");
    		}
    }

    @FXML
    void doReset(ActionEvent event) {
    		this.model.reset();
    		txtInput.clear();
    		txtResult.clear();
    }

    @FXML
    void initialize() {
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'VotiNobel.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'VotiNobel.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'VotiNobel.fxml'.";
    }

	public void setModel(Model model) {
		
		this.model = model;
	}
}
