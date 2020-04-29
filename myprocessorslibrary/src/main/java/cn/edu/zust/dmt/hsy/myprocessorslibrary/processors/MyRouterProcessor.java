package cn.edu.zust.dmt.hsy.myprocessorslibrary.processors;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import cn.edu.zust.dmt.hsy.myannotationslibrary.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.myannotationslibrary.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.myannotationslibrary.constants.MyRouterSettings;

import static cn.edu.zust.dmt.hsy.myprocessorslibrary.utils.MyErrorUtils.showMyArgumentException;
import static cn.edu.zust.dmt.hsy.myprocessorslibrary.utils.MyErrorUtils.showMyNullPointerException;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/10/2020 21:13
 **/
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public final class MyRouterProcessor extends AbstractProcessor {
    /**
     * @return strings of canonical name for supported annotations
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> set = new HashSet<>();
        set.add(MyRouter.class.getCanonicalName());
        return set;
    }

    /**
     * @return strings of javaCompileOptions this processor supported
     */
    @Override
    public Set<String> getSupportedOptions() {
        final Set<String> set = new HashSet<>();
        set.add(innerConstants.INSTANCE.SUPPORTED_OPTIONS_NAME);
        return set;
    }

    /**
     * @description class for hold all constants for this processor
     */
    private static final class innerConstants {
        private innerConstants() {
            MyRouterSettings myRouterSettings = new MyRouterSettings();
            this.SUPPORTED_CLASS_PATH = myRouterSettings.SUPPORTED_CLASS_PATH;
            this.GENERATED_FILE_PATH = myRouterSettings.GENERATED_FILE_PATH;
            this.GENERATED_INTERFACE_NAME = myRouterSettings.GENERATED_INTERFACE_NAME;
            this.GENERATED_METHOD_NAME = myRouterSettings.GENERATED_METHOD_NAME;
        }

        private static final innerConstants INSTANCE = new innerConstants();
        private final String SUPPORTED_OPTIONS_NAME = "modulePrefix";
        private final String SUPPORTED_CLASS_PATH;
        private final String GENERATED_FILE_PATH;
        private final String GENERATED_INTERFACE_NAME;
        private final String GENERATED_METHOD_NAME;
    }

    private Elements mElementUtils = null;
    private Filer mFilerUtils = null;
    private Types mTypeUtils = null;

    private String mModulePrefix = null;

    /**
     * @param processingEnvironment apt running environment
     */
    @Override
    public synchronized void init(final ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mElementUtils = processingEnvironment.getElementUtils();
        mFilerUtils = processingEnvironment.getFiler();
        mTypeUtils = processingEnvironment.getTypeUtils();

        if (mElementUtils == null || mFilerUtils == null || mTypeUtils == null) {
            showMyNullPointerException("Processor environment initialized error!");
        }

        Map<String, String> options = processingEnvironment.getOptions();
        if (!options.isEmpty()) {
            try {
                mModulePrefix = options.get(innerConstants.INSTANCE.SUPPORTED_OPTIONS_NAME);
            } catch (NullPointerException e) {
                showMyNullPointerException("JavaCompileOptions arguments is not defined!");
            }
        }
        if (mModulePrefix.isEmpty()) {
            showMyArgumentException("JavaCompileOptions arguments is empty!");
        }
    }

    //todo:check meanings of return by process method

    /**
     * @param set              elements of code tree where used {@link MyRouter}
     * @param roundEnvironment compile environment for set
     * @return should other processors process {@link MyRouter} annotations on elements or not
     */
    @Override
    public boolean process(final Set<? extends TypeElement> set, final RoundEnvironment roundEnvironment) {
        if (!set.isEmpty()) {
            final Set<? extends Element> annotatedElementSet = roundEnvironment
                    .getElementsAnnotatedWith(MyRouter.class);
            if (!annotatedElementSet.isEmpty()) {
                processAnnotations(annotatedElementSet);
            }
        }
        return false;
    }

    /**
     * @param annotatedElementSet set contains elements annotated by {@link MyRouter}
     */
    private void processAnnotations(final Set<? extends Element> annotatedElementSet) {
        final Map<MyRouterPaths, String> mRouterMetaMap = new HashMap<>();
        for (Element element : annotatedElementSet) {
            final TypeMirror typeMirror = element.asType();
            final MyRouter myRouter = element.getAnnotation(MyRouter.class);
            //set supported class here
            if (mTypeUtils.isSubtype(typeMirror, mElementUtils
                    .getTypeElement(innerConstants.INSTANCE.SUPPORTED_CLASS_PATH).asType())) {
                mRouterMetaMap.put(myRouter.path(), typeMirror.toString());
            } else {
                showMyArgumentException("MyRouterPath only support BaseActivity!");
            }
        }
        generateRecorderFile(mRouterMetaMap);
    }

    /**
     * @param pathToTargetMap for creating path java file
     */
    private void generateRecorderFile(final Map<MyRouterPaths, String> pathToTargetMap) {
        final ParameterizedTypeName myParameterizedTypeName = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(MyRouterPaths.class),
                ClassName.get(String.class));

        final MethodSpec.Builder myMethodBuilder = MethodSpec
                .methodBuilder(innerConstants.INSTANCE.GENERATED_METHOD_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(Override.class)
                .returns(myParameterizedTypeName)
                .addStatement("final $T returnMap = new $T<>()", myParameterizedTypeName, HashMap.class);
        for (MyRouterPaths key : pathToTargetMap.keySet()) {
            myMethodBuilder.addStatement("returnMap.put($L.$L, $S)", MyRouterPaths.class.getSimpleName()
                    , key, pathToTargetMap.get(key));
        }
        myMethodBuilder.addStatement("return returnMap");

        final TypeSpec myClass = TypeSpec.enumBuilder(mModulePrefix + "MyRouterRecorder")
                .addSuperinterface(ClassName.get(mElementUtils
                        .getTypeElement(innerConstants.INSTANCE.GENERATED_INTERFACE_NAME)))
                .addModifiers(Modifier.PUBLIC)
                .addEnumConstant("INSTANCE")
                .addMethod(myMethodBuilder.build())
                .build();

        final JavaFile myJavaFile = JavaFile.builder(innerConstants.INSTANCE.GENERATED_FILE_PATH, myClass)
                .addFileComment("This file is automatically generated by APT skill. Do not Modify!!!")
                .build();

        try {
            myJavaFile.writeTo(mFilerUtils);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
