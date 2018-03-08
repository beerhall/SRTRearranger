/**
 * 
 */
package models;

import java.time.Duration;

/**
 * SRT对象
 * 
 * @author BeerHall
 *
 */
public class SRTObj {

  /**
   * 序号
   */

  private int index;

  /*
   * 开始时间
   */
  private Duration start;

  /**
   * 结束时间
   */
  private Duration end;

  /**
   * 文本
   */
  private String text;


  /**
   * 构造函数
   * 
   * @param _index 编号
   * @param _start 开始时间
   * @param _end 结束时间
   * @param _text 文本
   */
  public SRTObj(int _index, Duration _start, Duration _end, String _text) {
    setIndex(_index);
    setStart(_start);
    setEnd(_end);
    setText(_text);
  }

  /**
   * 从文本构造
   * 
   * @param _text SRT文本
   */
  public SRTObj(String _text) {
    // TODO
  }

  /**
   * @return the index
   */
  public int getIndex() {
    return index;
  }

  /**
   * @param index the index to set
   */
  public void setIndex(int index) {
    this.index = index;
  }

  /**
   * @return the start
   */
  public Duration getStart() {
    return start;
  }

  /**
   * 解析SRT文本
   * 
   * @param _text SRT文本
   */
  private void parse(String _text) {
    // TODO
  }

  /**
   * @param start the start to set
   */
  public void setStart(Duration start) {
    this.start = start;
  }

  /**
   * @return the end
   */
  public Duration getEnd() {
    return end;
  }

  /**
   * @param end the end to set
   */
  public void setEnd(Duration end) {
    this.end = end;
  }

  /**
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * @param text the text to set
   */
  public void setText(String text) {
    this.text = text;
  }

}
