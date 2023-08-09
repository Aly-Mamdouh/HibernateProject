package org.alio;

import entity.TodosEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;

import java.util.List;

public class HibernateDBManger {
    private EntityManagerFactory emf;
    private EntityManager em;

    public HibernateDBManger(String persistenceUnitName) {
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    public void openEntityManager() {
        em = emf.createEntityManager();
    }

    public void closeEntityManager() {
        if (em != null) {
            em.close();
        }
    }
    public TodosEntity getTodoEntityById(int id) {
        return em.find(TodosEntity.class, id);
    }
    public void insertTodoEntity(TodosEntity todo) {
        try {
            em.getTransaction().begin();
            em.persist(todo);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateTodoEntity(TodosEntity todo) {
        try {
            em.getTransaction().begin();
            em.merge(todo);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTodoEntityById(int id) {
        try {
            em.getTransaction().begin();
            TodosEntity todo = em.find(TodosEntity.class, id);
            if (todo != null) {
                em.remove(todo);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public List<TodosEntity> selectAllData() {
        TypedQuery<TodosEntity> query = em.createQuery("from TodosEntity", TodosEntity.class);
        return query.getResultList();
    }
}
