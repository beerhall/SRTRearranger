/**
 * 
 */
package models;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * SRT对象
 * 
 * @author BeerHall
 *
 */
public class SRTObj {
  /**
   * 日志工具
   */
  private Logger log = LogManager.getLogger(SRTObj.class.getName());

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
    log.debug("从文本构造SRTObj：" + this.hashCode());
    String[] lines = _text.split("\n");
    setIndex(Integer.parseInt(lines[0]));
    StringBuilder sb = new StringBuilder();
    for (int i = 2; i < lines.length; i++) {
      sb.append(lines[i] + "\n");
    }
    setText(sb.toString());

    String[] durations = lines[1].split("-->");
    setStart(getDuration(durations[0]));
    setEnd(getDuration(durations[1]));
  }

  /**
   * 获取时间
   * 
   * @param str 字符串
   * @return 获取的时间
   */
  private Duration getDuration(String str) {
    str = str.trim();
    String[] times = str.split(",");
    String[] time3 = times[0].split(":");

    long millis = Long.parseLong(times[1]);
    long seconds = Long.parseLong(time3[2]);
    long minutes = Long.parseLong(time3[1]);
    long hours = Long.parseLong(time3[0]);

    Duration duration = Duration.ofMillis(millis);
    duration = duration.plusSeconds(seconds);
    duration = duration.plusMinutes(minutes);
    duration = duration.plusHours(hours);

    return duration;
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
    log.debug("设置SRTObj：" + this.hashCode() + "序号为" + index);
    this.index = index;
  }

  /**
   * @return the start
   */
  public Duration getStart() {
    return start;
  }


  /**
   * @param start the start to set
   */
  public void setStart(Duration start) {
    log.debug("设置SRTObj：" + this.hashCode() + "开始时间为" + start);
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
    log.debug("设置SRTObj：" + this.hashCode() + "结束时间为" + end);
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
