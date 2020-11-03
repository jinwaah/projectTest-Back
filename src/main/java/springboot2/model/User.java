package springboot2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUser;
    private String firstName;
    private String lastName;
    private String mail;
    private String phone;

    @ManyToMany
    @JoinTable(name="user_article",
               joinColumns = @JoinColumn(name= "idUser"),
               inverseJoinColumns = @JoinColumn( name = "idArticle"))
    private List<Article> listArticles = new ArrayList<>();

    public User() {

    }

    public User(String firstName, String lastName, String mail, String phone) {
        super();
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setMail(mail);
        this.setPhone(phone);
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Column(name = "firstName", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastName", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "mail", nullable = false)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Column(name = "phone", nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Article> getListArticles() {
        return listArticles;
    }

    @Override
    public String toString() {
        return "User [id =" + idUser + ", firstName =" + firstName + ", lastName =" + lastName + ", phone =" + phone + "]";
    }
}
