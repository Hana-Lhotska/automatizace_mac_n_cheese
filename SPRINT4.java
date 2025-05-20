package cz.czechitas.automation;

import org.junit.jupiter.api.Test;

import java.time.Instant;

public class SPRINT4 extends TestRunner {

    void generallogin (String email, String password) {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(email);
        browser.loginSection.insertPassword(password);
        browser.loginSection.clickLoginButton();
    }
    void generaluserregistration (String name,String surname, String email, String password, String checkpassword) {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.clickRegisterButton();
        browser.registerSection.insertFirstAndLastName(name,surname);
        browser.registerSection.insertEmail(email);
        browser.registerSection.insertPassword(password);
        browser.registerSection.insertPasswordConfirmation(checkpassword);
        browser.registerSection.clickRegisterButton();
    }
    void generalorder_SCHOOLINNATURE (String ico,String Client, String Address, String Substitute, String name, String surname,
                                      String mail, String phone, String startdate, String starttime, String enddate, String endtime) {
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.insertICO(ico);
        browser.waitFor(5);
        browser.orderDetailSection.insertClient(Client);
        browser.orderDetailSection.insertFullAddress(Address);
        browser.orderDetailSection.insertSubstitute(Substitute);
        browser.orderDetailSection.insertContactPersonNameAndSurname(name,surname);
        browser.orderDetailSection.insertContactPersonEmail(mail);
        browser.orderDetailSection.insertContactPersonTelephone(phone);

        browser.orderSection.selectSchoolInNatureOption();

        browser.orderDetailSection.insertChildrenCountToSchoolInNature(25);
        browser.orderDetailSection.insertAdultsCountToSchoolInNature(5);
        browser.orderDetailSection.insertInAgeToSchoolInNature(15);
        browser.orderDetailSection.insertStartDate(startdate);
        browser.orderDetailSection.insertStartTime(starttime);
        browser.orderDetailSection.selectLunchStartToSchoolInNature();
        browser.orderDetailSection.insertEndDate(enddate);
        browser.orderDetailSection.insertEndTime(endtime);
        browser.orderDetailSection.selectBreakfastEndToSchoolInNature();
        browser.orderDetailSection.saveSchoolInNatureOrder();
        browser.waitFor(5);

    }
    void generalorder_SUBURBANCAMP (String ico,String Client, String Address, String Substitute, String name, String surname,
                                    String mail, String phone, String startdate, String enddate){
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.insertICO(ico);
        browser.waitFor(5);
        browser.orderDetailSection.insertClient(Client);
        browser.orderDetailSection.insertFullAddress(Address);
        browser.orderDetailSection.insertSubstitute(Substitute);
        browser.orderDetailSection.insertContactPersonNameAndSurname(name,surname);
        browser.orderDetailSection.insertContactPersonEmail(mail);
        browser.orderDetailSection.insertContactPersonTelephone(phone);
        browser.orderDetailSection.insertStartDate(startdate);
        browser.orderDetailSection.insertEndDate(enddate);

        browser.orderSection.selectSuburbanCampOption();

        browser.orderDetailSection.selectAfternoonSuburbanCampVariant();
        browser.orderDetailSection.insertChildrenCountToSuburbanCamp(20);
        browser.orderDetailSection.insertInAgeToSuburbanCamp(12);
        browser.orderDetailSection.insertAdultsCountToSuburbanCamp(4);
        browser.orderDetailSection.saveSuburbanCampOrder();
    }
    void generalapplication_asParent (String term, String name, String surname, String birthdate) {
        browser.applicationSection.clickCreateNewApplicationButton();
        browser.applicationSection.selectProgrammingSection();
        browser.applicationSection.clickCreatePythonApplicationButton();

        browser.applicationDetailsSection.selectTerm(term);
        browser.applicationDetailsSection.insertStudentFirstName(name);
        browser.applicationDetailsSection.insertStudentLastName(surname);
        browser.applicationDetailsSection.insertBirthdate(birthdate);
        browser.applicationDetailsSection.selectCashPaymentMethod();
        browser.applicationDetailsSection.clickAcceptTermsCheckbox();
        browser.applicationDetailsSection.clickCreateApplicationButton();

    }
    @Test
    void UserregistrationandloginprocesHP () {
        var name = "Ikeak";
        var surname = "Gingerak";
        var email = browser.generateRandomName(8) + "@mymail.com";
        var password = "GigerIkeak123";

        asserter.generalSection.checkCurrentUrl("https://team8-2022brno.herokuapp.com/");

        generaluserregistration(name,surname,email,password,password);
        browser.waitFor(5);
        asserter.applicationSection.checkIsLoggedIn();

        browser.loginSection.logout();
        browser.waitFor(5);
        asserter.loginSection.checkUserIsNotLoggedIn();

        generallogin(email,password);
        browser.waitFor(5);
        asserter.applicationSection.checkIsLoggedIn();
    }

    @Test
    void Createbookingasteacher_SCHOOLINNATURE_LUaBF (){
        var ico = "44065663";
        var client ="KG" + browser.generateRandomName(2) + Instant.now();
        var address = "Otmarova 33";
        var substitute = "Plevelkova";
        var name = "Pavla" ;
        var surname = "Plevelkova";
        var mail = "pavlaplevelkova@mymail.cz";
        var phone = "123456789";
        var startdate = "10.10.2025";
        var starttime = "12:00";
        var enddate = "14.10.2025";
        var endtime = "10:00";


        asserter.generalSection.checkCurrentUrl("https://team8-2022brno.herokuapp.com/");
        asserter.loginSection.checkUserIsNotLoggedIn();

        generalorder_SCHOOLINNATURE(ico,client,address,substitute,name,surname,mail,phone,startdate,starttime,enddate,endtime);
        browser.waitFor(5);

        generallogin("da-app.master@czechitas.cz", "AppRoot123");
        browser.waitFor(5);
        //asserter.applicationSection.checkIsLoggedIn();
        browser.internalMenu.goToOrdersSection();
        browser.orderSection.search(client);
        asserter.orderSection.checkNumberOfOrders(1);

        browser.loginSection.logout();
        asserter.loginSection.checkUserIsNotLoggedIn();

        generallogin("da-app.admin@czechitas.cz", "Czechitas123");
        browser.waitFor(5);
        //asserter.applicationSection.checkIsLoggedIn();
        browser.internalMenu.goToOrdersSection();
        browser.orderSection.search(client);
        asserter.orderSection.checkNumberOfOrders(1);
    }
    @Test
    void Createbookingasteacher_SUBURBANCAMP_Af () {
        var ico = "63418435";
        var client ="GTR" + browser.generateRandomName(2) + Instant.now();
        var address = "Masarykovo namesti 9";
        var substitute = "Lopatkova";
        var name = "Sandra" ;
        var surname = "Lopatkova";
        var mail = "sandralopatkova@mymail.cz";
        var phone = "987654321";
        var startdate = "12.12.2025";
        var enddate = "19.12.2025";

        asserter.generalSection.checkCurrentUrl("https://team8-2022brno.herokuapp.com/");
        asserter.loginSection.checkUserIsNotLoggedIn();

        generalorder_SUBURBANCAMP(ico,client,address,substitute,name,surname,mail,phone,startdate,enddate);
        browser.waitFor(5);

        generallogin("da-app.master@czechitas.cz", "AppRoot123");
        browser.waitFor(5);

        browser.internalMenu.goToOrdersSection();
        browser.orderSection.search(client);
        asserter.orderSection.checkNumberOfOrders(1);

        browser.loginSection.logout();
        asserter.loginSection.checkUserIsNotLoggedIn();

        generallogin("da-app.admin@czechitas.cz", "Czechitas123");
        browser.waitFor(5);
        browser.internalMenu.goToOrdersSection();
        browser.orderSection.search(client);
        asserter.orderSection.checkNumberOfOrders(1);
    }
    @Test
    void createapplicationasparent_Happypath () {
        var email = "panda.cervena@mymail.com";
        var password = "PandaCervena123";
        var term = "04.06. - 08.06.2025";
        var name = "Tunic";
        var surname = browser.generateRandomName(10);
        var birthdate = "12.12.2012";

        asserter.generalSection.checkCurrentUrl("https://team8-2022brno.herokuapp.com/");
        asserter.loginSection.checkUserIsNotLoggedIn();

        generallogin(email,password);
        browser.waitFor(5);
        asserter.applicationSection.checkIsLoggedIn();

        generalapplication_asParent(term,name,surname,birthdate);
        browser.waitFor(5);

        browser.headerMenu.goToApplicationsSection();
        browser.applicationSection.search(surname);
        asserter.applicationSection.checkNumberOfApplications(1);

        browser.applicationSection.openFirstApplicationDetailsPage();

        asserter.applicationDetailSection.checkTerm(term);
        asserter.applicationDetailSection.checkparentemail(email);
        asserter.applicationDetailSection.checkFirstName(name);
        asserter.applicationDetailSection.checkLastName(surname);
        asserter.applicationDetailSection.checkPaymentMethod("Hotově");
    }
}
