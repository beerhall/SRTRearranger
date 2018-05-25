/**
 * 
 */
package main;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import models.SRTDoc;
import utils.Transferer;

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
      SRTDoc doc = new SRTDoc(new File("3.srt"));
      Transferer.transfer(doc, new File("3_en.txt"), new File("3_zh.txt"));
    } catch (IOException e) {
      log.error(e);
      e.printStackTrace();
    }
  }

}
