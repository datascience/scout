package eu.scape_project.watch.scheduling.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.scape_project.watch.interfaces.AdaptorPluginInterface;
import eu.scape_project.watch.interfaces.ResultInterface;
import eu.scape_project.watch.utils.exceptions.PluginException;

/**
 * Quartz Adaptor Job class which wraps up an adaptor. 
 * @author Kresimir Duretec <duretec@ifs.tuwien.ac.at>
 *
 */
public class QuartzAdaptorJob implements Job {

  private static final Logger LOG = LoggerFactory.getLogger(QuartzAdaptorJob.class);

  /*
   * private String adaptorClassName;
   * 
   * private String adaptorVersion;
   * 
   * private Properties properties;
   * 
   * private String adaptorProperties;
   */

  private QuartzScheduler scheduler;

  private String adaptorId;

  private QuartzListenerManager lManager;

  AdaptorPluginInterface adaptor;

  public void setScheduler(QuartzScheduler scheduler) {
    this.scheduler = scheduler;
  }

  public void setAdaptorId(String adaptorId) {
    this.adaptorId = adaptorId;
  }

  public void setlManager(QuartzListenerManager lManager) {
    this.lManager = lManager;
  }

  public AdaptorPluginInterface getAdaptorPlugin() {
    return adaptor;
  }

  @Override
  public void execute(final JobExecutionContext jec) {

    adaptor = scheduler.getAdaptorPluginInterface(adaptorId);
    try {
      while (adaptor.hasNext()) {
        ResultInterface result = adaptor.next();
        lManager.notify(adaptor, result);
      }
      LOG.info(adaptor.getName()+" has nothing to give");
      jec.setResult(new Boolean(true));
      return;
    } catch (PluginException e) {
      LOG.warn("An exception occured in Adaptor");
      jec.put("exception", e);
      jec.setResult(new Boolean(false));
      return;
    }
  }

}
