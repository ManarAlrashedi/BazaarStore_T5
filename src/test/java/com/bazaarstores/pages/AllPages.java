package com.bazaarstores.pages;

import com.bazaarstores.utilities.Driver;

public class AllPages {

    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private DashboardPage dashboardPage;
    private ProductsPage productsPage;
    private CustomerCartPage customerCartPage;
    private StoresPage storesPage;
    private CreateStorePage createStorePage;
    private UsersPage userPage;


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
    public StoresPage getStoresPage() {
        if (storesPage == null) {
            storesPage = new StoresPage();
        }
        return storesPage;
    }

    public ProductsPage getProductsPage() {
        if (productsPage == null) {
            productsPage = new ProductsPage();
        }
        return productsPage;
    }
    public CustomerCartPage getCustomerCartPage () {
        if (customerCartPage == null) {
            customerCartPage = new CustomerCartPage();
        }
        return customerCartPage;
    }

    public CreateStorePage getCreateStorePage () {
        if (createStorePage == null) {
            createStorePage = new CreateStorePage();
        }
        return createStorePage;
    }

    public UsersPage getUsersPage () {
        if (userPage == null) {
            userPage = new UsersPage();
        }
        return userPage;
    }

    public UsersPage getUserPage() {
        if (userPage == null) {
            userPage = new UsersPage();
        }
        return userPage;
    }
}