package ch.lanza.rbm.persistence.services;

import ch.lanza.rbm.domain.exceptions.ValueException;
import ch.lanza.rbm.domain.models.TimeSpan;
import ch.lanza.rbm.persistence.models.RmiTimeSpan;

public class TimeSpanMapping {

	public static RmiTimeSpan toRmiTimeSpan(final TimeSpan timeSpan) {
		RmiTimeSpan rmiTimeSpan = new RmiTimeSpan();
		rmiTimeSpan.start = timeSpan.getStart();
		rmiTimeSpan.end = timeSpan.getEnd();
		return rmiTimeSpan;
	}

	public static TimeSpan toTimeSpan(RmiTimeSpan timeSpan) throws ValueException {
		return new TimeSpan(timeSpan.start, timeSpan.end);
	}
}
