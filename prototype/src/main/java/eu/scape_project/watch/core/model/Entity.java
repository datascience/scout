package eu.scape_project.watch.core.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import thewebsemantic.Id;
import thewebsemantic.Namespace;
import thewebsemantic.binding.RdfBean;
import eu.scape_project.watch.core.KB;

@Namespace(KB.WATCH_NS)
@XmlRootElement(name = KB.ENTITY)
@XmlAccessorType(XmlAccessType.FIELD)
public class Entity extends RdfBean<Entity> {

	public Entity() {
		super();
	}

	public Entity(EntityType et, String n) {
		this.type = et;
		this.name = n;
	}

	@Id
	@XmlElement(required = true)
	private String name;

	@XmlElement
	@JsonProperty
	private EntityType type;

	// @XmlElement
	// @JsonProperty
	// private List<PropertyValue> propertyValues;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EntityType getEntityType() {
		return type;
	}

	public void setEntityType(EntityType entityType) {
		this.type = entityType;
	}

	// public List<PropertyValue> getPropertyValues() {
	// return propertyValues;
	// }
	//
	// public void setPropertyValues(List<PropertyValue> propertyValues) {
	// this.propertyValues = propertyValues;
	// }

	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + ((type == null) ? 0 : type.hashCode());
	// result = prime * result + ((name == null) ? 0 : name.hashCode());
	// return result;
	// }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String toString() {
		return "Entity(name=" + name + ", type=" + type + ")";
	}
}