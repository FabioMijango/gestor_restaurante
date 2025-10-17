package com.fabiomijango.gestor_restaurante.util;

public final class Constants {
    private Constants() {
        throw new AssertionError();
    }

    // Base path
    public static final String API_BASE_PATH = "/api/v1";
    public static final String WILDCARD_PATH = "/**";

    // Common path variables
    public static final String PATH_ID = "/{id}";

    //Dish paths
    public static final String DISH_CONTROLLER = "/dishes";

    // Table paths
    public static final String TABLE_CONTROLLER = "/tables";

    // Order paths
    public static final String ORDER_CONTROLLER = "/orders";

    // DishOrder paths
    public static final String DISH_ORDER_PATH = "/dishOrder";


    // User paths
    public static final String USER_CONTROLLER = "/users";
    public static final String AUTH_PATH = "/auth";
    public static final String LOGIN_PATH = "/login";

    public enum Roles {
        ADMIN,
        CHEF,
        WAITER
    }
}
