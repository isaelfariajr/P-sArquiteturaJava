package br.com.uniciv.minhastarefas.repository;

import br.com.uniciv.minhastarefas.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
