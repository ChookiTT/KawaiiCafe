package cz.osu.swi_cafe.services;

import cz.osu.swi_cafe.dto.AddressDTO;
import cz.osu.swi_cafe.dto.ContactDTO;
import cz.osu.swi_cafe.dto.UserDTO;
import cz.osu.swi_cafe.repos.UserRepository;
import cz.osu.swi_cafe.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserDTO> getAllUsers() {

        List<User> users = userRepository.findAll();
        return users.stream()
        .map(user -> {
            // 1. Nejdřív vytvoříš menší DTOčka (pokud uživatel tu adresu/kontakt má)
            ContactDTO cDto = null;
            if (user.getContact() != null) {
                cDto = new ContactDTO(
                        user.getContact().getContactId(),
                        user.getContact().getEmail(),
                        user.getContact().getPhoneNumber()
                );
            }

            AddressDTO aDto = null;
            if (user.getAddress() != null) {
                aDto = new AddressDTO(
                        user.getAddress().getAddressId(),
                        user.getAddress().getStreet(),
                        user.getAddress().getHouseNumber(),
                        user.getAddress().getCity()
                );
            }

        return new UserDTO(
                        user.getUsername(),
                        user.getFirstName(),
                        user.getLastName(),
                        cDto,
                        aDto
                     );
            })
                .collect(Collectors.toList());
    }
}
