package com.gamingfunserver.project.model.company;

import com.gamingfunserver.project.model.audit.DateAudit;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "company_type")
public class CompanyType extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

   // @Enumerated(EnumType.STRING)
    //@NaturalId
    @Column(length = 60,name="name")
    private String name;

    @Size(max = 100)
    private String description;

    public CompanyType(){};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
