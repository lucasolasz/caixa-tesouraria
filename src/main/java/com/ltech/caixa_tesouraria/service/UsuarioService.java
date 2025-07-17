package com.ltech.caixa_tesouraria.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ltech.caixa_tesouraria.model.Roles;
import com.ltech.caixa_tesouraria.model.Usuario;
import com.ltech.caixa_tesouraria.repository.RoleRepository;
import com.ltech.caixa_tesouraria.repository.ServiceCrud;
import com.ltech.caixa_tesouraria.repository.UsuarioRepository;

@Service
public class UsuarioService extends ServiceCrud<Usuario, Long, UsuarioRepository> {

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder,
            RoleRepository roleRepository) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void ajusteAntesGravacao(Usuario entity) {
        entity.setPassword(this.passwordEncoder.encode(entity.getPassword()));
        entity.setEnabled(true);
        entity.setRoles(this.addRoleToUser("USER"));
        super.ajusteAntesGravacao(entity);
    }

    public List<Roles> addRoleToUser(String roleName) {
        return this.roleRepository.findByName(roleName);
    }

    public boolean checkIfUserExist(String username) {
        return this.getRepository().existsByUsername(username);
    }
}
