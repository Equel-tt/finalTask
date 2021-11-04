package by.allahverdiev.finaltask.entity;

public class Product implements Entity {
    private int id;
    private User manager;
    private String name;
    private ProductType productType;
    private Provider provider;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }


    public Product() {
    }

    public Product(int id) {
        this.id = id;
    }

    public Product(int newId, String newName) {
        this.id = newId;
        this.name = newName;
    }

    public Product(int newId, String newName, User newManager, ProductType newProductType, Provider newProvider) {
        this.id = newId;
        this.name = newName;
        this.manager = newManager;
        this.productType = newProductType;
        this.provider = newProvider;
    }

    @Override
    public String toString() {
        return this.id + " " + this.name + " " + this.manager + " " + this.productType + " " + this.provider;
    }
}
