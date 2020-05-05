package Application.Model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor

public class User {
    private String username;
    private String password;
    private String email;
}
