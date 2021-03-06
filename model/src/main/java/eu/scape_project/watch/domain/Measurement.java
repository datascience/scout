package eu.scape_project.watch.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import thewebsemantic.Id;
import thewebsemantic.Namespace;
import thewebsemantic.binding.RdfBean;
import eu.scape_project.watch.utils.KBUtils;

/**
 * Logs the moment when a property value was measured.
 * 
 * @author Luis Faria <lfaria@keep.pt>
 */
@Namespace(KBUtils.WATCH_NS)
@XmlRootElement(name = KBUtils.MEASUREMENT)
@XmlAccessorType(XmlAccessType.FIELD)
public class Measurement extends RdfBean<Measurement> {

	/**
	 * Get measurement Id.
	 * 
	 * @param entityName
	 *            The name of the related entity.
	 * @param propertyName
	 *            The name of the related property.
	 * @param timestamp
	 *            The time stamp of the measurement.
	 * @return The id to be used in RDF queries.
	 */
	public static final String createId(final String entityName,
			String propertyName, final Date timestamp) {
		return KBUtils.hashId(entityName, propertyName, timestamp);
	}

	/**
	 * Auto-generated id.
	 */
	@Id
	private String id;

	/**
	 * The measured property value.
	 */
	@XmlElement
	@JsonProperty
	private PropertyValue propertyValue;

	/**
	 * The moment in time of the measurement.
	 */
	@XmlElement
	private Date timestamp;

	/**
	 * Flag measurement as significant.
	 */
	@XmlElement
	private boolean significant;

	/**
	 * Flag measurement as the last measurement.
	 */
	@XmlElement
	private boolean last;

	/**
	 * Flag measurement as a limit measurement.
	 */
	@XmlElement
	private boolean limit;

	/**
	 * The source adaptor that made the measurement.
	 */
	@XmlElement
	@JsonProperty
	private SourceAdaptor adaptor;

	/**
	 * Create a new empty measurement.
	 */
	public Measurement() {
		super();
	}

	/**
	 * Create a new measurement.
	 * 
	 * @param pv
	 *            The measured property value.
	 * @param timestamp
	 *            The moment in time of the measurement.
	 * @param adaptor
	 *            The source adaptor that took the measurement.
	 */
	public Measurement(final PropertyValue pv, final Date timestamp,
			final SourceAdaptor adaptor) {
		this.propertyValue = pv;
		this.timestamp = (Date) timestamp.clone();
		this.last = false;
		this.limit = false;
		this.adaptor = adaptor;

		updateId();
		updateSignificant();
	}

	/**
	 * Update unique identifier of the measurement.
	 */
	private void updateId() {

		if (propertyValue != null) {
			if (propertyValue.getEntity() != null
					&& propertyValue.getProperty() != null) {
				this.id = createId(propertyValue.getEntity().getName(),
						propertyValue.getProperty().getName(), this.timestamp);
			} else {
				this.id = "unknown";
			}
		} else {
			this.id = "unknown";
		}
	}

	public PropertyValue getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(final PropertyValue propertyValue) {
		this.propertyValue = propertyValue;
		updateId();

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return (Date) timestamp.clone();
	}

	public void setTimestamp(final Date timestamp) {
		this.timestamp = (Date) timestamp.clone();
		updateId();
	}

	public boolean isSignificant() {
		return significant;
	}

	public void setSignificant(final boolean significant) {
		this.significant = significant;
	}

	private void updateSignificant() {
		this.significant = last || limit;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
		updateSignificant();
	}

	public boolean isLimit() {
		return limit;
	}

	public void setLimit(boolean limit) {
		this.limit = limit;
		updateSignificant();
	}

	public SourceAdaptor getAdaptor() {
		return adaptor;
	}

	public void setAdaptor(final SourceAdaptor adaptor) {
		this.adaptor = adaptor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adaptor == null) ? 0 : adaptor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (last ? 1231 : 1237);
		result = prime * result + (limit ? 1231 : 1237);
		result = prime * result
				+ ((propertyValue == null) ? 0 : propertyValue.hashCode());
		result = prime * result + (significant ? 1231 : 1237);
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Measurement)) {
			return false;
		}
		final Measurement other = (Measurement) obj;
		if (adaptor == null) {
			if (other.adaptor != null) {
				return false;
			}
		} else if (!adaptor.equals(other.adaptor)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (last != other.last) {
			return false;
		}
		if (limit != other.limit) {
			return false;
		}
		if (propertyValue == null) {
			if (other.propertyValue != null) {
				return false;
			}
		} else if (!propertyValue.equals(other.propertyValue)) {
			return false;
		}
		if (significant != other.significant) {
			return false;
		}
		if (timestamp == null) {
			if (other.timestamp != null) {
				return false;
			}
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String
				.format("Measurement [id=%s, propertyValue=%s, timestamp=%s, significant=%s, last=%s, limit=%s, adaptor=%s]",
						id, propertyValue, timestamp, significant, last, limit,
						adaptor);
	}

}
