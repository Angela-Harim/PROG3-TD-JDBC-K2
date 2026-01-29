package main.java;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class Ingredient {
    private Integer id;
    private String name;
    private CategoryEnum category;
    private Double price;
    private List<StockMovement> stockMovementList;


    public Ingredient() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<StockMovement> getStockMovementList() {
        return stockMovementList;
    }

    public void setStockMovementList(List<StockMovement> stockMovementList) {
        this.stockMovementList = stockMovementList;
    }

    public StockValue getStockValueAt(Instant t) {
        if (stockMovementList == null) return null;
        List<StockMovement> stockMovements = stockMovementList.stream()
                .filter(sm -> !sm.getCreationDatetime().isAfter(t))
                .toList();

        double movementIn = stockMovements.stream()
                .filter(sm -> sm.getType() == MovementTypeEnum.IN)
                .mapToDouble(sm -> sm.getValue().getQuantity())
                .sum();

        double movementOut = stockMovements.stream()
                .filter(sm -> sm.getType() == MovementTypeEnum.OUT)
                .mapToDouble(sm -> sm.getValue().getQuantity())
                .sum();

        StockValue stockValue = new StockValue();
        stockValue.setQuantity(movementIn - movementOut);
        stockValue.setUnit(unitSet.keySet().stream().findFirst().get());

        return stockValue;

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && category == that.category && Objects.equals(price, that.price) && Objects.equals(stockMovementList, that.stockMovementList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, price, stockMovementList);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", stockMovementList=" + stockMovementList +
                '}';
    }
}
