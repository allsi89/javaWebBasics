package chushkaapp.repository;

import chushkaapp.domain.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    private EntityManager eManager;

    public ProductRepositoryImpl() {
        this.eManager = Persistence
        .createEntityManagerFactory("chushka")
        .createEntityManager();
    }

    @Override
    public Product save(Product entity) {
        this.eManager.getTransaction().begin();
        this.eManager.persist(entity);
        this.eManager.getTransaction().commit();
        return entity;
    }

    @Override
    public Product findById(String id) {
        this.eManager.getTransaction().begin();
        Product product = this.eManager
                .createQuery("SELECT p FROM products p WHERE p.id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
        this.eManager.getTransaction().commit();
        return product;
    }

    @Override
    public List<Product> findAll() {
        this.eManager.getTransaction().begin();
        List<Product> products = this.eManager
                .createQuery("SELECT p FROM products p", Product.class)
                .getResultList();
        this.eManager.getTransaction().commit();
        return products;
    }

    @Override
    public Product getProductByName(String productName) {
        return this.eManager
                .createQuery("SELECT p FROM products p WHERE p.name = :name", Product.class)
                .setParameter("name", productName)
                .getSingleResult();
    }
}
