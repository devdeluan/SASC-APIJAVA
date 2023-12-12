package com.senai.apisasc.models;
import com.senai.apisasc.models.SetorModel;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;


import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_funcionario")
public class FuncionarioModel implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String nome;
    private String email;
    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_setor", referencedColumnName = "id")
    private SetorModel setor;

    @ManyToOne
    @JoinColumn(name = "id_tipofuncionario", referencedColumnName = "id")
    private TipoFuncionarioModel tipofuncionario;

    // Implementação do método isEnabled()
    @Override
    public boolean isEnabled() {
        // Você precisa fornecer a lógica para determinar se o usuário está habilitado.
        // Por exemplo, se houver uma flag 'ativo' no seu modelo, você poderia retornar o valor dela.
        // Neste exemplo, estou assumindo que o usuário está sempre habilitado.
        return true;
    }

    // Outros métodos da interface UserDetails (getAuthorities, getPassword, getUsername, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired).

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Implemente a lógica para obter as autoridades do usuário, se aplicável.
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Implemente a lógica para verificar se a conta do usuário não está expirada.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Implemente a lógica para verificar se a conta do usuário não está bloqueada.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Implemente a lógica para verificar se as credenciais do usuário não estão expiradas.
        return true;
    }

    @Override
    public String getPassword() {
        // Retorne a senha do usuário.
        return senha;
    }

    @Override
    public String getUsername() {
        // Retorne o nome de usuário (normalmente é o email no seu caso).
        return email;
    }
}
