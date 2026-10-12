package fatec.flowsense.backend.controller;

import fatec.flowsense.backend.entities.Role;
import fatec.flowsense.backend.entities.User;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import fatec.flowsense.backend.controller.dto.CreateUserDto;
import fatec.flowsense.backend.repository.RoleRepository;
import fatec.flowsense.backend.repository.UserRepository;

@Controller
public class UserController {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserController(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;

	}

	@ResponseBody
	@PostMapping("/users")
	@Transactional
	public ResponseEntity<Void> newUser(@RequestBody CreateUserDto dto) {

		System.out.println("===== ALGUEM FOI REGISTRADO =======================================");

		var basicRole = roleRepository.findByName(Role.Values.BASIC.name());

		if (basicRole == null) {
			basicRole = new Role();
			basicRole.setName(Role.Values.BASIC.name());
			basicRole = roleRepository.save(basicRole);
		}

		var userFromDb = userRepository.findByEmail(dto.email());
		if (userFromDb.isPresent()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email já cadastrado");
		}

		var user = new User();
		user.setEmail(dto.email());
		user.setPassword(bCryptPasswordEncoder.encode(dto.password()));
		user.setRoles(Set.of(basicRole));

		userRepository.save(user);

		return ResponseEntity.ok().build();
	}

}