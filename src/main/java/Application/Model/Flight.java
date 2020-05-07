package Application.Model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Flight {
    private String sursa;
    private String destinatie;
    private String oraPlecare;
    private String durata;
    private String zile;
    private int pret;
}
