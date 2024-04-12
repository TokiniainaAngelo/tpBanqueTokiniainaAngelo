/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbds.tokiniaina.tpbanquetokiniainaangelo.jsf;

import com.mbds.tokiniaina.tpbanquetokiniainaangelo.entity.CompteBancaire;
import com.mbds.tokiniaina.tpbanquetokiniainaangelo.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.io.Serializable;

/**
 *
 * @author Best
 */
@Named(value = "transfert")
@ViewScoped
public class Transfert implements Serializable{

    /**
     * Creates a new instance of Transfert
     */
    public Transfert() {
    }

    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    private Long idSource;
    private Long idDestination;
    private int montant;

    public Long getIdSource() {
        return idSource;
    }

    public Long getIdDestination() {
        return idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public void setIdDestination(Long idDestination) {
        this.idDestination = idDestination;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    public String transferer(){
        CompteBancaire source = gestionnaireCompte.findById(idSource);       
        CompteBancaire destination = gestionnaireCompte.findById(idDestination);
        gestionnaireCompte.transferer(source,destination,montant);
        return "listeComptes";
    }
    
}
