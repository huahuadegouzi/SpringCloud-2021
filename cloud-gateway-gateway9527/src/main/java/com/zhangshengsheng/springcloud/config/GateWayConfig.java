package com.zhangshengsheng.springcloud.config;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springcloud2021
 * @description: 配置类
 * @author: 张胜胜
 * @create: 2021-02-17 20:14
 **/
@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder  routes = routeLocatorBuilder.routes();

        /*
         * 代表访问http://localhost:9527/guonei
         * 跳转到http://news.baidu.com/guonei
         * */
        routes.route("route1",
                r->r.path("/guonei")
                        .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}