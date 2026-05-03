package cz.osu.swi_cafe.services;

import cz.osu.swi_cafe.dto.AddressDTO;
import cz.osu.swi_cafe.dto.ContactDTO;
import cz.osu.swi_cafe.dto.UserDTO;
import cz.osu.swi_cafe.repos.UserRepository;
import cz.osu.swi_cafe.tables.Address;
import cz.osu.swi_cafe.tables.Role;
import cz.osu.swi_cafe.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
            ContactDTO cDto = null;
            if (user.getContact() != null) {
                cDto = new ContactDTO(

                        user.getContact().getEmail(),
                        user.getContact().getPhoneNumber()
                );
            }

            AddressDTO aDto = null;
            if (user.getAddress() != null) {
                aDto = new AddressDTO(

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
    public User login(String username, String plainPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Uživatel nenalezen!"));

        if (BCrypt.checkpw(plainPassword, user.getPassword())) {
            return user;
        } else {
            throw new RuntimeException("Špatné heslo!");
        }
    }
    public User RegisterUser(User user) {
        String hashed=BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);

        if (user.getContact() != null) {
           user.getContact().setUser(user);
        }


        if (user.getContact() != null) {
            user.getContact().setUser(user);
        }

        if (user.getAddress() != null) {
            Address address = user.getAddress();

            if (address.getUsers() == null) {
                address.setUsers(new java.util.ArrayList<>());
            }
            address.getUsers().add(user);
        }
        user.setRole(Role.CUSTOMER);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User neexistuje"));

        existingUser.setUsername(userDetails.getUsername());
        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setLastName(userDetails.getLastName());
        existingUser.setAddress(userDetails.getAddress());
        existingUser.setContact(userDetails.getContact());

        return userRepository.save(existingUser);


    }
}

