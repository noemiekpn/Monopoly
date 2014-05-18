package monopoly.src.game;
 
import java.awt.Image;
 
import javax.swing.JOptionPane;
 
public class Terrain extends Property {
        private int numHouses = 0;
        private int numHotels = 0;
       
        private int[] houseRents = new int[4];
        private int hotelRent;
        private int constructionCost;
 
        public Terrain(String name, int price, int rent, int mortgage, int location, Image img) {
                super(name, price, rent, mortgage, location, img);
        }
       
        public void setHouseRents(int rent1, int rent2, int rent3, int rent4) {
                houseRents[0] = rent1;
                houseRents[1] = rent2;
                houseRents[2] = rent3;
                houseRents[3] = rent4;
        }
       
       
        public void setHotelRent(int rent) {
                hotelRent = rent;
        }
       
        public void setConstructionCost(int cost) {
                constructionCost = cost;
        }
       
        public int getConstructionCost() {
                return constructionCost;
        }
       
        public int getTerrainRent() {
                int totalRent = 0;
               
                if(numHouses == 0)
                        totalRent = rent;
                else if(numHouses == 1)
                        totalRent = houseRents[0];
                else if(numHouses == 2)
                        totalRent = houseRents[1];
                else if(numHouses == 3)
                        totalRent = houseRents[2];
                else if(numHouses == 4 && numHotels == 0)
                        totalRent = houseRents[3];
                else if(numHouses == 4 && numHotels == 1)
                        totalRent = hotelRent;
                       
                return totalRent;
        }
       
        /**
         * Adds from 1 to 4 houses to terrain.
         * @param numHouses - Number of houses to be added.
         */
        public void addHouses(int numHouses) {
                int total = this.numHouses + numHouses;
               
                if(total > 4) {
                        // Terrain is ready for hotel
                        int ans = JOptionPane.showConfirmDialog(null,
                                        "You already have 4 houses here.\n" +
                                        "Do you want to add a hotel instead?",
                                        "House Limit Warning",
                                        JOptionPane.YES_NO_OPTION);
                       
                        if(ans == JOptionPane.YES_OPTION) {
                                addHotel();
                        }
                } else {
                        this.numHouses += numHouses;
                }
        }
       
        /**
         * Adds one hotel to terrain.
         */
        public void addHotel() {
                if(numHotels == 0) {
                        this.numHotels++;
                }
        }
 
        public void subHouses(int numHouses) {
                int total = this.numHouses - numHouses;
               
                if(total >= 0) {
                        this.numHouses = total;
                }
        }
       
        /**
         * Subtracts on hotel from terrain.
         */
        public void subHotel() {
                if(numHotels > 0) {
                        this.numHotels--;
                }
        }
}