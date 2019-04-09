package com.gamingfunserver.project.payload.Company;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CompanyTypeCreateRequest {
    @NotBlank
    @Size(min = 1, max = 40)
    private String name;

    private String description;

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
