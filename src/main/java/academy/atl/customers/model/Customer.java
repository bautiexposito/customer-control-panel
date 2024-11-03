package academy.atl.customers.model;

import academy.atl.customers.dto.CustomerDto;
import jakarta.persistence.*;
import lombok.*;

//@Getter @Setter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode @ToString
@Data
@Entity
@Table(name = "customers")
public class Customer {

    public Customer() {
    }

    public Customer(CustomerDto customerDto) {
        this.id = customerDto.getId();
        this.firstName = customerDto.getFirstName();
        this.lastName = customerDto.getLastName();
        this.email = customerDto.getEmail();
        this.address = customerDto.getAddress();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;

}
