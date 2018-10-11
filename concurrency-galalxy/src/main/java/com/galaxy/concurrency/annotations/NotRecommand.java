package com.galaxy.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标记不推荐的类或写法
 * <p>
 * Created by wangpeng
 * Date: 2018/10/11
 * Time: 10:59
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NotRecommand {

    String value() default "";
}
