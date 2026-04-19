package cz.osu.swi_cafe.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String firstName;
    private String lastName;
    ContactDTO contact;
    AddressDTO address;
}
