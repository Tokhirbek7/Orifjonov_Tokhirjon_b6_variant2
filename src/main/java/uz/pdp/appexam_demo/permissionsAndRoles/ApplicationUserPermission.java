package uz.pdp.appexam_demo.permissionsAndRoles;

public enum ApplicationUserPermission {
    PRODUCT_ADD("adding the product:"),
    PRODUCT_UPDATE("updating the product:"),
    PRODUCT_READ("updating the product:"),
    PRODUCT_DELETE("updating the product:"),
    PRODUCT_READ_BY_ID("Read the product by id:");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
}
