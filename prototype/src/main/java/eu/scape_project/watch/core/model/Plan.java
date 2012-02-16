package eu.scape_project.watch.core.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eu.scape_project.watch.core.KB;

import thewebsemantic.Id;
import thewebsemantic.Namespace;

/**
 * Reference to a preservation plan, to be used by external assessment.
 * 
 * External assessment will use not only the Trigger Condition but also
 * additional (global) knowledge that cannot be encoded into the Condition in
 * order to assess the information. This module will not be part of the Watch
 * component.
 * 
 * @author Luis Faria <lfaria@keep.pt>
 * 
 */
@Namespace(KB.WATCH_NS)
@XmlRootElement(name = KB.PLAN)
@XmlAccessorType(XmlAccessType.FIELD)
public class Plan {

  /**
   * Create a new empty plan.
   */
  public Plan() {
    super();
  }

  /**
   * Create a plan.
   * 
   * @param id
   *          The unique plan identifier
   */
  public Plan(final String id) {
    super();
    this.id = id;
  }

  /**
   * The unique identifier of the plan.
   */
  @Id
  @XmlElement(required = true)
  private String id;

  public String getId() {
    return this.id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Plan other = (Plan) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
  
  
}
