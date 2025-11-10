package com.bazaarstores.pages;

public class AllPages {

    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private DashboardPage dashboardPage;
    private ProductsPage productsPage;
    private StoresPage storesPage;


    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public RegistrationPage getRegistrationPage() {
        if (registrationPage == null) {
            registrationPage = new RegistrationPage();
        }
        return registrationPage;
    }

    public DashboardPage getDashboardPage() {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage();
        }
        return dashboardPage;
    }

    public ProductsPage getProductsPage() {
        if (productsPage == null) {
            productsPage = new ProductsPage();
        }
        return productsPage;
    }

    public StoresPage getStoresPage() {
        if (storesPage == null) {
            storesPage = new StoresPage();
        }
        return storesPage;
    }
}