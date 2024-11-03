package academy.atl.customers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
