package org.leon.swagger.core;

import org.leon.swagger.annotation.*;
import org.leon.swagger.model.GlobalPara;
import org.leon.swagger.model.Swagger;
import org.leon.swagger.model.component.Path;
import org.leon.swagger.model.component.Response;
import org.leon.swagger.model.component.Tag;
import org.leon.swagger.utils.ClassHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Swagger 注解实现核心
 * @since 1.0.0
 */
class Core {

    private static Swagger swagger = Swagger.getInstance();

    private static Map<String, Map<String, Path.ApiMethod>> paths = Singleton.getPaths();
    private static Map<String, String> classMap = Singleton.getClassMap();

    /**
     * 生成Swagger Paths中方法介绍的对象
     */
    private static void getApiOperation(Path.ApiMethod apiMethod, Annotation annotation) {
        ApiOperation apiOperation = (ApiOperation) annotation;
        Map<String, Path.ApiMethod> methodMap = new HashMap<>();
        apiMethod.setSummary(apiOperation.description());
        apiMethod.setDescription(apiOperation.description());
        apiMethod.addTag(apiOperation.tag());
        apiMethod.addConsume(apiOperation.consumes());
        methodMap.put(apiOperation.httpMethod(), apiMethod);
        paths.put(apiOperation.url(), methodMap);
    }

    /**
     * 生成Swagger Paths中多参数的对象
     */
    private static void getParams(Path.ApiMethod apiMethod, Annotation annotation) {
        Params apiOperation = (Params) annotation;
        Param[] params = apiOperation.value();
        for (Param apiParam : params) {
            if (apiParam.dataType().equals("file")) {
                apiMethod.addParameter(new Path.Parameter(apiParam.name(),"formData",apiParam.description(),apiParam.required(),apiParam.dataType(),apiParam.format(),apiParam.defaultValue()));
            } else {
                apiMethod.addParameter(new Path.Parameter(apiParam.name(),apiParam.description(),apiParam.required(),apiParam.dataType(),apiParam.format(),apiParam.defaultValue()));
            }
        }
    }

    /**
     * 生成Swagger Paths中单参数的对象
     */
    private static void getParam(Path.ApiMethod apiMethod, Annotation annotation) {
        Param apiParam = (Param) annotation;
        apiMethod.addParameter(new Path.Parameter(apiParam.name(),apiParam.description(),apiParam.required(),apiParam.dataType(),apiParam.format(),apiParam.defaultValue()));
    }

    /**
     * 生成Swagger Paths中多响应的对象
     */
    private static void getResps(Path.ApiMethod apiMethod, Annotation annotation) {
        Resps apiOperation = (Resps) annotation;
        Resp[] resps = apiOperation.value();
        for (Resp apiResp : resps) {
            apiMethod.addResponse(apiResp.code(), new Response(apiResp.description()));
        }
    }

    /**
     * 生成Swagger Paths中单响应的对象
     */
    private static void getResp(Path.ApiMethod apiMethod, Annotation annotation) {
        Resp apiResponse = (Resp) annotation;
        apiMethod.addResponse(apiResponse.code(), new Response(apiResponse.description()));
    }

    /**
     * 生成Swagger Paths中某一Tag中所有方法注解的对象
     */
    private static void getMethodsAPI(Class<?> clazz) {
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {

            Path.ApiMethod apiMethod = new Path.ApiMethod();
            apiMethod.setOperationId(method.getName());
            apiMethod.addProduce("application/json");

            List<Path.Parameter> parameterList = GlobalPara.getParameterList();
            if (parameterList != null && parameterList.size() > 0) {
                for (Path.Parameter parameter : parameterList) {
                    apiMethod.addParameter(parameter);
                }
            }

            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();

                if (ApiOperation.class == annotationType) {
                    getApiOperation(apiMethod, annotation);
                }
                if (Params.class == annotationType) {
                    getParams(apiMethod, annotation);
                }
                if (Param.class == annotationType) {
                    getParam(apiMethod, annotation);
                }
                if (Resps.class == annotationType) {
                    getResps(apiMethod, annotation);
                }
                if (Resp.class == annotationType) {
                    getResp(apiMethod, annotation);
                }
            }
        }
    }

    /**
     * 生成Swagger Paths
     */
    static void createPaths() {
        Set<Class<?>> classSet = ClassHelper.getBeanClassSet();
        for (Class<?> cls : classSet) {
            if (cls.isAnnotationPresent(Api.class)) {
                Api api = cls.getAnnotation(Api.class);
                if (!classMap.containsKey(api.tag())) {
                    classMap.put(api.tag(), api.description());
                }
                getMethodsAPI(cls);
            }
        }
        swagger.setPaths(paths);
    }

    /**
     * 生成Swagger Tags
     */
    static void createTags() {
        if (classMap.size() > 0) {
            for (String key : classMap.keySet()) {
                swagger.addTags(new Tag(key, classMap.get(key)));
            }
        }
    }

}
