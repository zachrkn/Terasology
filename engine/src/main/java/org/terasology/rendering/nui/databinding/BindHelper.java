/*
 * Copyright 2013 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.rendering.nui.databinding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.classMetadata.reflect.ReflectFactory;
import org.terasology.classMetadata.reflect.ReflectionReflectFactory;
import org.terasology.utilities.ReflectionUtil;

import java.lang.reflect.Method;

/**
 * @author Immortius
 */
public final class BindHelper {

    private static final Logger logger = LoggerFactory.getLogger(BindHelper.class);
    private static ReflectFactory bindingFactory = new ReflectionReflectFactory();

    private BindHelper() {
    }

    public static <T> Binding<T> bindBeanProperty(String property, Object source, Class<T> propertyType) {
        Method getter = ReflectionUtil.findGetter(property, source.getClass(), propertyType);
        Method setter = ReflectionUtil.findSetter(property, source.getClass(), propertyType);
        if (getter == null || setter == null) {
            logger.warn("Failed to resolve property {} of type {} - is the getter or setter missing?", property, source.getClass());
            return new DefaultBinding<>();
        }
        return new BeanBinding<>(source, getter, setter);
    }
}
