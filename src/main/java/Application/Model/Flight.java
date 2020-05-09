package Application.Model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Flight {
    private String source;
    private String destination;
    private String departureHour;
    private String landingHour;
    private String days;
    private int price;
}
