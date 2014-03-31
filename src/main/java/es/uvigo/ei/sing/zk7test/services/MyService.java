package es.uvigo.ei.sing.zk7test.services;

import es.uvigo.ei.sing.zk7test.entity.Log;
import java.util.List;

public interface MyService {
	Log addLog(Log log);

	List<Log> getLogs();

	void deleteLog(Log log);
}
