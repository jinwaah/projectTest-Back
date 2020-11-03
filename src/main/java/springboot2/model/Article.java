package springboot2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idArticle;
    private String title;
    private Float price;

    @ManyToMany
    @JoinTable(name="user_article",
            joinColumns = @JoinColumn(name= "idArticle"),
            inverseJoinColumns = @JoinColumn( name = "idUser"))
    private List<User> listUsers = new ArrayList<>();

    public Article() {

    }

    public Article(String title, Float price) {
        this.setTitle(title);
        this.setPrice(price);
    }

    public long getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(long idArticle) {
        this.idArticle = idArticle;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "price", nullable = false)
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<User> getListUsers() {
        return listUsers;
    }

    @Override
    public String toString() {
        return "Article [id =" + idArticle + ", Title =" + title + ", Price =" + price + "]";
    }
}
