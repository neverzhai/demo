package com.shuanger.dynamicdemo.staticProxy;

import com.shuanger.democommon.params.CreateActivityRequest;
import com.shuanger.democommon.params.DeleteActivityRequest;
import com.shuanger.dynamicdemo.originalObject.IActivityController;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-20 22:29
 * @description: 基于实现接口实现动态代理
 *              适用于有接口的情况下， 如果我们使用的第三方库，而要代理的对象并没有定义接口则无法使用， 需要通过继承类来实现
 */
public class InterfaceActivityControllerProxy  implements IActivityController {

    @Override
    public boolean createActivity(CreateActivityRequest request) {
        return false;
    }

    @Override
    public boolean deleteActivity(DeleteActivityRequest request) {
        return false;
    }
}