package eu.scape_project.watch.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import thewebsemantic.Id;
import thewebsemantic.Namespace;
import thewebsemantic.binding.Jenabean;
import thewebsemantic.binding.RdfBean;
import eu.scape_project.watch.dao.DAO;
import eu.scape_project.watch.domain.Entity;
import eu.scape_project.watch.domain.EntityType;
import eu.scape_project.watch.domain.Measurement;
import eu.scape_project.watch.domain.Property;
import eu.scape_project.watch.domain.PropertyValue;
import eu.scape_project.watch.domain.Source;
import eu.scape_project.watch.domain.SourceAdaptor;
import eu.scape_project.watch.utils.JavaUtils;
import eu.scape_project.watch.utils.KBUtils;
import eu.scape_project.watch.utils.exceptions.InvalidJavaClassForDataTypeException;
import eu.scape_project.watch.utils.exceptions.UnsupportedDataTypeException;

public class ValueStrangeCharsTest {

  /**
   * The logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ValueStrangeCharsTest.class);

  /**
   * A temporary directory to hold the data.
   */
  private File dataTempir;

  /**
   * Initialize the data folder.
   * 
   * @throws IOException
   *           Error creating temporary data folder
   */
  @Before
  public void before() throws IOException {
    dataTempir = JavaUtils.createTempDirectory();
    KBUtils.dbConnect(dataTempir.getPath(), false);
  }

  /**
   * Cleanup the data folder.
   */
  @After
  public void after() {
    LOG.info("Deleting data folder at " + dataTempir);
    KBUtils.dbDisconnect();
    FileUtils.deleteQuietly(dataTempir);
  }

  /**
   * Testing strange chars in value.
   * 
   * @throws InvalidJavaClassForDataTypeException
   * @throws UnsupportedDataTypeException
   */
  @Test
  public void testStrangeCharsInValue() throws UnsupportedDataTypeException, InvalidJavaClassForDataTypeException {

    final String strange_schars = " âñüç!\"<>«»€#%\n\r\0test";

    final EntityType type = new EntityType("type", "test");
    final Entity entity = new Entity(type, "entity");
    final Property property = new Property(type, "property", "");
    final Source source = new Source("source", "");
    final SourceAdaptor adaptor = new SourceAdaptor("adaptor", "0.0.1", "default", source, new ArrayList<EntityType>(),
      new ArrayList<Property>(), new HashMap<String, String>());

    DAO.save(type);
    DAO.save(entity);
    DAO.save(property);
    DAO.save(source);
    DAO.save(adaptor);

    final PropertyValue pv1 = DAO.PROPERTY_VALUE.save(adaptor, new PropertyValue(entity, property, strange_schars));
    final PropertyValue pv2 = DAO.PROPERTY_VALUE.save(adaptor, new PropertyValue(entity, property, strange_schars));
    final PropertyValue pv3 = DAO.PROPERTY_VALUE.save(adaptor,
      new PropertyValue(entity, property, strange_schars + "2"));

    final int versionCount = DAO.PROPERTY_VALUE.countWithEntityAndProperty(entity.getId(), property.getId());
    Assert.assertEquals(2, versionCount);

    final int pv1MeasurementCount = DAO.MEASUREMENT.countByPropertyValue(pv1, false);
    Assert.assertEquals(2, pv1MeasurementCount);
    
    final int pv3MeasurementCount = DAO.MEASUREMENT.countByPropertyValue(pv3, false);
    Assert.assertEquals(1, pv3MeasurementCount);

  }

}
