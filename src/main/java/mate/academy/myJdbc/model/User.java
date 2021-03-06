package mate.academy.myJdbc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String username;
    private String password;
    private String mail;
    private String token;
    private List<Role> roles = new ArrayList<>();

    public User(Long id, String username, String password, String mail, String token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.token = token;
    }

    public void addRole(String role) {
        roles.add(new Role(role));
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, mail);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", token='" + token + '\'' +
                ", roles=" + roles +
                '}';
    }

    public static class Builder {
        private Long id;
        private String username;
        private String password;
        private String mail;
        private String token;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder mail(String mail) {
            this.mail = mail;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public User build() {
            return new User(id, username, password, mail, token);
        }
    }
}
