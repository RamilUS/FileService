package bell.yusipov.fileservice.config;

import bell.yusipov.fileservice.model.Usr;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UsrPrincipal implements UserDetails {

    private Usr usr;

    public UsrPrincipal(Usr usr){
        this.usr=usr;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(usr.getRole().getRoleName()));

        return authorities;
    }
    @Override
    public String getPassword() {
        return usr.getPassword();
    }

    @Override
    public String getUsername() {
        return usr.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return usr.getIsActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return usr.getIsActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return usr.getIsActive();
    }

    @Override
    public boolean isEnabled() {
        return usr.getIsActive();
    }

    public Usr getUsr() {
        return usr;
    }
}
