package org.yuhan.ziyu.gateway.config.swagger;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuwu on 2018/9/13 0013.
 */
@Component
@Primary
@EnableSwagger2Doc
@ConfigurationProperties(prefix = "swagger")
@ConditionalOnExpression("#{'${spring.profiles.active}'.equals('prod') == false}")
public class SwaggerConfig implements SwaggerResourcesProvider {

    private List<String> apps;

    public void setApps(List<String> apps) {
        this.apps = apps;
    }

    /**
     * Retrieves an instance of the appropriate type. The returned object may or
     * may not be a new instance, depending on the implementation.
     *
     * @return an instance of the appropriate type
     */
    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        apps.forEach(app->{
            resources.add(swaggerResource(app, "/"+app+"/v2/api-docs", "1.0"));
        });
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
