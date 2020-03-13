package com.guns21.example.guava;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;
import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Optional;

/**
 * 动态设置泛型
 * @author jliu
 * @date 2019-11-28
 */
public class TypeTokenMain {
    public static void main(String[] args) throws NoSuchFieldException {
        Type a = A.class.getField("a").getGenericType();
        ParameterizedType pt = (ParameterizedType) a;
        System.err.println(pt.getActualTypeArguments()[0]);
        Type t = ofArrayListType(TypeToken.of(pt.getActualTypeArguments()[0]));
        System.err.println(t);
        Type shadeType = TypeUtils.parameterize(MessageResult.class, TypeUtils.parameterize(ArrayList.class, pt.getActualTypeArguments()));
        System.err.println(shadeType);

    }

    public static <K> Type ofArrayListType(TypeToken<K> keyToken) {
        return new TypeToken<MessageResult<ArrayList<K>>>(){}
                .where(new TypeParameter<K>() {},  keyToken).getType();
    }

    public static class A {
        public Optional<A> a;
    }

    public static class MessageResult<T> {

    }
}
