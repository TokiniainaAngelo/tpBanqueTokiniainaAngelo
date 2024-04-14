/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbds.tokiniaina.tpbanquetokiniainaangelo.jsf;

import com.mbds.tokiniaina.tpbanquetokiniainaangelo.entity.CompteBancaire;
import com.mbds.tokiniaina.tpbanquetokiniainaangelo.service.GestionnaireCompte;
import com.mbds.tokiniaina.tpbanquetokiniainaangelo.util.Util;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;

/**
 *
 * @author Best
 */
@Named(value = "modifier")
@ViewScoped
public class Modifier implements Serializable {

    private CompteBancaire compte;
    private Long idCompte;
    private CompteBancaire prevCompte;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public String modifier() {
        compte = gestionnaireCompte.update(compte);
        Util.addFlashInfoMessage("Compte de " + prevCompte.getNom()+ " " + prevCompte.getSolde() + "a été mis à jour : " + compte.getNom()+ " " + compte.getSolde());
        return "listeComptes?faces-redirect=true";
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public void loadCompte() {
        this.prevCompte = gestionnaireCompte.findById(idCompte);
        this.compte = this.prevCompte;

    }

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }
}
