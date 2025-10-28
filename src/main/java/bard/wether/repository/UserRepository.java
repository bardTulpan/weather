package bard.wether.repository;

import bard.wether.entity.Session;
import bard.wether.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAll() {

        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u", User.class);
        return query.getResultList();
    }

//    public User save(User user) {
//        if (user.getId() == null) {
//            entityManager.persist(user);
//            return user;
//        } else {
//            return entityManager.merge(user);
//        }
//    }
//
//    public Session saveSession(Session session) {
//        if (session.getId() == null) {
//            entityManager.persist(session);
//            return session;
//        } else {
//            return entityManager.merge(session);
//        }
//    }
//
//    public Session createTestSession(User user) {
//        try {
//            Session session = new Session(user, LocalDateTime.now().plusHours(1));
//            saveSession(session);
//            System.out.println("Test user created successfully!");
//        } catch (Exception e) {
//            System.out.println("Error creating test user: " + e.getMessage());
//        }
//        return null;
//    }
//
//    public User createTestUser() {
//        try {
//            User testUser = new User("test", "password");
//            save(testUser);
//            System.out.println("Test user created successfully!");
//        } catch (Exception e) {
//            System.out.println("Error creating test user: " + e.getMessage());
//        }
//        return null;
//    }

    public User save(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
            entityManager.flush(); // Принудительно сохраняем чтобы получить ID
            return user;
        } else {
            return entityManager.merge(user);
        }
    }

    public Session saveSession(Session session) {
        if (session.getId() == null) {
            entityManager.persist(session);
            return session;
        } else {
            return entityManager.merge(session);
        }
    }

    public Session createTestSession(User user) {
        try {
            // Убедимся, что пользователь сохранен в БД
            if (user.getId() == null) {
                user = save(user); // Сохраняем пользователя если у него нет ID
            }

            Session session = new Session(user, LocalDateTime.now().plusHours(1));
            return saveSession(session);
        } catch (Exception e) {
            System.out.println("Error creating test session: " + e.getMessage());
            throw e;
        }
    }

    public User createTestUser() {
        try {
            User testUser = new User("test", "password");
            return save(testUser); // Возвращаем сохраненного пользователя
        } catch (Exception e) {
            System.out.println("Error creating test user: " + e.getMessage());
            throw e;
        }
    }
}
