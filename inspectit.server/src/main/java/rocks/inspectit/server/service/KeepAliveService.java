package rocks.inspectit.server.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocks.inspectit.server.util.AgentStatusDataProvider;
import rocks.inspectit.shared.all.cmr.service.IKeepAliveService;
import rocks.inspectit.shared.all.spring.logger.Log;

/**
 * Service to keep track of the online-status of registered agents.
 * 
 * @author Marius Oehler
 *
 */
@Service
public class KeepAliveService implements IKeepAliveService {

	/**
	 * Logger for the class.
	 */
	@Log
	Logger log;

	/**
	 * {@link AgentStatusDataProvider}.
	 */
	@Autowired
	AgentStatusDataProvider agentStatusDataProvider;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sendKeepAlive(long platformId) {
		if (log.isDebugEnabled()) {
			log.debug("Received keep-alive signal from platform " + platformId);
		}

		agentStatusDataProvider.handleKeepAliveSignal(platformId);
	}

	/**
	 * Starts a continuous thread to be able to detect dead agents.
	 */
	@PostConstruct
	public void postConstruct() {
		if (log.isInfoEnabled()) {
			log.info("|-Keep Alive Service active...");
		}
	}
}
