package testcases.booktour;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.CheckOut;
import pages.HomePage;
import pages.Tour;
import pages.TourDetail;

public class TC_CreateBooking extends BaseTest {
    HomePage homePage;
    TourDetail tourDetail;
    Tour tour;
    CheckOut checkOut;

    @Test
    public void createbooking(){
        homePage= new HomePage(driver);
        homePage.navigateToNorthernJapan();
        tour = new Tour(driver);
        tour.clickViewDetailTourButton("/trips/trip-to-nago/");
        tourDetail=new TourDetail(driver);
        tourDetail.bookTourWithHasRoomOptAndHasDeposit("3","Yes");
        checkOut = new CheckOut(driver);
        checkOut.inputFirstName("Nha");
    }
}
