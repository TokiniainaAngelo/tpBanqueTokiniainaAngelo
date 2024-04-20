/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbds.tokiniaina.tpbanquetokiniainaangelo.jsf;

import com.mbds.tokiniaina.tpbanquetokiniainaangelo.entity.CompteBancaire;
import com.mbds.tokiniaina.tpbanquetokiniainaangelo.entity.OperationBancaire;
import com.mbds.tokiniaina.tpbanquetokiniainaangelo.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Best
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable{

    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    private Long idCompte;
    private CompteBancaire compte;
    private List<OperationBancaire> operations;
    
    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public List<OperationBancaire> getOperations() {
        return operations;
    }
    /**
     * Creates a new instance of Operations
     */
    public Operations() {
    }
    
    public void loadCompte() {
        this.compte = gestionnaireCompte.findById(idCompte);
        this.operations = compte.getOperations();
    }
    
}
