package eu.scape_project.watch.dao;

import java.util.List;

import eu.scape_project.watch.domain.EntityType;
import eu.scape_project.watch.domain.Property;
import eu.scape_project.watch.utils.KBUtils;

/**
 * 
 * @author Luis Faria <lfaria@keep.pt>
 * 
 */
public final class PropertyDAO extends AbstractDO<Property> {

  /**
   * The name of the relationship to {@link EntityType} in {@link Property}.
   */
  private static final String ENTITY_TYPE_REL = KBUtils.WATCH_PREFIX + "type";

  /**
   * Logger.
   */
  // private static final Logger LOG =
  // LoggerFactory.getLogger(PropertyDAO.class);

  /**
   * Get the complete Property RDF Id to use in SPARQL.
   * 
   * @param entityType
   *          The name of the entity type that contains this property.
   * @param propertyName
   *          The name of the property.
   * @return The complete Property RDF Id using namescape prefix
   */
  public static String getPropertyRDFId(final String entityType, final String propertyName) {
    return KBUtils.getRdfId(Property.class, Property.createId(entityType, propertyName));
  }

  /**
   * Get the complete Property RDF Id to use in SPARQL.
   * 
   * @param property
   *          The property from which go get the RDF id.
   * 
   * @return The complete Property RDF Id using namescape prefix
   */
  public static String getPropertyRDFId(final Property property) {
    return KBUtils.getRdfId(Property.class, property.getId());
  }

  /**
   * Get the complete Property RDF Id to use in SPARQL.
   * 
   * @param propertyId
   *          The property from which go get the RDF id.
   * 
   * @return The complete Property RDF Id using namescape prefix
   */
  public static String getPropertyRDFId(final String propertyId) {
    return KBUtils.getRdfId(Property.class, propertyId);
  }

  /**
   * No other instances other then in {@link DAO}.
   */
  protected PropertyDAO() {

  }

  public Property findById(final String id) {
    return super.findById(id, Property.class);
  }

  /**
   * Find a {@link Property} by the related {@link EntityType} and name.
   * 
   * @param entityTypeName
   *          The name of the related {@link EntityType}
   * @param name
   *          the name of the {@link Property}
   * @return the {@link Property} or <code>null</code> if not found
   */
  public Property findByEntityTypeAndName(final String entityTypeName, final String name) {
    final String id = Property.createId(entityTypeName, name);
    return super.findById(id, Property.class);
  }

  /**
   * Query for {@link Property}.
   * 
   * @see #query(Class, String, int, int)
   * 
   * @param bindings
   *          The query bindings, see
   *          {@link AbstractDO#query(Class, String, int, int)}
   * @param start
   *          The index of the first item to retrieve
   * @param max
   *          The maximum number of items to retrieve
   * @return A list of {@link Property} filtered by the above constraints
   */
  public List<Property> query(final String bindings, final int start, final int max) {
    return super.query(Property.class, bindings, start, max);
  }

  /**
   * Count the results of a query for {@link Property}.
   * 
   * @param bindings
   *          The query bindings, see {@link AbstractDO#count(Class, String)}
   * @return The number of results expected for the query
   */
  public int count(final String bindings) {
    return super.count(Property.class, bindings);
  }

  /**
   * List properties of a type.
   * 
   * @param type
   *          The related {@link EntityType}
   * @param start
   *          the index of the first item to retrieve
   * @param max
   *          the maximum number of items to retrieve
   * @return The list of properties filtered by the above constraints
   */
  public List<Property> listWithType(final String typeId, final int start, final int max) {
    final String bindings = String.format("?s %1$s %2$s", ENTITY_TYPE_REL, EntityTypeDAO.getEntityTypeRDFId(typeId));
    return this.query(bindings, start, max);
  }

  /**
   * Save a property into the knowledge base.
   * 
   * @param property
   *          The property to save.
   * @return The persisted property object.
   */
  public Property save(final Property property) {
    return super.saveImpl(property);
  }

}
