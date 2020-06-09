package com.shuanger.doctool.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.word.Word07Writer;

import java.awt.*;


/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-09 10:21
 * @description:
 */
public class WordUtil {

    public static void writeWord() {
        Word07Writer writer = new Word07Writer();

// 添加段落（标题）
        writer.addText(new Font("方正小标宋简体", Font.PLAIN, 22), "我是第一部分", "我是第二部分");
// 添加段落（正文）
        writer.addText(new Font("宋体", Font.PLAIN, 22), "我是正文第一部分", "我是正文第二部分");
// 写出到文件
        writer.flush(FileUtil.file("e:/wordWrite.docx"));
// 关闭
        writer.close();
    }
}
