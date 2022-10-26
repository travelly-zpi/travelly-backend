package pwr.edu.pl.travelly.api.controllers;

import com.azure.storage.blob.models.BlobProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pwr.edu.pl.travelly.core.user.UserFacade;
import pwr.edu.pl.travelly.core.user.dto.UserDto;
import pwr.edu.pl.travelly.core.user.form.LoginUserForm;
import pwr.edu.pl.travelly.core.user.form.CreateUserForm;
import pwr.edu.pl.travelly.core.user.form.UpdateUserForm;

import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

import static java.util.Objects.nonNull;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    private final UserFacade userFacade;

    public UserController(@Qualifier("userFacade") final UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody @Valid final LoginUserForm loginUserForm) throws AuthenticationException {
        return ResponseEntity.ok(this.userFacade.generateToken(loginUserForm));
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody @Valid final CreateUserForm user){
        return nonNull(userFacade.save(user)) ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> findUser(@PathVariable final UUID uuid){
        final UserDto user = userFacade.findByUuid(uuid);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value="/hello_world", method = RequestMethod.GET)
    public String helloWorld(){
        return "HELLO_WORLD";
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@ModelAttribute @Valid final UpdateUserForm userForm) throws IOException {
        return ResponseEntity.ok(userFacade.update(userForm));
    }

}
