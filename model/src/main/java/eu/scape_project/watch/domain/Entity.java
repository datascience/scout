package eu.scape_project.watch.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import thewebsemantic.Id;
import thewebsemantic.Namespace;
import thewebsemantic.binding.RdfBean;
import eu.scape_project.watch.dao.DAO;
import eu.scape_project.watch.utils.KBUtils;

/**
 * An entity is a concrete instance of some EntityType. E.g. 'ImageMagick v1.0'
 * is a concrete instance (an entity) and has the entity type 'Action
 * component'.
 * 
 * @author Luis Faria <lfaria@keep.pt>
 */
@Namespace(KBUtils.WATCH_NS)
@XmlRootElement(name = KBUtils.ENTITY)
@XmlAccessorType(XmlAccessType.FIELD)
public class Entity extends RdfBean<Entity> {

  /**
   * Get the property id based on the entity type and entity name.
   * 
   * @param entityTypeName
   *          the related entity type
   * @param entityName
   *          the entity name, unique for the related entity type
   * @return The identifier
   */
  public static String createId(final String entityTypeName, final String entityName) {
    return KBUtils.hashId(entityTypeName, entityName);
  }

  /**
   * The unique Id.
   */
  @Id
  @XmlElement(required = true)
  private String id;

  /**
   * The unique name of the entity.
   */
  @XmlElement(required = true)
  private String name;

  /**
   * The type of the entity.
   */
  @XmlElement
  @JsonProperty
  private EntityType type;

  /**
   * Create a new empty Entity.
   */
  public Entity() {
    super();
  }

  /**
   * Create a new Entity.
   * 
   * @param et
   *          The type of the entity
   * @param n
   *          a name that uniquely identifies the entity
   */
  public Entity(final EntityType et, final String n) {
    this.type = et;
    this.name = n;

    updateId();
  }

  /**
   * Update property Id based on the related {@link EntityType} and property
   * name.
   */
  private void updateId() {
    if (this.type != null && this.name != null) {
      this.id = createId(this.type.getName(), this.name);
    }
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;

    updateId();
  }

  public EntityType getType() {
    return type;
  }

  public void setType(final EntityType type) {
    this.type = type;

    updateId();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
    result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
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
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Entity other = (Entity) obj;
    if (this.type == null) {
      if (other.type != null) {
        return false;
      }
    } else if (!this.type.equals(other.type)) {
      return false;
    }
    if (this.name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!this.name.equals(other.name)) {
      return false;
    }
    return true;
  }

  @Override
  public Entity save() {
    final Entity entity = super.save();
    DAO.fireOnUpdated(this);
    return entity;
  }

  @Override
  public void delete() {
    super.delete();
    DAO.fireOnRemoved(this);
  }

  @Override
  public String toString() {
    return String.format("Entity [id=%s, name=%s, type=%s]", id, name, type);
  }
}
