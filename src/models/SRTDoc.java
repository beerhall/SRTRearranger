/**
 * 
 */
package models;

import java.io.File;
import java.util.ArrayList;

/**
 * SRT文档
 * 
 * @author BeerHall
 *
 */
public class SRTDoc {
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
   */
  public SRTDoc(File _file) {
    setFile(_file);
  }

  /**
   * 输出
   */
  public void output() {
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
