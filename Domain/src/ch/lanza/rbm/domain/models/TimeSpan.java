package ch.lanza.rbm.domain.models;

import java.util.Date;

import ch.lanza.rbm.domain.exceptions.ValueException;

public class TimeSpan {

	private Date start;
	private Date end;
	
	public TimeSpan ( Date start, Date end ) throws ValueException{
		setStart(start);
		setEnd(end);
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) throws ValueException{
		if ( start == null )
			throw new ValueException("start must not be null");
		if ( end != null && start.after(end) )
			throw new ValueException("start must be before end");
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) throws ValueException {
		if ( end == null )
			throw new ValueException("end must not be null");
		if ( start != null && end.before(start) )
			throw new ValueException("end must be after start");
		this.end = end;
	}
	
	public boolean contains(Date when) {
		return start.before(when) && end.after(when);
	}
	
	public boolean overlaps(TimeSpan timeSpan){
		return !(timeSpan.contains(this.start)
				|| timeSpan.contains(this.end)
				|| this.contains(timeSpan.start)
				|| this.contains(timeSpan.end));
	}
	
	public int inNights(){
		return (int) ((end.getTime() - start.getTime()) / 86400000); 
	}
	
	@Override
	public String toString() {
		return start + " - " + end;
	}
}
