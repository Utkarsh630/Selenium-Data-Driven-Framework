package utils;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider ( name = "valid_login_data" )
    public static Object[][] getValidLoginData(){
        return ExcelFileLoader.getExcelData ( "valid_login_data" );
    }

    @DataProvider ( name = "invalid_login_data" )
    public static Object[][] getInvalidLoginData(){
        return ExcelFileLoader.getExcelData ( "invalid_login_data" );
    }

    @DataProvider ( name = "forgot_password_data" )
    public static Object[][] getInvalidForgotPasswordData(){
        return ExcelFileLoader.getExcelData ( "forgot_password_data" );
    }

}
