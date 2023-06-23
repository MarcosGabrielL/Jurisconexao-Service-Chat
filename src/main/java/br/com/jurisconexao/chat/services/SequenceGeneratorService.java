package br.com.jurisconexao.chat.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;


@Service
public class SequenceGeneratorService {

    @PersistenceContext
    private EntityManager entityManager;

    public int generateSequence(String seqName) {
        Query query = entityManager.createNativeQuery("SELECT seq FROM database_sequence WHERE _id = :seqName");
        query.setParameter("seqName", seqName);

        Integer currentSeq = (Integer) query.getSingleResult();

        if (currentSeq != null) {
            // Increment the sequence value
            currentSeq += 1;

            // Update the sequence in the database
            Query updateQuery = entityManager.createNativeQuery("UPDATE database_sequence SET seq = :currentSeq WHERE _id = :seqName");
            updateQuery.setParameter("currentSeq", currentSeq);
            updateQuery.setParameter("seqName", seqName);
            updateQuery.executeUpdate();

            return currentSeq;
        } else {
            // If the sequence doesn't exist, insert a new record with initial value 1
            Query insertQuery = entityManager.createNativeQuery("INSERT INTO database_sequence (_id, seq) VALUES (:seqName, 1)");
            insertQuery.setParameter("seqName", seqName);
            insertQuery.executeUpdate();

            return 1;
        }
    }
}
