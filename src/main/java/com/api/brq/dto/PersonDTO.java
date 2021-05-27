package com.api.brq.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTO {

    @JsonProperty("id_person")
    private Integer idPerson;

    @JsonProperty("name_person")
    private String name;

    @JsonProperty("last_name_person")
    private String lastName;

    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("rg")
    private String rg;

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("cell_phone")
    private String celPhone;

    @JsonProperty("email")
    private String email;
}