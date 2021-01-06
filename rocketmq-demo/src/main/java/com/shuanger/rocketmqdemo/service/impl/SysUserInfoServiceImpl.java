package com.shuanger.rocketmqdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuanger.rocketmqdemo.dao.MemberSysUserInfoMapper;
import com.shuanger.rocketmqdemo.domain.SysUserInfo;
import com.shuanger.rocketmqdemo.domain.SyncUserRequest;
import com.shuanger.rocketmqdemo.exception.BusinessException;
import com.shuanger.rocketmqdemo.service.ISysUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 从用户中心同步用户数据 服务实现类
 * </p>
 *
 * @author zhaixiaoshuang
 * @since 2020-11-09
 */
@Slf4j
@Service
public class SysUserInfoServiceImpl extends ServiceImpl<MemberSysUserInfoMapper, SysUserInfo> implements ISysUserInfoService {

    public SysUserInfo queryBy(SFunction<SysUserInfo, Object> condition, Object value) {
                LambdaQueryWrapper<SysUserInfo> queryWrapper = new QueryWrapper<SysUserInfo>().lambda()
                .eq(condition, value);
        return getOne(queryWrapper);
    }


    public boolean saveOrUpdateUserInfo(SyncUserRequest request) {
        SysUserInfo userInfo = queryBy(SysUserInfo::getUserId, request.getUserId());
        if(ObjectUtils.isNull(userInfo)) {
            userInfo = new SysUserInfo();
//            return false;
        }
        log.info("同步之前用户信息： {}", JSON.toJSONString(userInfo));

        BeanUtils.copyProperties(request, userInfo,  getNullOrEmptyPropertyNames(request));

        log.info("同步之后用户信息： {}", JSON.toJSONString(userInfo));
        return saveOrUpdate(userInfo);
    }

    private String[] getNullOrEmptyPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (ObjectUtils.isNull(srcValue))
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
