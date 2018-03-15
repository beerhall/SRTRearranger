/**
 * 
 */
package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * SRT文档
 * 
 * @author BeerHall
 *
 */
public class SRTDoc {
  /**
   * 日志工具
   */
  private Logger log = LogManager.getLogger(SRTDoc.class.getName());
  /**
   * 文件
   */
  private File file;
  /**
   * SRT对象表
   */
  private ArrayList<SRTObj> list = new ArrayList<SRTObj>();

  /**
   * 构造函数
   * 
   * @param _file 文件
   * @throws IOException
   */
  public SRTDoc(File _file) throws IOException {
    log.debug("构造SRTDoc：" + this.hashCode() + "，文件：" + _file.getName());
    setFile(_file);
    BufferedReader br = new BufferedReader(new FileReader(getFile()));
    String line;
    StringBuilder sb = new StringBuilder();
    log.info("读取文件：" + getFile().getName());
    while ((line = br.readLine()) != null) {
      if (!line.isEmpty()) {
        sb.append(line + "\n");
      } else {
        log.debug("在SRTDoc：" + this.hashCode() + "中读取了" + sb.toString().split("\n")[0]);
        list.add(new SRTObj(sb.toString()));
        sb = new StringBuilder();
      }
    }
    br.close();
  }

  /**
   * 构造函数
   */
  public SRTDoc() {
    log.debug("构造SRTDoc：" + this.hashCode());
  }

  /**
   * 输出
   * 
   * @param file_output 输出文件
   */
  public void output(File file_output) {
    // TODO
  }

  /**
   * @return the file
   */
  public File getFile() {
    return file;
  }

  /**
   * @param file the file to set
   */
  public void setFile(File file) {
    this.file = file;
  }

  /**
   * @return the list
   */
  public ArrayList<SRTObj> getList() {
    return list;
  }

  /**
   * @param list the list to set
   */
  public void setList(ArrayList<SRTObj> list) {
    this.list = list;
  }

}
