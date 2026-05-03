package cz.osu.swi_cafe.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;

    @OneToOne(mappedBy = "contact")
    @JsonIgnore
    private User user
;

}
