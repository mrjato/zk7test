package es.uvigo.ei.sing.zk7test.services.impl;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import es.uvigo.ei.sing.zk7test.entity.Log;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
@TestExecutionListeners({ 
	DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class
})
@DatabaseSetup("dataset.xml")
public class LogDaoTest {
	@Autowired
	private LogDao dao;

	@Test
	public void testQueryAll() {
		assertEquals(6, dao.queryAll().size());
	}

	@Test
	public void testGet() {
		final int id = 3;
		final Date date = new Date(
			new GregorianCalendar(2014, Calendar.MARCH, 1, 12, 00, 03)
		.getTimeInMillis());
		
		final Log log = dao.get(id);
		
		assertEquals(new Integer(id), log.getId());
		assertEquals("Hola", log.getMessage());
		assertEquals(date, log.getDate());
	}

	@Test
	public void testSave() {
		final Log log = dao.save(new Log("Olá"));
		assertEquals(7, dao.queryAll().size());
		
		final Log dbLog = dao.get(log.getId());
		assertEquals(log.getId(), dbLog.getId());
		assertEquals(log.getMessage(), dbLog.getMessage());
		assertEquals(log.getDate(), dbLog.getDate());
	}

	@Test
	public void testDelete() {
		dao.delete(dao.get(4));
		
		assertEquals(5, dao.queryAll().size());
	}

}
