package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.services;

import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.entities.User;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.exceptions.BadRequestException;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.exceptions.NotFoundException;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.payloads_DTO.User_DTO.NewUserDTO;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsersService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    // FIND ALL

    public Page<User> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.userRepository.findAll(pageable);
    }

    // SAVE

    public User save(NewUserDTO payload) {
        this.userRepository.findByEmail(payload.email()).ifPresent(user -> {
                    throw new BadRequestException("The e-mail " + user.getEmail() + " is already in use.");
                }
        );

        User newUser = new User(payload.username(),
                payload.email(),
                payload.name(),
                payload.surname(),
                bcrypt.encode(payload.password()),
                payload.role()
        );

        User savedUser = this.userRepository.save(newUser);

        log.info("The user with ID: " + savedUser.getId() + " has been duly saved.");

        return savedUser;
    }

    // FIND BY ID

    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    // FIND BY ID & UPDATE

    public User findByIdAndUpdate(Long id, NewUserDTO payload) {
        User found = this.findById(id);

        if (!found.getEmail().equals(payload.email())) {
            this.userRepository.findByEmail(payload.email()).ifPresent(user -> {
                        throw new BadRequestException("The e-mail " + user.getEmail() + " is already in use. Try again.");
                    }
            );
        }

        found.setUsername(payload.username());
        found.setEmail(payload.email());
        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setPassword(payload.password());

        User modifyUser = this.userRepository.save(found);

        log.info("User with ID: " + modifyUser.getId() + " has been duly updated.");

        return modifyUser;
    }

    // FIND BY ID & DELETE

    public void findByIdAndDelete(Long id) {
        User found = this.findById(id);
        this.userRepository.delete(found);
    }

    // FIND BY EMAIL

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email " + email + " has not been found."));
    }
}
