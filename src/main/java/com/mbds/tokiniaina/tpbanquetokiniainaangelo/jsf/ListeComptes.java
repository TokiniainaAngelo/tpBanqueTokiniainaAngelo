/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbds.tokiniaina.tpbanquetokiniainaangelo.jsf;

import com.mbds.tokiniaina.tpbanquetokiniainaangelo.entity.CompteBancaire;
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
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private List<CompteBancaire> allComptes;

    /**
     * Creates a new instance of ListeComptes
     */
    public List<CompteBancaire> getAllComptes() {
        if (allComptes == null) {
            allComptes = gestionnaireCompte.getAllComptes();
        }
        return allComptes;
    }
}
