package org.xuanchengwei.jpa.generator;

import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.xuanchengwei.jpa.generator.config.GlobalConfig;
import org.xuanchengwei.jpa.generator.config.PackageConfig;
import org.xuanchengwei.jpa.generator.config.ClassConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.regex.Matcher;

/**
 * @author XUAN-CW
 * @date 2021/8/21 - 1:28
 */
@Setter
@Accessors(chain = true)
public class JpaCodeGenerator {

    ClassConfig classConfig;
    PackageConfig packageConfig;
    GlobalConfig globalConfig;

    public <T> JpaCodeGenerator(Class<T> entity){
        initClassConfig(entity);
        initPackageConfig(entity);
        initGlobalConfig(entity);
    }

    public <T> void initClassConfig(Class<T> entity) {
        ClassConfig classConfig = new ClassConfig();
        classConfig.setEntity(entity.getSimpleName());
        classConfig.setEntityInHyphen(entity.getSimpleName()
                .replaceAll("(?<=.)([A-Z])","-$1")
                .replaceAll("_","-").toLowerCase());
        for (Field field : entity.getDeclaredFields()){
            for (Annotation annotation : field.getDeclaredAnnotations()){
                if (annotation.toString().endsWith("Id()")) {
                    classConfig.setEntityIdType(field.getType().getSimpleName());
                    break;
                }
            }
        }
        this.classConfig = classConfig;
    }

    public <T> void initPackageConfig(Class<T> entity) {
        PackageConfig packageConfig = new PackageConfig();
        String entityPackageFullName = entity.getPackage().getName();
        packageConfig.setEntity(entityPackageFullName
                .substring(entityPackageFullName.lastIndexOf(".")+1));
        packageConfig.setParent(entityPackageFullName
                .substring(0,entityPackageFullName.lastIndexOf(".")));
        packageConfig.setModuleName(packageConfig.getParent()
                .substring(packageConfig.getParent().lastIndexOf(".")+1));
        packageConfig.setModuleNameInHyphen(packageConfig.getModuleName()
                .replaceAll("(?<=.)([A-Z])","-$1")
                .replaceAll("_","-").toLowerCase());
        this.packageConfig = packageConfig;
    }

    public <T> void initGlobalConfig(Class<T> entity) {
        GlobalConfig globalConfig = new GlobalConfig();
        String outputDir = System.getProperty("user.dir")+File.separator
                + "src"+ File.separator +"main"+File.separator+"java"+File.separator
                + entity.getPackage().getName().substring(0,entity.getPackage().getName().lastIndexOf("."))
                .replaceAll("\\.", Matcher.quoteReplacement(File.separator));
        System.out.println(outputDir);
        globalConfig.setOutputDir(outputDir);
        this.globalConfig = globalConfig;
    }

    public void generate(){
        output("controller.vm", packageConfig.getController(),
                classConfig.getEntity()+ classConfig.getController() + ".java");
        output("service.vm", packageConfig.getService(),
                classConfig.getEntity()+ classConfig.getService() + ".java");
        output("serviceImpl.vm", packageConfig.getServiceImpl(),
                classConfig.getEntity()+ classConfig.getServiceImpl() + ".java");
        output("repository.vm", packageConfig.getRepository(),
                classConfig.getEntity()+ classConfig.getRepository() + ".java");
    }

    private void output(String template,String packageName,String fileName){
        File thisFileOutputTo = new File((globalConfig.getOutputDir()+File.separator+packageName)
                .replaceAll("\\.", Matcher.quoteReplacement(File.separator)));
        thisFileOutputTo.mkdir();
        File outputFile = new File(thisFileOutputTo.getAbsoluteFile()+File.separator+fileName);

        VelocityEngine ve=new VelocityEngine();
        //设置模板加载路径，这里设置的是class下
        ve.setProperty(Velocity.RESOURCE_LOADER, "class");
        ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        //进行初始化操作
        ve.init();
            // 设置传递的参数
        VelocityContext context = new VelocityContext();
        context.put(classConfig.getClass().getSimpleName(), classConfig);
        context.put(packageConfig.getClass().getSimpleName(),packageConfig);
        context.put(globalConfig.getClass().getSimpleName(),globalConfig);
        //加载模板，设定模板编码
        Template t=ve.getTemplate(template,"utf8");
        //设置输出路径
        try(PrintWriter controllerWriter = new PrintWriter(outputFile.getAbsoluteFile())){
            //将环境数据转化输出
            t.merge(context, controllerWriter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
