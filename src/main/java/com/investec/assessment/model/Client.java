package com.investec.assessment.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "mobile_number", nullable = false, unique = true)
    private String mobileNumber;
    @Column(name = "id_number", nullable = false, unique = true)
    private String idNumber;
    @Column(name = "physical_address")
    private String physicalAddress;
}
