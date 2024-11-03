package academy.atl.customers.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
}
