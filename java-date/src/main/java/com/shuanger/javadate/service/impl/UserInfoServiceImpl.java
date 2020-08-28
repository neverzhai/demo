package com.shuanger.javadate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuanger.javadate.dao.UserInfoMapper;
import com.shuanger.javadate.domain.UserInfo;
import com.shuanger.javadate.service.UserInfoService;
import com.shuanger.javadate.utils.ExcelWriteUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-20 11:45
 * @description:
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public Workbook exportList() {
        SXSSFWorkbook workbook = exportListInternally();

        return workbook;
    }

    // 这个方法是不行的呀
    @Override
    @Async
    public Workbook exportAsyncList(HttpServletResponse response) {
        log.info("异步调用导出用户信息");

        SXSSFWorkbook workbook = exportListInternally();

        try {
            ExcelWriteUtil.export(response, workbook, "用户信息列表");
        } catch (Exception e) {
            log.error("异步调用导出用户信息列表异常: {}", e.getMessage());
        }

        return workbook;

    }

    private SXSSFWorkbook exportListInternally() {
        SXSSFWorkbook workbook = ExcelWriteUtil.createWorkbook("用户信息");

        String[] title = {"userId", "username", "mobile"};
        ExcelWriteUtil.writeHeader(workbook, title);

        LambdaQueryWrapper<UserInfo> wrapper = new QueryWrapper<UserInfo>().lambda()
                .eq(UserInfo::getDeleted, 0);

        List<UserInfo> userInfos = list(wrapper);
        String[][] content = new String[userInfos.size()][title.length];

        for (int rowIndex = 0; rowIndex < userInfos.size(); rowIndex++) {
            int columnIndex = 0;
            UserInfo userInfo = userInfos.get(rowIndex);
            content[rowIndex][columnIndex++] = String.valueOf(userInfo.getId());
            content[rowIndex][columnIndex++] = userInfo.getUsername();
            content[rowIndex][columnIndex++] = userInfo.getMobile();
        }

        ExcelWriteUtil.writeContent(workbook, content, 0);
        return workbook;
    }
}
