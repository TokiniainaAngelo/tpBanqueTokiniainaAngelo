/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mbds.tokiniaina.tpbanquetokiniainaangelo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Best
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT c FROM CompteBancaire as c join fetch c.operations"),
    @NamedQuery(name = "CompteBancaire.countAll", query = "SELECT COUNT(c) FROM CompteBancaire c"),
    @NamedQuery(name = "CompteBancaire.findById", query = "SELECT c FROM CompteBancaire c WHERE c.id=:id"),})
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int solde;
    @Version
    private int version;

    public int getVersion() {
        return version;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OperationBancaire> operations = new ArrayList();

    /**
     * Get the value of solde
     *
     * @return the value of solde
     */
    public int getSolde() {
        return solde;
    }

    /**
     * Set the value of solde
     *
     * @param solde new value of solde
     */
    public void setSolde(int solde) {
        this.solde = solde;
    }

    /**
     * Get the value of nom
     *
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }

    public List<OperationBancaire> getOperations() {
        return operations;
    }

    /**
     * Set the value of nom
     *
     * @param nom new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public CompteBancaire() {
    }

    ;
    
    public CompteBancaire(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
        operations.add(new OperationBancaire("Création compte", solde));
    }

    public void deposer(int montant) {
        solde += montant;
        operations.add(new OperationBancaire("Dépôt", montant));
    }

    public void retirer(int montant) {
        if (montant < solde) {
            solde -= montant;
            operations.add(new OperationBancaire("Retrait", -montant));
        } else {
            solde = 0;
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mbds.tokiniaina.tpbanquetokiniainaangelo.entity.CompteBancaire[ id=" + id + " ]";
    }

}
