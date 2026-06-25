package cl.duoc.api_gateway;

import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> clientesRoute() {
        return GatewayRouterFunctions.route("msvc_clientes")
                .route(RequestPredicates.path("/api/clientes/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("msvc_clientes"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> clientesApiDocsRoute() {
        return GatewayRouterFunctions.route("msvc_clientes_docs")
                .route(RequestPredicates.path("/clientes/api-docs"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("msvc_clientes"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> eventosRoute() {
        return GatewayRouterFunctions.route("msvc_eventos")
                .route(RequestPredicates.path("/api/eventos/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("msvc_eventos"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> categoriasRoute() {
        return GatewayRouterFunctions.route("msvc_categorias")
                .route(RequestPredicates.path("/api/categorias/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("msvc_eventos"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> recintosRoute() {
        return GatewayRouterFunctions.route("msvc_recintos")
                .route(RequestPredicates.path("/api/recintos/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("msvc_recintos"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> exponentesRoute() {
        return GatewayRouterFunctions.route("msvc_exponentes")
                .route(RequestPredicates.path("/api/exponentes/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("msvc_exponentes"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> preciosRoute() {
        return GatewayRouterFunctions.route("msvc_precios")
                .route(RequestPredicates.path("/api/precios/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("msvc_precios"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> ordenesRoute() {
        return GatewayRouterFunctions.route("msvc_ordenes")
                .route(RequestPredicates.path("/api/ordenes/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("msvc_ordenes"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> ordenesApiDocsRoute() {
        return GatewayRouterFunctions.route("msvc_ordenes_docs")
                .route(RequestPredicates.path("/ordenes/api-docs"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("msvc_ordenes"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> ticketsRoute() {
        return GatewayRouterFunctions.route("msvc_tickets")
                .route(RequestPredicates.path("/api/tickets/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("msvc_tickets"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> resenasRoute() {
        return GatewayRouterFunctions.route("msvc_resenas")
                .route(RequestPredicates.path("/api/resenas/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("msvc_resenas"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> preguntasRoute() {
        return GatewayRouterFunctions.route("msvc_preguntas")
                .route(RequestPredicates.path("/api/preguntas/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("msvc_resenas"))
                .build();
    }
}