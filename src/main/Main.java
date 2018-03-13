/**
 * 
 */
package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author BeerHall
 *
 */
public class Main {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Logger log = LogManager.getLogger(Main.class.getName());
    log.info("This is an info log");
    log.debug("This is an debug log");
    log.warn("This is an warn log");
    log.error("This is an error log");
  }

}
