/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbds.tokiniaina.tpbanquetokiniainaangelo.jsf;

import com.mbds.tokiniaina.tpbanquetokiniainaangelo.entity.CompteBancaire;
import com.mbds.tokiniaina.tpbanquetokiniainaangelo.service.GestionnaireCompte;
import com.mbds.tokiniaina.tpbanquetokiniainaangelo.util.Util;
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
public class Transfert implements Serializable {

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

    public String transferer() {
        CompteBancaire source = gestionnaireCompte.findById(idSource);
        CompteBancaire destination = gestionnaireCompte.findById(idDestination);
        boolean erreur = false;
        if (source == null) {
            // Message d'erreur associé au composant source ; form:source est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la source est "source"
            // dans la page JSF qui lance le transfert.
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } else {
            if (source.getSolde() < montant) { // à compléter pour le cas où le solde du compte source est insuffisant...
                Util.messageErreur("Solde du compte source insuffisante pour le transfert!", "Solde du compte source insuffisante pour le transfert!", "form:source");
                erreur = true;
            }
        }
        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }

        gestionnaireCompte.transferer(source, destination, montant);
        Util.addFlashInfoMessage("Transfert de la somme de " + montant + " effectué, source: " + source.getNom() + " - " + source.getSolde() + ", destination: " + destination.getNom() + " - " + destination.getSolde());
        return "listeComptes?faces-redirect=true";
    }

}
