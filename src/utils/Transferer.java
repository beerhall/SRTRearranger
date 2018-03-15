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
  /**
   * 重排
   * 
   * @param doc SRT文档
   * @param en 重排后的文档
   * @param cn 中文翻译文档
   * @throws IOException
   */
  public static void transfer(SRTDoc doc, File en, File cn) throws IOException {
    Logger log = LogManager.getLogger(Transferer.class.getName());
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

    ArrayList<SRTObj> objList = doc.getList();
    int iObjLine = 0;
    int iObjWord = 0;
    int iEnLline = 0;
    for (String enLine : enList) {
      Duration start = getCurrentDuration(objList, iObjWord, iObjLine);

      String[] enLines = enLine.split(" ");
      int enLen = enLines.length;
      String[] currentObjs = objList.get(iObjLine).getText().split(" ");
      for (int i = 0; i < enLen; i++) {
        currentObjs = objList.get(iObjLine).getText().split(" ");
        if (iObjWord >= currentObjs.length) {
          iObjWord = 0;
          iObjLine++;
        }
        iObjWord++;
      }
      String word = currentObjs[iObjWord];
      if (word.equals(enLines[enLen])) {
        log.debug("成功对照");
      } else {
        log.warn("对照失败，原词：" + word + "，重排词：" + enLines[enLen]);
        log.warn("原词位置：第" + iObjLine + "行，第" + iObjWord + "个词");
        log.warn("重排位置：第" + iEnLline + "行，第" + enLen + "个词");
      }
      Duration end = getCurrentDuration(objList, iObjWord, iObjLine);
      new SRTObj(iEnLline + 1, start, end, enLine);
      iEnLline++;
    }
  }

  /**
   * 获取当前时间
   * 
   * @param objList SRTObj列表
   * @param iObjWord obj单词序号
   * @param iObjLine obj行号
   * @return
   */
  private static Duration getCurrentDuration(ArrayList<SRTObj> objList, int iObjWord,
      int iObjLine) {
    SRTObj currentObj = objList.get(iObjLine);
    Duration start = currentObj.getStart();
    Duration end = currentObj.getEnd();

    int totalLength = currentObj.getText().length();
    String[] words = currentObj.getText().split(" ");
    int length = 0;

    for (int i = 0; i < iObjWord; i++) {
      length += words[i].length();
      length++;
    }
    length--;

    return start.plus(end.minus(start).multipliedBy(totalLength).dividedBy(length));
  }
}
