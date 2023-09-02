package br.rafael.restapi2.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.rafael.restapi2.banco.MySQLBanco;
import br.rafael.restapi2.model.User;

//CRUD REST + SPRING BOOT

@RestController
@RequestMapping("/api/users")
public class UserController {
    private MySQLBanco banco = new MySQLBanco();
    private Long nextId = 1L;

    @GetMapping
    public String getAllUsers() {
        return banco.readData();
    }


    @PostMapping("/")
    private void redirectCreateUser(@RequestBody Map<String,String> payload) {
    	createUser(payload);
    }
    
    @PostMapping
    public void createUser(@RequestBody Map<String,String> payload) {
        banco.insertData(payload.get("nome"), payload.get("email"));
        //return new User(payload.get("nome"),payload.get("email"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = banco.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody Map<String,String> payload) {
        User user = banco.findUserById(id);
        if (user != null) {

            banco.updateData(payload.get("nome"), payload.get("email"));
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, @PathVariable Map<String,String> payload) {
        User user = banco.findUserById(id);
        if (user != null) {
            //only carry URL PATH VARIABLE AS PAYLOAD
            System.out.println(payload);
            System.out.println(user.getEmail());
            banco.deleteData(user.getEmail());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    private User banco.findUserById(Long id) {
//        return users.stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
}
