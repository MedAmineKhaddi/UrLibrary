package ma.services.urbook.Payload.Response;

import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ApiResponse {

    private String message;
    private Boolean status;
}
