package davidebraghi.U5_W3_D5_Davide_Braghi_TEST.services;

import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.entities.User;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.exceptions.UnauthorizedException;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.payloads_DTO.LoginDTO;
import davidebraghi.U5_W3_D5_Davide_Braghi_TEST.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        User found = this.usersService.findByEmail(body.email());

        if (bcrypt.matches(body.password(), found.getPassword())) {
            return jwtTools.createToken(found);
        } else {
            throw new UnauthorizedException("Wrong credentials. Try again.");
        }
    }
}
