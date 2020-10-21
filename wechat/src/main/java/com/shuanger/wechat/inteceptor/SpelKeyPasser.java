package com.shuanger.wechat.inteceptor;

import com.shuanger.wechat.config.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-10-21 18:32
 * @description:
 */
@Slf4j
public final class SpelKeyPasser {

    public static String parseExpressionKey(String keyExpression, Object[] args) {
        try {
            if (!keyExpression.contains(AppConstants.POUND_SIGN) &&
                    !keyExpression.contains(AppConstants.SINGLE_QUOTATION_MARK)) {
                return keyExpression;
            }

            StandardEvaluationContext context = new StandardEvaluationContext();

            // add self define arguments
            context.setVariable("args", args);

            ExpressionParser parser = new SpelExpressionParser();

            Expression expression = parser.parseExpression(keyExpression);

            return expression.getValue(context, String.class);
        } catch (Exception e) {
            log.error("SPEL key解析失败 ：{}", keyExpression, e);
            return null;
        }
    }
}
