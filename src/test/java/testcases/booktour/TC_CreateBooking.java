package testcases.booktour;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.Tour;
import pages.TourDetail;

public class TC_CreateBooking extends BaseTest {
    HomePage homePage;
    TourDetail tourDetail;
    Tour tour;

    @Test
    public void createbooking(){
        homePage= new HomePage(driver);
        tourDetail = new TourDetail(driver);
        tour = new Tour(driver);
        //homePage.clickTour();
        homePage.navigateToNorthernJapan();
        tour.clickViewDetailTourButton("/trips/trip-to-nago/");
        tourDetail.clickBtnBookNow();
//        tourDetail.selectRandomStartDateLt60Days();
    }
}
