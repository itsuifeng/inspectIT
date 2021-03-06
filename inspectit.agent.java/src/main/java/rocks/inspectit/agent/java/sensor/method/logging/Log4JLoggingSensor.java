package rocks.inspectit.agent.java.sensor.method.logging;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import rocks.inspectit.agent.java.core.IIdManager;
import rocks.inspectit.agent.java.hooking.IHook;
import rocks.inspectit.agent.java.sensor.method.AbstractMethodSensor;
import rocks.inspectit.agent.java.sensor.method.IMethodSensor;
import rocks.inspectit.agent.java.sensor.method.logging.severity.SeverityHelper;
import rocks.inspectit.agent.java.sensor.method.logging.severity.SeverityHelperFactory;
import rocks.inspectit.agent.java.sensor.method.logging.severity.SeverityHelperFactory.Framework;

/**
 * Logging sensor to capture log4j loggings.
 * 
 * @author Stefan Siegl
 */
public class Log4JLoggingSensor extends AbstractMethodSensor implements IMethodSensor {

	/** Configuration key for the minimum level that should be captured. */
	public static final String CONFIG_KEY_MINIMUM_LEVEL = "minlevel";

	/**
	 * Used for creating and resolving ids necessary to communicate with the server.
	 */
	@Autowired
	private IIdManager idManager;

	/** hook instance. */
	private Log4JLoggingHook hook;

	/**
	 * {@inheritDoc}
	 */
	public void init(Map<String, Object> parameter) {
		// read the desired minimum level and pass it to the hook
		String minimumLevelToCapture = (String) parameter.get(CONFIG_KEY_MINIMUM_LEVEL);
		hook = new Log4JLoggingHook(idManager, minimumLevelToCapture);
	}

	/**
	 * {@inheritDoc}
	 */
	public IHook getHook() {
		return hook;
	}
}
