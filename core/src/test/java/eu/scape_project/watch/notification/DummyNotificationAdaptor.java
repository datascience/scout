package eu.scape_project.watch.notification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eu.scape_project.watch.domain.AsyncRequest;
import eu.scape_project.watch.domain.DataType;
import eu.scape_project.watch.domain.Notification;
import eu.scape_project.watch.domain.Trigger;
import eu.scape_project.watch.interfaces.NotificationPluginInterface;
import eu.scape_project.watch.interfaces.PluginType;
import eu.scape_project.watch.utils.ConfigParameter;
import eu.scape_project.watch.utils.exceptions.PluginException;

/**
 * 
 * Notification adaptor to be used in tests.
 * 
 * @author Luis Faria <lfaria@keep.pt>
 * 
 */
public class DummyNotificationAdaptor implements NotificationPluginInterface {

  /**
   * The notification plugin name.
   */
  private static final String NAME = LogNotificationAdaptor.class.getSimpleName();

  /**
   * The notification plugin version.
   */
  private static final String VERSION = "0.0.2";

  /**
   * Test notification type.
   */
  public static final String TEST_TYPE = "test";

  /**
   * Supported types.
   */
  private static final Set<String> TYPES = new HashSet<String>(Arrays.asList(TEST_TYPE));

  /**
   * List of sent notifications.
   */
  private final List<Notification> notifications = new ArrayList<Notification>();

  /**
   * Whereas this adaptor should consume the events or not.
   */
  private boolean consumeEvent = false;

  @Override
  public Set<String> getSupportedTypes() {
    return TYPES;
  }

  @Override
  public boolean send(final Notification notification, final Trigger trigger, final AsyncRequest request) {
    this.notifications.add(notification);
    return this.consumeEvent;
  }

  public List<Notification> getNotifications() {
    return this.notifications;
  }

  public boolean isConsumeEvent() {
    return this.consumeEvent;
  }

  public void setConsumeEvent(final boolean consumeEvent) {
    this.consumeEvent = consumeEvent;
  }

  @Override
  public void init() throws PluginException {
    // nothing to do
  }

  @Override
  public void shutdown() throws PluginException {
    this.notifications.clear();
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public String getVersion() {
    return VERSION;
  }

  @Override
  public String getDescription() {
    return "Test notification plugin that keeps notifications in a list to later check if it was sent";
  }

  @Override
  public PluginType getPluginType() {
    return PluginType.NOTIFICATION;
  }

  @Override
  public Map<String, DataType> getParametersInfo() {
    return new HashMap<String, DataType>();
  }

  @Override
  public List<ConfigParameter> getParameters() {
    return new ArrayList<ConfigParameter>();
  }

}
