package com.shuanger.doctool.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.word.Word07Writer;
import com.shuanger.doctool.domain.FormDataBody;
import com.shuanger.doctool.domain.PostmanRequest;
import com.shuanger.doctool.domain.PostmanRequestBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.util.List;


/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-09 10:21
 * @description:
 */
@Slf4j
@Service
public class WordUtilService {

    Word07Writer writer;

    public  void writeWord() {
        Word07Writer writer = new Word07Writer();

// 添加段落（标题）
        writer.addText(getNameFont(), "我是第一部分", "我是第二部分");
// 添加段落（正文）
        writer.addText(getNameFont(), "我是正文第一部分", "我是正文第二部分");
// 写出到文件
        writer.flush(FileUtil.file("e:/wordWrite.docx"));
// 关闭
        writer.close();
    }

    public void initWordWriterWithTitle(String title) {
        writer = new Word07Writer();
        writer.addText(ParagraphAlignment.CENTER, getTitleFont(), title);
    }

    public void writeName(int index, String requestName) {
        writer.addText(getNameFont(), ++index  + "." + requestName);
    }

    public void writePostRequest(PostmanRequest postmanRequest) {
        writer.addText(getContentFont(), " - 请求方式: " + postmanRequest.getMethod());
        writer.addText(getContentFont(), " - 请求URL: " + postmanRequest.getUrl().getRaw());

        PostmanRequestBody requestBody = postmanRequest.getBody();
        if(!StringUtils.isEmpty(requestBody)) {
            writer.addText(getContentFont(), " - 请求说明: " + requestBody.getMode());
            writer.addText(getContentFont(), " - 请求入参: ");

            writeRequestBody(requestBody);

            writer.addText(getContentFont(), " - 入参说明: ");
        }

        writer.addText(getContentFont(), " - 请求出参: ");
        writer.addText(getContentFont(), " - 出参说明: ");

    }

    public void writeGetRequest(PostmanRequest request) {
        writer.addText(getContentFont(), " - 请求方式: " + request.getMethod());
        writer.addText(getContentFont(), " - 请求URL: " + request.getUrl().getRaw());

        writer.addText(getContentFont(), " - 请求入参: 无 ");
        writer.addText(getContentFont(), " - 请求出参: ");
        writer.addText(getContentFont(), " - 出参说明: ");
    }

    private void writeRequestBody(PostmanRequestBody requestBody) {

        switch (requestBody.getMode()) {
            case "raw":
                writeJsonBody(requestBody);
                break;
            case "formdata":
                writeFormDataBody(requestBody);
                break;
            default:
                break;
        }
    }

    private void writeFormDataBody(PostmanRequestBody requestBody) {
        List<FormDataBody> formdata = requestBody.getFormdata();
        writer.addText(getCodeFont(), "     [");
        formdata.forEach(data -> writer.addText(getCodeFont(), "            " + JSONUtil.toJsonStr(data)));
        writer.addText(getCodeFont(), "     ]");
    }

    private void writeJsonBody(PostmanRequestBody requestBody) {
        String jsonString = requestBody.getRaw();
        String[] split = jsonString.split("\\,|\\{|\\}");

        writer.addText(getCodeFont(), "     {");
        for (String code : split) {
            if (!StringUtils.isEmpty(code)) {
                writer.addText(getCodeFont(),"      " + code);
            }
        }
        writer.addText(getCodeFont(), "     }");
    }

    public void flushAndClose(String title) {
        writer.flush(FileUtil.file("e:/" + title + ".docx"));
        writer.close();
    }

    private Font getContentFont() {
        return new Font("宋体", Font.PLAIN, 10);
    }

    private Font getCodeFont() {
        return new Font("方正姚体", Font.PLAIN, 10);
    }

    private Font getNameFont() {
        return new Font("宋体", Font.BOLD, 16);
    }

    private Font getTitleFont() {
        return new Font("方正小标宋简体", Font.CENTER_BASELINE, 24);

    }
}
