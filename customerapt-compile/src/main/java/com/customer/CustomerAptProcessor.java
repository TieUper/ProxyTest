package com.customer;

import com.annotation.BindView;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * Created by Administrator on 2018/5/8.
 */
@AutoService(Processor.class)
public class CustomerAptProcessor extends AbstractProcessor {

    //监听事件
    private static final List<Class<? extends Annotation>> LISTENERS = Arrays.asList();

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        return false;
    }
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        for (Class<? extends Annotation> annotation : getSupportedAnnotations()) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(BindView.class);
        annotations.addAll(LISTENERS);

        return annotations;
    }

    private void findAndParseTargets(RoundEnvironment env) {

        for (Element element : env.getElementsAnnotatedWith(BindView.class)) {

        }
    }
}
