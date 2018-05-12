package com.annotation;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * 处理Annotation类
 * Created by Administrator on 2018/5/3.
 */
@AutoService(Processor.class)
public class CakeProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .build();
        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();
        JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld)
                .build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Test.class.getCanonicalName());
    }

    //    @Override
//    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        StringBuilder builder = new StringBuilder()
//                .append("package com.annotation;")
//                .append("public class GeneratedClass {") // open class
//                .append("public String getMessage() {") // open method
//                .append("return ");
//
//
//                        // for each javax.lang.model.element.Element annotated with the CustomAnnotation
//        for (Element element : roundEnv.getElementsAnnotatedWith(CustomAnnotation.class)) {
//            String objectType = element.getSimpleName().toString();
//
//
//
//            // this is appending to the return statement
//            builder.append(objectType).append(" says hello!\n");
//        }
//
//
//        builder.append(";")  //end return
//                .append("}") // close method
//                .append("}"); // close class
//
//        System.out.println("-------------" + builder.toString());
//
//
//
//        try { // write the file
//            JavaFileObject source = processingEnv.getFiler().createSourceFile("com.annotation.GeneratedClass");
//
//
//            Writer writer = source.openWriter();
//            writer.write(builder.toString());
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            // Note: calling e.printStackTrace() will print IO errors
//            // that occur from the file already existing after its first run, this is normal
//        }
//
//
//        return true;
//    }
}
