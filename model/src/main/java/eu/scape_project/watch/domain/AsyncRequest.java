package eu.scape_project.watch.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.annotate.JsonProperty;

import thewebsemantic.Id;
import thewebsemantic.Namespace;
import thewebsemantic.binding.RdfBean;
import eu.scape_project.watch.dao.DAO;
import eu.scape_project.watch.utils.JavaUtils;
import eu.scape_project.watch.utils.KBUtils;

/**
 * An Asynchronous Request, that will be kept in the KBUtils in order to be
 * monitored an acted upon.
 * 
 * @author Luis Faria <lfaria@keep.pt>
 * 
 */
@Namespace(KBUtils.WATCH_NS)
@XmlRootElement(name = KBUtils.ASYNC_REQUEST)
@XmlAccessorType(XmlAccessType.FIELD)
public class AsyncRequest extends RdfBean<AsyncRequest> {

  /**
   * The unique id that identifies the asynchronous request.
   */
  @Id
  @XmlElement(required = true)
  private String id;

  /**
   * A descriptive label for this request
   */
  @XmlElement
  private String description;

  /**
   * The list of {@link Trigger} associated with the request.
   */
  @XmlElement
  @JsonProperty
  private List<Trigger> triggers;

  /**
   * Create a new empty request with a generated Id.
   */
  public AsyncRequest() {
    this("", new ArrayList<Trigger>());
  }

  /**
   * Create a new request with a generated Id.
   * 
   * @param triggers
   *          The list of triggers to be installed on this request
   * 
   */
  public AsyncRequest(final String description, final List<Trigger> triggers) {
    this.id = UUID.randomUUID().toString();
    this.description = description;
    this.triggers = triggers;
  }

  /**
   * Get the unique Id.
   * 
   * @return the Identifier
   */
  public String getId() {
    return id;
  }

  /**
   * Set the unique Id.
   * 
   * @param id
   *          The Identifier
   */
  public void setId(final String id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Get the related triggers.
   * 
   * @return A list of {@link Trigger}
   */
  public List<Trigger> getTriggers() {
    return triggers;
  }

  /**
   * Set the list of triggers.
   * 
   * @param triggers
   *          A list of {@link Trigger}
   */
  public void setTriggers(final List<Trigger> triggers) {
    this.triggers = triggers;
  }

  /**
   * Add a new trigger to the existing list.
   * 
   * @param t
   *          the new trigger
   */
  public void addTrigger(final Trigger t) {
    this.triggers.add(t);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((triggers == null) ? 0 : triggers.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof AsyncRequest)) {
      return false;
    }
    AsyncRequest other = (AsyncRequest) obj;
    if (description == null) {
      if (other.description != null) {
        return false;
      }
    } else if (!description.equals(other.description)) {
      return false;
    }
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    if (triggers == null) {
      if (other.triggers != null) {
        return false;
      }
    } else if (!triggers.equals(other.triggers)) {
      return false;
    }
    return true;
  }

  @Override
  public AsyncRequest save() {
    final AsyncRequest req = super.save();
    DAO.fireOnUpdated(this);
    return req;
  }

  @Override
  public void delete() {
    super.delete();
    DAO.fireOnRemoved(this);
  }

  @Override
  public String toString() {
    return String.format("AsyncRequest [id=%s, description=%s, triggers=%s]", id, description, triggers);
  }

}
