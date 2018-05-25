/**
 * 
 */
package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import models.SRTDoc;
import models.SRTObj;

/**
 * 重排器
 * 
 * @author BeerHall
 *
 */
public class Transferer {
  static Logger log = LogManager.getLogger(Transferer.class.getName());
  private static int lineIndex = 0;
  private static int charIndex = 0;
  static ArrayList<Duration> startDuration = new ArrayList<Duration>();
  static ArrayList<Duration> endDuration = new ArrayList<Duration>();

  /**
   * 重排
   * 
   * @param doc SRT文档
   * @param en 重排后的文档
   * @param cn 中文翻译文档
   * @throws IOException
   */
  public static void transfer(SRTDoc doc, File en, File cn) throws IOException {
    log.info(
        "以重排后文档" + en.getName() + "为基准转换原SRTDoc：" + doc.hashCode() + "，并附上中文翻译：" + cn.getName());

    BufferedReader br = new BufferedReader(new FileReader(en));
    String line;
    ArrayList<String> enList = new ArrayList<String>();
    log.debug("读取重排后文档文档:" + en.getName());
    while ((line = br.readLine()) != null) {
      enList.add(line);
    }
    br.close();

    br = new BufferedReader(new FileReader(cn));
    ArrayList<String> cnList = new ArrayList<String>();
    log.debug("读取汉化文档:" + cn.getName());
    while ((line = br.readLine()) != null) {
      cnList.add(line);
    }

    br.close();

    ArrayList<SRTObj> objList = doc.getList();

    for (SRTObj obj : objList) {
      line = obj.getText();
      log.debug("正在处理第" + obj.getIndex() + "行文本：" + line);
      for (int i = 0; i < line.length(); i++) {
        char ch = line.charAt(i);
        if (!Character.isWhitespace(ch)) {
          log.debug("正在处理第" + i + "个字符" + ch);
          if (Character.isLetter(ch)) {
            while (Character.toUpperCase(ch) != Character
                .toUpperCase(NextEnChar(enList, obj, i, line)));
          } else {
            while (ch != NextEnChar(enList, obj, i, line));
          }
        } else {
          log.debug("在第" + obj.getIndex() + "行第" + i + "位遇到空格，跳过");
        }
      }
    }
    endDuration.add(objList.get(objList.size() - 1).getEnd());
    int i = 0;
    File outputfile = new File("output.srt");
    SRTDoc transdoc = new SRTDoc();
    for (String str : enList) {
      SRTObj nsrtl =
          new SRTObj(i + 1, startDuration.get(i), endDuration.get(i), cnList.get(i++) + "\n" + str);
      transdoc.getList().add(nsrtl);
    }
    transdoc.output(outputfile);
  }

  /**
   * 英文重排文档的下一个字符
   * 
   * @param enList 英文重排文档
   * @return 下一个字符
   */
  public static char NextEnChar(ArrayList<String> enList, SRTObj obj, int i, String line) {
    log.debug("获取英文文档的下一个字符，当前为第" + lineIndex + "行第" + charIndex + "个,为"
        + enList.get(lineIndex).charAt(charIndex));


    while (enList.get(lineIndex).length() == charIndex + 1) {
      endDuration.add(GetCurrentDuration(obj.getStart(), obj.getEnd(), i, line.length()));
      log.debug("添加结束时间到第" + lineIndex + "行");
      charIndex = 0;
      lineIndex++;
    }
    if (charIndex == 0) {
      startDuration.add(GetCurrentDuration(obj.getStart(), obj.getEnd(), i, line.length()));
      log.debug("添加初始时间到第" + lineIndex + "行");
    }
    return enList.get(lineIndex).charAt(charIndex++);
  }

  /**
   * 获取当前时间
   * 
   * @param start 开始时间
   * @param end 结束时间
   * @param i 坐标
   * @param length 长度
   * @return 当前时间
   */
  public static Duration GetCurrentDuration(Duration start, Duration end, int i, int length) {
    return start.plus(end.minus(start).multipliedBy(i + 1).dividedBy(length));
  }
}
