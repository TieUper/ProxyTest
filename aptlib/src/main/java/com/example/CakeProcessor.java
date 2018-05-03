package com.example;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * 处理Annotation类
 * Created by Administrator on 2018/5/3.
 */
@SupportedAnnotationTypes("com.example.CustomAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class CakeProcessor extends AbstractProcessor {


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        StringBuilder builder = new StringBuilder()
                .append("package com.example;")
                .append("public class GeneratedClass {") // open class
                .append("public String getMessage() {") // open method
                .append("return ");


                        // for each javax.lang.model.element.Element annotated with the CustomAnnotation
        for (Element element : roundEnv.getElementsAnnotatedWith(CustomAnnotation.class)) {
            String objectType = element.getSimpleName().toString();



            // this is appending to the return statement
            builder.append(objectType).append(" says hello!\n");
        }


        builder.append(";")  //end return
                .append("}") // close method
                .append("}"); // close class

        System.out.println("-------------" + builder.toString());



        try { // write the file
            JavaFileObject source = processingEnv.getFiler().createSourceFile("com.example.GeneratedClass");


            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // Note: calling e.printStackTrace() will print IO errors
            // that occur from the file already existing after its first run, this is normal
        }


        return true;
    }
}
