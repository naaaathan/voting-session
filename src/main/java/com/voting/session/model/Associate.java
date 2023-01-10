package com.voting.session.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;

@Entity
@Table(name = "associate")
public class Associate extends AbstractPersistable<Long> {

    @Column
    private String associateName;

    @Column(unique = true)
    private String associatePrimaryDocument;

    @Column
    private Date associateBirth;


    public void setAssociateName(String associateName) {
        this.associateName = associateName;
    }

    public void setAssociatePrimaryDocument(String associatePrimaryDocument) {
        this.associatePrimaryDocument = associatePrimaryDocument;
    }

    public void setAssociateBirth(Date associateBirth) {
        this.associateBirth = associateBirth;
    }




}
