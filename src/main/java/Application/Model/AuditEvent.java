package Application.Model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AuditEvent {
    String username;
    String action;
    String timeStamp;
}
