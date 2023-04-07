package ir.mapsa.secondspringproject.codegenerator;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Set<Class<?>> set = findAnnotatedClasses();
        set.forEach(i -> {
            StringBuilder code = initialCode();
            String className = i.getSimpleName() + "Impl";
            code.append(className + " implements " + i.getSimpleName() + "{\n");
            Arrays.stream(i.getMethods()).forEach(method -> {
                Class<?> parameterType = method.getParameters()[0].getType();
                code.append(methodStarter(parameterType, method));

                BeanInfo beanInfo = null;
                try {
                    beanInfo = Introspector.getBeanInfo(parameterType);
                } catch (IntrospectionException e) {
                    throw new RuntimeException(e);
                }

                Arrays.stream(beanInfo.getPropertyDescriptors())
                        .forEach(property -> {
                            code.append(generateFieldCode(method, property));
                        });
                code.append("\t\treturn result;\n").append("\t}\n");
            });

            code.append("}");

            writeToFile(code.toString(), className);
        });
    }

    private static void writeToFile(String content, String className) {
        try {
            FileWriter fw = new FileWriter(
                    "/Users/pooya/projects/second-spring-project/src/main/java/ir/mapsa" + "/secondspringproject/codegenerator/" + className + ".java");
            fw.write(content);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static StringBuilder generateFieldCode(Method method, PropertyDescriptor property) {
        StringBuilder code = new StringBuilder();
        try {
            method.getReturnType().getDeclaredField(property.getName());
            if (property.getPropertyType() == method.getReturnType()
                    .getDeclaredField(property.getName()).getType()) {
                code.append("\t\tresult." + property.getWriteMethod()
                        .getName() + "(dto." + property.getReadMethod().getName() + "());\n");
            }
        } catch (NoSuchFieldException e) {

        }
        return code;
    }

    private static StringBuilder methodStarter(Class<?> parameterType, Method method) {
        String returnTypeName = method.getReturnType().getName();
        StringBuilder code = new StringBuilder();
        code.append(
                "\t@Override\n\tpublic " + returnTypeName + " " + method.getName() + "(" + parameterType.getName() + " dto){\n");
        code.append("\t\tif(dto==null){\n \t\t\treturn null; \n\t\t}\n");
        code.append("\t\t" + returnTypeName + " result=new " + returnTypeName + "();\n");
        return code;
    }

    private static Set<Class<?>> findAnnotatedClasses() {
        Reflections reflections = new Reflections("ir.mapsa", new TypeAnnotationsScanner(), new SubTypesScanner());

        Set<Class<?>> set = reflections.getTypesAnnotatedWith(MyMapper.class);
        return set;
    }

    private static StringBuilder initialCode() {
        StringBuilder code = new StringBuilder();
        code.append("package ir.mapsa.secondspringproject.codegenerator;\n");
        code.append("public class ");
        return code;
    }

//    private static String toPascalCase(String name) {
//        return name.substring(0, 1).toUpperCase() + name.substring(1);
//    }
}
