package springboot2.controller;

import springboot2.exception.ResourceNotFoundException;
import springboot2.model.User;
import springboot2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{idUser}")
    public ResponseEntity<User> getUserById(@PathVariable(value ="idUser") Long idUser)
        throws ResourceNotFoundException {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + idUser));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{idUser}")
    public ResponseEntity<User> updateUser(@PathVariable(value ="idUser") Long idUser,
                                                  @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + idUser ));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setMail(userDetails.getMail());
        user.setPhone(userDetails.getPhone());

        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{idUser}")
    public Map<String, Boolean> deleteUser (@PathVariable(value = "idUser") Long idUser )
        throws ResourceNotFoundException {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id ::" + idUser));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
