/**
 * 
 */
package main;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import models.SRTDoc;

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
    try {
      SRTDoc doc = new SRTDoc(new File("input.srt"));
    } catch (IOException e) {
      log.error(e);
      e.printStackTrace();
    }
  }

}
