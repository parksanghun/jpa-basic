package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainPersistance {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // 비영속
            Member memberA = new Member();
            memberA.setId(101L);
            memberA.setName("HelloA");

            // 영속
            System.out.println("=== BEFORE ===");
            em.persist(memberA);
            System.out.println("=== AFTER ===");

            Member memberB = new Member();
            memberB.setId(102L);
            memberB.setName("HelloB");
            em.persist(memberB);

            Member member1 = em.find(Member.class, 101L);
            Member member2 = em.find(Member.class, 102L);

//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());
            System.out.println("=== BEFORE COMMIT ===");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }


        emf.close();
    }
}
