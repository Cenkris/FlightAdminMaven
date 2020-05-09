package Application.Model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Flight {
    @NonNull
    private String source;
    @NonNull
    private String destination;
    private String departureHour;
    private String landingHour;
    private String days;
    private int price;
}
