package academy.atl.customers.model;

import jakarta.persistence.*;
import lombok.*;

//@Getter @Setter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode @ToString
@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;

}
