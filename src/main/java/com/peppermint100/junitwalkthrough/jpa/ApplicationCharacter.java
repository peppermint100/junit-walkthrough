package com.peppermint100.junitwalkthrough.jpa;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "application_character")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApplicationCharacter {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    public ApplicationCharacter(String name) {
        this.name = name;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
