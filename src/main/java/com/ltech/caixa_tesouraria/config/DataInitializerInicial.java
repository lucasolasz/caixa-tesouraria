package com.ltech.caixa_tesouraria.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ltech.caixa_tesouraria.model.Roles;
import com.ltech.caixa_tesouraria.model.Usuario;
import com.ltech.caixa_tesouraria.repository.RoleRepository;
import com.ltech.caixa_tesouraria.repository.UsuarioRepository;

@Component
@Profile("dev")
public class DataInitializerInicial implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializerInicial(JdbcTemplate jdbcTemplate, UsuarioRepository usuarioRepository,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        jdbcTemplate.execute(carregarRoles());
        jdbcTemplate.execute(carregarMes());
        jdbcTemplate.execute(carregarUsuarios());
        jdbcTemplate.execute(carregarRolesUsuario());
        jdbcTemplate.execute(carregarTipoMovimentacao());
        jdbcTemplate.execute(carregarFundoFinanceiro());

        System.out.println("Scripts executados");

    }

    public String carregarFundoFinanceiro() {

        try {
            Path path = Paths.get(new ClassPathResource("scripts/fundo_financeiro.sql").getURI());
            String sql = Files.readString(path);
            return sql;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public String carregarMes() {

        try {
            Path path = Paths.get(new ClassPathResource("scripts/mes.sql").getURI());
            String sql = Files.readString(path);
            return sql;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public String carregarTipoMovimentacao() {

        try {
            Path path = Paths.get(new ClassPathResource("scripts/tipo_movimentacao.sql").getURI());
            String sql = Files.readString(path);
            return sql;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public String carregarRoles() {

        try {
            Path path = Paths.get(new ClassPathResource("scripts/roles.sql").getURI());
            String sql = Files.readString(path);
            return sql;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public String carregarUsuarios() {

        try {
            Path path = Paths.get(new ClassPathResource("scripts/usuario.sql").getURI());
            String sql = Files.readString(path);
            return sql;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public String carregarRolesUsuario() {

        try {
            Path path = Paths.get(new ClassPathResource("scripts/usuario_roles.sql").getURI());
            String sql = Files.readString(path);
            return sql;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public void criarUsuarios() {
        if (this.usuarioRepository.count() == 0) {
            List<Roles> roleAdm = this.roleRepository.findAll();
            Usuario admin = new Usuario();

            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123"));
            admin.setFirstName("Administrador");
            admin.setLastName("Sistema");
            admin.setEnabled(true);
            admin.setRoles(roleAdm);

            List<Roles> roleUser = this.roleRepository.findByName("ROLE_USER");
            Usuario user = new Usuario();

            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("123"));
            user.setFirstName("Usuario");
            user.setLastName("comum");
            user.setEnabled(true);
            user.setRoles(roleUser);

            this.usuarioRepository.save(admin);
            this.usuarioRepository.save(user);

            System.out.println("Usuários criados!");
        }
    }

}
