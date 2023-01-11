package com.voting.session.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

import java.util.Date;

@Entity
@Table(name = "associate")
public class Associate extends AbstractPersistable<Long> {

    @Id
    @GeneratedValue
    @Nullable
    private Long id;

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

    @Override
    @Nullable
    public Long getId() {
        return id;
    }

    @Override
    public void setId(@Nullable Long id) {
        this.id = id;
    }
}
