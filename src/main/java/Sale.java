


import java.time.Instant;
import java.util.Objects;

public class Sale {
    private Integer id;
    private Instant creationDatetime;
    private Order order;

    public Sale(Integer id, Instant creationDatetime, Order order) {
        this.id = id;
        this.creationDatetime = creationDatetime;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public Instant getCreationDatetime() {
        return creationDatetime;
    }

    public Order getOrder() {
        return order;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreationDatetime(Instant creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id) && Objects.equals(creationDatetime, sale.creationDatetime) && Objects.equals(order, sale.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDatetime, order);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", creationDatetime=" + creationDatetime +
                ", order=" + order +
                '}';
    }
}

