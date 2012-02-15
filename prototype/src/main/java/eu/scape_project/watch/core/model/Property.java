package eu.scape_project.watch.core.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import thewebsemantic.Id;
import thewebsemantic.Namespace;
import thewebsemantic.binding.RdfBean;
import eu.scape_project.watch.core.KB;

@Namespace(KB.WATCH_NS)
@XmlRootElement(name = KB.PROPERTY)
@XmlAccessorType(XmlAccessType.FIELD)
public class Property extends RdfBean<Property> {

	public static String createId(String entityTypeName, String propertyName) {
		return entityTypeName + "/" + propertyName;
	}

	private void updateId() {
		if (type != null && name != null) {
			id = createId(type.getName(), name);
		}
	}

	@Id
	@JsonIgnore
	private String id;

	@XmlElement
	@JsonProperty
	private EntityType type;

	@XmlElement
	private String name;

	@XmlElement
	private String description;

	@XmlElement
	@JsonProperty
	private DataType datatype;

	public Property() {
		super();
	}

	public Property(EntityType t, String n, String d) {
		super();
		this.type = t;
		this.name = n;
		this.description = d;
		this.datatype = DataType.TEXT;

		updateId();
	}

	public Property(EntityType t, String n, String d, DataType dt) {
		this(t, n, d);
		this.datatype = dt;
	}

	public String getId() {
		return id;
	}

	public EntityType getEntityType() {
		return type;
	}

	public void setEntityType(EntityType entityType) {
		this.type = entityType;
		updateId();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		updateId();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property other = (Property) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public DataType getDatatype() {
		return datatype;
	}

	public void setDatatype(DataType datatype) {
		this.datatype = datatype;
	}

}