import java.util.Objects;

public class Service {

    private int id;
    private String name;
    private String photoLink;
    private String description;
    private int price;

    public Service(String name, String photoLink, String description, int price) {
        this.name = name;
        this.photoLink = photoLink;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return id == service.id && price == service.price && name.equals(service.name) && photoLink.equals(service.photoLink) && description.equals(service.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, photoLink, description, price);
    }
}
