package monopoly.src.game;
 
import java.awt.Image;
 
abstract class Property {
        protected int ownerID;
 
        protected final int location;   // Position in board
 
        protected final String name;
        protected final int price;
        protected int rent;
        protected final int mortgage;
        protected final Image img;
 
        public Property(String name, int price, int rent, int mortgage, int location, Image img) {
                ownerID = 7;    // All properties belong to the bank at beginning
 
                this.name = name;
                this.price = price;
                this.rent = rent;
                this.mortgage = mortgage;
                this.location = location;
                this.img = img;
        }
       
        /**Tells the property'name*/   
        public String getPropertyName() {
                return name;
        }
       
        /**Returns the buying price */
        public int getPropertyPrice() {
                return price;
        }
       
     /**Returns the mortgage's price  */
        public int getPropertyMortgage() {
                return mortgage;
        }
       
        /**Tells who's the owner */
        public int getPropertyOwnerID() {
                return ownerID;
        }
       
        /**Set new ownership */
        public void setPropertyOwner(int ownerID) {
                this.ownerID = ownerID;
        }
       
        /**Returns the property location on board*/
        public int getPropertyLocation(){
                return location;
        }
       
        public void drawCard(GUI_PANEL Pane){
                Pane.removeAll();
                Pane.setImg(img, 0);
                Pane.setImgPos(0, 0, 0);
 
 
        }
}