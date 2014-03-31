package es.uvigo.ei.sing.zk7test.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.ei.sing.zk7test.entity.Log;

@Repository
public class LogDao {
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<Log> queryAll() {
		return em.createQuery("SELECT l FROM Log l", Log.class)
			.getResultList();
	}

	@Transactional(readOnly = true)
	public Log get(Integer id) {
		return em.find(Log.class, id);
	}

	@Transactional
	public Log save(Log log) {
		em.persist(log);
		return log;
	}

	@Transactional
	public void delete(Log log) {
		Log r = get(log.getId());
		if (r != null) {
			em.remove(r);
		}
	}

}
