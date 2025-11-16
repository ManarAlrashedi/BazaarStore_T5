package com.bazaarstores.pages;

import com.bazaarstores.pages.Admin.CreateStorePage;
import com.bazaarstores.pages.Admin.CreateUserPage;
import com.bazaarstores.pages.Admin.StoresPage;
import com.bazaarstores.pages.Admin.UsersPage;
import com.bazaarstores.pages.Customer.CartPage;
import com.bazaarstores.pages.Customer.CustomerPage;
import com.bazaarstores.pages.Login.LoginPage;
import com.bazaarstores.pages.Registration.RegistrationPage;
import com.bazaarstores.pages.StoreManager.ProductsPage;

public class AllPages {

    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private DashboardPage dashboardPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private StoresPage storesPage;
    private CustomerPage customerPage;
    private UsersPage usersPage;
    private CreateUserPage createUserPage;
   private CreateStorePage createStorePage;


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
    public CartPage getCartPage () {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }
    public CreateStorePage getCreateStorePage () {
        if (createStorePage == null) {
            createStorePage = new CreateStorePage();
        }
        return createStorePage;
   }
public CustomerPage getCustomerPage () {
    if (customerPage == null) {
        customerPage = new CustomerPage();
    }
    return customerPage;
}
    public UsersPage getUsersPage() {
        if (usersPage == null) {
            usersPage = new UsersPage();
        }
        return usersPage;
    }
    public CreateUserPage getCreateUserPage() {
        if (createUserPage == null) {
            createUserPage = new CreateUserPage();
        }
        return createUserPage;
    }
}